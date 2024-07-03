package iii.unipv.soproject;

import java.util.concurrent.BlockingQueue;

public class Cliente implements Runnable {
    private int id;
    private BlockingQueue<Ordine> ordini;

    public Cliente(int id, BlockingQueue<Ordine> ordini) {
        this.id = id;
        this.ordini = ordini;
    }

    @Override
    public void run() {
        try {
            // Il cliente fa un ordine
            Ordine ordine = new Ordine("Ordine del Cliente " + id);
            System.out.println("Cliente " + id + " fa un ordine: " + ordine.getDescrizione());
            ordini.put(ordine);

            // Simula il tempo di attesa del cliente
            Thread.sleep((int)(Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
