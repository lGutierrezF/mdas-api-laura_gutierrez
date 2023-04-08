package shared.domain;


import java.io.Serializable;

public abstract class DomainEvent implements Serializable {
    private final int ID;
    private final DomainEventMetaData domainEventMetaData;

    public DomainEvent(int ID) {
        this.ID = ID;
        this.domainEventMetaData = new DomainEventMetaData(ID);
    }
    public abstract String type();
}
