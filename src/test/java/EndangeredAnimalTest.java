import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

public class EndangeredAnimalTest {
    EndangeredAnimal animal,animal1;

    @BeforeEach
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "kosgei", "12345678");
        animal = new EndangeredAnimal("Elephant","Ill","Old");
        animal1 = new EndangeredAnimal("Lion","Okay","Young");
    }

    @AfterEach
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals *;";
            con.createQuery(sql).executeUpdate();
        }

    }

    @Test
    public void animal_instantiatesCorrectly() {
        assertNotNull(animal);
    }

    @Test
    public void animal_getsName_Lion() {
        assertEquals("Elephant", animal.getName());
    }

    @Test
    public void animal_getshealth_Okay() {
        assertEquals("Ill", animal.getHealth());
    }

    @Test
    public void animal_getsAge_Old() {
        assertEquals("Old", animal.getAge());
    }

    @Test
    public void all_returnsAllInstancesOfEndangeredAnimal_true() {
        animal.save();
        animal1.save();
        assertEquals(EndangeredAnimal.all().get(0), animal);
        assertEquals(EndangeredAnimal.all().get(1), animal1);
    }

    @Test
    public void save_assignsIdToObject() {
        animal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.allEndangeredAnimals().get(0);
        System.out.println(savedEndangeredAnimal.getId());
        assertEquals(animal.getId(), savedEndangeredAnimal.getId());
    }
}