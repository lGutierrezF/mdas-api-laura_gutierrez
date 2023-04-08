package shared.domain;

import java.util.Set;

public interface EventPublisher {
    public  void publish(DomainEvent event);
    public  void publish(Set<DomainEvent> events);
}
