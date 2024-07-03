package iii.unipv.soproject;

import java.util.concurrent.BlockingQueue;

public class Cameriere implements Runnable {
    private BlockingQueue<Ordine> ordini;
    private BlockingQueue<Ordine> ordiniDaPreparare;

    public Cameriere(BlockingQueue<Ordine> ordini, BlockingQueue<Ordine> ordiniDaPreparare) {
        this.ordini = ordini;
        this.ordiniDaPreparare = ordiniDaPreparare;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Il cameriere prende un ordine dal cliente
                Ordine ordine = ordini.take();
                System.out.println("Cameriere prende: " + ordine.getDescrizione());

                // Il cameriere porta l'ordine in cucina
                ordiniDaPreparare.put(ordine);

                // Simula il tempo di servizio del cameriere
                Thread.sleep((int)(Math.random() * 5000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
