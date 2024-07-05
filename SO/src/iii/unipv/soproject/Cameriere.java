package iii.unipv.soproject;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Cameriere implements Runnable {
    private static final Logger logger = Logger.getLogger(Cameriere.class.getName());
    private BlockingQueue<Ordine> ordini;
    private BlockingQueue<Ordine> ordiniDaPreparare;

    public Cameriere(BlockingQueue<Ordine> ordini, BlockingQueue<Ordine> ordiniDaPreparare) {
        this.ordini = ordini;
        this.ordiniDaPreparare = ordiniDaPreparare;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Ordine ordine = ordini.take();
                logger.info("Cameriere prende: " + ordine.getDescrizione());
                ordiniDaPreparare.put(ordine);
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }finally {
            logger.log(Level.INFO, "Thread Cameriere interrotto");
        }
    }
}
