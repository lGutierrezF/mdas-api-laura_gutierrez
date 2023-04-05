package shared;


import java.io.Serializable;
import java.sql.Timestamp;

public class DomainEventMetaData implements Serializable {
    private final int ID;
    private final Timestamp timestamp;

    public DomainEventMetaData(int ID) {
        this.ID = ID;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
