package iii.unipv.soproject;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Main {
	static int N_THREAD=5;

    public static void main(String[] args) {
        BlockingQueue<Ordine> ordini = new LinkedBlockingQueue<>();
        BlockingQueue<Ordine> ordiniDaPreparare = new LinkedBlockingQueue<>();

        Thread cucina = new Thread(new Cucina(ordiniDaPreparare));
        Thread cameriere = new Thread(new Cameriere(ordini, ordiniDaPreparare));

        cucina.start();
        cameriere.start();

        for (int i = 1; i <= 5; i++) {
            new Thread(new Cliente(i, ordini)).start();
        }
    }
}
