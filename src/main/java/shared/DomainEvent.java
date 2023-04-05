package shared;


public abstract class DomainEvent {
    private int ID;
    private DomainEventMetaData domainEventMetaData;

    public DomainEvent(int ID) {
        this.ID = ID;
        this.domainEventMetaData = new DomainEventMetaData(ID);
    }
    public abstract String type();
}
