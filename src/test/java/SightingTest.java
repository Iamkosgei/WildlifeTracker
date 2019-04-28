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
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "kosgei", "12345678");
        sighting = new Sighting(1, 1);
        sighting2 = new Sighting(2, 2);


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
    public void sighting_getsLocationId_1() {
        assertEquals(1, sighting.getLocationId());
    }

    @Test
    public void ranger_getsRangerId_1() {
        assertEquals(1, sighting.getRangerId());
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