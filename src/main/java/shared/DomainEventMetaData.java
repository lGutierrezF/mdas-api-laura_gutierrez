package shared;


import java.sql.Timestamp;

public class DomainEventMetaData {
    private int ID;
    private Timestamp timestamp;

    public DomainEventMetaData(int ID) {
        this.ID = ID;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
