package iii.unipv.soproject;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Cliente implements Runnable {
    private static final Logger logger = Logger.getLogger(Cliente.class.getName());
    private int id;
    private BlockingQueue<Ordine> ordini;

    public Cliente(int id, BlockingQueue<Ordine> ordini) {
        this.id = id;
        this.ordini = ordini;
    }

    @Override
    public void run() {
        try {
            Ordine ordine = new Ordine("Ordine del Cliente " + id);
            logger.info("Cliente " + id + " fa un ordine: " + ordine.getDescrizione());
            ordini.put(ordine);
            Thread.sleep((int)(Math.random() * 10000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.WARNING, "Thread Cliente " + id + " interrotto", e);
        } finally {
        	logger.info("Thread Cliente " + id + " è stato chiuso");
        }
    }
}