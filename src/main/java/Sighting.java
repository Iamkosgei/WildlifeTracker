import org.sql2o.Connection;

import java.util.List;

public class Sighting {
    private int id;
    private int locationId;
    private int rangerId;

    public Sighting(int locationId, int rangerId) {
        this.locationId = locationId;
        this.rangerId= rangerId;
    }

    public int getId() {
        return id;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getRangerId() {
        return rangerId;
    }

    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open())  {
            String sql = "INSERT INTO sightings (locationid, rangerid) VALUES (:locationId, :rangerId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("locationId", this.locationId)
                    .addParameter("rangerId",this.rangerId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting= con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

    @Override
    public boolean equals(Object otherSighting){
        if (!(otherSighting instanceof Sighting)) {
            return false;
        } else {
            Sighting newSighting = (Sighting) otherSighting;
            return this.getId() == newSighting.id;
        }
    }

}
