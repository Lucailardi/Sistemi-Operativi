package iii.unipv.soproject;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Cliente implements Runnable {
    private static final Logger logger = Logger.getLogger(Cliente.class.getName());
    private int id;
    private BlockingQueue<Ordine> ordini;
    private EventListener listener;

    public Cliente(int id, BlockingQueue<Ordine> ordini, EventListener listener) {
        this.id = id;
        this.ordini = ordini;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            Ordine ordine = new Ordine("Ordine del Cliente " + id);
            logger.info("Cliente " + id + " fa un ordine: " + ordine.getDescrizione());
            ordini.put(ordine);
            if (listener != null) {
                listener.handleEvent("Cliente " + id + " ha fatto un ordine.", EventListener.EventType.CLIENTE);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.WARNING, "Thread Cliente " + id + " interrotto", e);
        }
    }
}
