import edu.Task3;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3test {

    @Test
    void testAddAndFindByName() {
        Task3.CachedPersonDatabase database = new Task3.CachedPersonDatabase();
        Task3.Person person = new Task3.Person(1, "John", "123 Main St", "555-1234");
        database.add(person);

        List<Task3.Person> foundPersons = database.findByName("John");
        assertEquals(1, foundPersons.size());
        assertEquals(person, foundPersons.get(0));
    }

    @Test
    void testAddAndFindByAddress() {
        Task3.CachedPersonDatabase database = new Task3.CachedPersonDatabase();
        Task3.Person person = new Task3.Person(1, "John", "123 Main St", "555-1234");
        database.add(person);

        List<Task3.Person> foundPersons = database.findByAddress("123 Main St");
        assertEquals(1, foundPersons.size());
        assertEquals(person, foundPersons.get(0));
    }

    @Test
    void testAddAndFindByPhone() {
        Task3.CachedPersonDatabase database = new Task3.CachedPersonDatabase();
        Task3.Person person = new Task3.Person(1, "John", "123 Main St", "555-1234");
        database.add(person);

        List<Task3.Person> foundPersons = database.findByPhone("555-1234");
        assertEquals(1, foundPersons.size());
        assertEquals(person, foundPersons.get(0));
    }

    @Test
    void testDelete() {
        Task3.CachedPersonDatabase database = new Task3.CachedPersonDatabase();
        Task3.Person person = new Task3.Person(1, "John", "123 Main St", "555-1234");
        database.add(person);

        database.delete(1);

        List<Task3.Person> notFoundPersons = database.findByName("John");
        assertTrue(notFoundPersons.isEmpty());
    }
}
