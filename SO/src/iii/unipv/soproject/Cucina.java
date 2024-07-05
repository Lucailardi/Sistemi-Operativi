package iii.unipv.soproject;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Cucina implements Runnable {
    private static final Logger logger = Logger.getLogger(Cucina.class.getName());
    private BlockingQueue<Ordine> ordiniDaPreparare;

    public Cucina(BlockingQueue<Ordine> ordiniDaPreparare) {
        this.ordiniDaPreparare = ordiniDaPreparare;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Ordine ordine = ordiniDaPreparare.take();
                logger.info("Cucina prepara: " + ordine.getDescrizione());
                Thread.sleep((int)(Math.random() * 10000));
                logger.info("Cucina ha preparato: " + ordine.getDescrizione());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.INFO, "Thread Cucina interrotto");
        }
    }
}
