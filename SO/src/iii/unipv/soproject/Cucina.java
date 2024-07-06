package iii.unipv.soproject;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Cucina implements Runnable {
    private static final Logger logger = Logger.getLogger(Cucina.class.getName());
    private BlockingQueue<Ordine> ordiniDaPreparare;
    private EventListener listener;

    public Cucina(BlockingQueue<Ordine> ordiniDaPreparare, EventListener listener) {
        this.ordiniDaPreparare = ordiniDaPreparare;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Ordine ordine = ordiniDaPreparare.take();
                logger.info("Cucina prepara: " + ordine.getDescrizione());
                if (listener != null) {
                    listener.handleEvent("Cucina prepara: " + ordine.getDescrizione(), EventListener.EventType.CUCINA);
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(10000));
                logger.info("Cucina ha preparato: " + ordine.getDescrizione());
                if (listener != null) {
                    listener.handleEvent("Cucina ha preparato: " + ordine.getDescrizione(), EventListener.EventType.CUCINA);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            logger.log(Level.INFO, "Thread Cucina interrotto");
        }
    }
}
