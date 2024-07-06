package iii.unipv.soproject;

public interface EventListener {
    void handleEvent(String message, EventType eventType);

    enum EventType {
        CLIENTE, CAMERIERE, CUCINA
    }
}
