package edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {

    public static class Person {
        private final int id;
        private final String name;
        private final String address;
        private final String phoneNumber;

        public Person(int id, String name, String address, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
    }

    public static class CachedPersonDatabase implements PersonDatabase {
        private final Object lock = new Object();
        private final Map<Integer, Person> idIndex = new HashMap<>();
        private final Map<String, List<Person>> nameIndex = new HashMap<>();
        private final Map<String, List<Person>> addressIndex = new HashMap<>();
        private final Map<String, List<Person>> phoneNumberIndex = new HashMap<>();

        @Override
        public void add(Person person) {
            synchronized (lock) {
                idIndex.put(person.getId(), person);
                addToIndex(nameIndex, person.getName(), person);
                addToIndex(addressIndex, person.getAddress(), person);
                addToIndex(phoneNumberIndex, person.getPhoneNumber(), person);
            }
        }

        @Override
        public void delete(int id) {
            synchronized (lock) {
                Person person = idIndex.get(id);
                if (person != null) {
                    removeFromIndex(nameIndex, person.getName(), person);
                    removeFromIndex(addressIndex, person.getAddress(), person);
                    removeFromIndex(phoneNumberIndex, person.getPhoneNumber(), person);
                    idIndex.remove(id);
                }
            }
        }

        @Override
        public List<Person> findByName(String name) {
            synchronized (lock) {
                return nameIndex.getOrDefault(name, new ArrayList<>());
            }
        }

        @Override
        public List<Person> findByAddress(String address) {
            synchronized (lock) {
                return addressIndex.getOrDefault(address, new ArrayList<>());
            }
        }

        @Override
        public List<Person> findByPhone(String phone) {
            synchronized (lock) {
                return phoneNumberIndex.getOrDefault(phone, new ArrayList<>());
            }
        }

        private void addToIndex(Map<String, List<Person>> index, String key, Person person) {
            index.computeIfAbsent(key, k -> new ArrayList<>()).add(person);
        }

        private void removeFromIndex(Map<String, List<Person>> index, String key, Person person) {
            List<Person> persons = index.get(key);
            if (persons != null) {
                persons.remove(person);
                if (persons.isEmpty()) {
                    index.remove(key);
                }
            }
        }
    }

    public interface PersonDatabase {
        void add(Person person);
        void delete(int id);

        List<Person> findByName(String name);
        List<Person> findByAddress(String address);
        List<Person> findByPhone(String phone);
    }

    public static void main(String[] args) {
        // Пример использования
        CachedPersonDatabase personDatabase = new CachedPersonDatabase();

        Person person1 = new Person(1, "John", "123 Main St", "555-1234");
        Person person2 = new Person(2, "Jane", "456 Oak St", "555-5678");

        personDatabase.add(person1);
        personDatabase.add(person2);

        List<Person> foundByName = personDatabase.findByName("John");
        System.out.println(foundByName);

        List<Person> foundByAddress = personDatabase.findByAddress("456 Oak St");
        System.out.println(foundByAddress);

        List<Person> foundByPhone = personDatabase.findByPhone("555-5678");
        System.out.println(foundByPhone);

        personDatabase.delete(1);
        List<Person> notFound = personDatabase.findByName("John");
        System.out.println(notFound);
    }
}
