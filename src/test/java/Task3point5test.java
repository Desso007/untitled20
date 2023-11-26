

import edu.Task3point5;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class Task3point5test {

    @Test
    void testAddAndFindByName() {
        Task3point5.Person person = new Task3point5.Person(1, "John", "123 Main St", "555-1234");
        Task3point5.CachedPersonDatabase database = new Task3point5.CachedPersonDatabase();
        database.add(person);

        List<Task3point5.Person> foundPersons = database.findByName("John");

        assertEquals(1, foundPersons.size());
        assertEquals(person, foundPersons.get(0));
    }

    @Test
    void testDelete() {
        Task3point5.Person person = new Task3point5.Person(1, "John", "123 Main St", "555-1234");
        Task3point5.CachedPersonDatabase database = new Task3point5.CachedPersonDatabase();
        database.add(person);

        List<Task3point5.Person> foundPersonsBeforeDeletion = database.findByName("John");
        assertEquals(1, foundPersonsBeforeDeletion.size());

        database.delete(1);

        List<Task3point5.Person> foundPersonsAfterDeletion = database.findByName("John");
        assertEquals(0, foundPersonsAfterDeletion.size());
    }

    @Test
    void testFindByNameWithReadWriteLock() {
        Task3point5.Person person1 = new Task3point5.Person(1, "John", "123 Main St", "555-1234");
        Task3point5.Person person2 = new Task3point5.Person(2, "John", "456 Oak St", "555-5678");

        Task3point5.CachedPersonDatabase database = new Task3point5.CachedPersonDatabase();
        database.add(person1);
        database.add(person2);

        Runnable readOperation = () -> {
            List<Task3point5.Person> foundPersons = database.findByName("John");
            assertTrue(foundPersons.contains(person1));
            assertTrue(foundPersons.contains(person2));
        };

        Runnable writeOperation = () -> {
            database.add(new Task3point5.Person(3, "Jane", "789 Pine St", "555-9876"));
        };

        // Параллельно выполняем одну операцию на чтение, другую на запись
        Thread readThread = new Thread(readOperation);
        Thread writeThread = new Thread(writeOperation);

        readThread.start();
        writeThread.start();

        // Ждем завершения обоих потоков
        try {
            readThread.join();
            writeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
