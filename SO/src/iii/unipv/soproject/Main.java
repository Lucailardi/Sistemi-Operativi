package iii.unipv.soproject;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class Main {
    static int N_CLIENTI = 5;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        BlockingQueue<Ordine> ordini = new LinkedBlockingQueue<>();
        BlockingQueue<Ordine> ordiniDaPreparare = new LinkedBlockingQueue<>();

        ExecutorService executor = Executors.newFixedThreadPool(7);
        executor.execute(new Cucina(ordiniDaPreparare));
        executor.execute(new Cameriere(ordini, ordiniDaPreparare));

        for (int i = 1; i <= N_CLIENTI; i++) {
            executor.execute(new Cliente(i, ordini));
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            executor.shutdownNow();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    logger.warning("Forzare l'arresto dei thread...");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
            logger.info("Ristorante chiuso");
        }, 30, TimeUnit.SECONDS);

        scheduler.shutdown();
    }
}
