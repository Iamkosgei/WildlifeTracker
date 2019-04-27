import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

public class SightingTest {
    private Sighting sighting,sighting2;

    @BeforeEach
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "kosgei", "12345678");
        sighting = new Sighting("Zone A", "John Doe");
        sighting2 = new Sighting("Zone B", "Jane Doe");


    }

    @AfterEach
    public void tearDown() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sightings *;";
            con.createQuery(sql).executeUpdate();
        }
    }

    @Test
    public void sighting_instantiatesCorrectly() {
        assertNotNull(sighting);
    }

    @Test
    public void sighting_getsLocation_Zone_A() {
        assertEquals("Zone A", sighting.getLocation());
    }

    @Test
    public void ranger_getsRangerName_John_Doe() {
        assertEquals("John Doe", sighting.getRangerName());
    }

    @Test
    public void all_returnsAllInstancesOfSightings_true() {
        sighting.save();
        sighting2.save();
        assertEquals(Sighting.all().get(0), sighting);
        assertEquals(Sighting.all().get(1), sighting2);
    }

    @Test
    public void save_assignsIdToObject() {
        sighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(sighting.getId(), savedSighting.getId());
    }

}