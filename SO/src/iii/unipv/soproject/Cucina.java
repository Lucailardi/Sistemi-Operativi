package iii.unipv.soproject;

import java.util.concurrent.BlockingQueue;

public class Cucina implements Runnable {
    private BlockingQueue<Ordine> ordiniDaPreparare;

    public Cucina(BlockingQueue<Ordine> ordiniDaPreparare) {
        this.ordiniDaPreparare = ordiniDaPreparare;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // La cucina prepara un ordine
                Ordine ordine = ordiniDaPreparare.take();
                System.out.println("Cucina prepara: " + ordine.getDescrizione());

                // Simula il tempo di preparazione dell'ordine
                Thread.sleep((int)(Math.random() * 10000));

                System.out.println("Cucina ha preparato: " + ordine.getDescrizione());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}