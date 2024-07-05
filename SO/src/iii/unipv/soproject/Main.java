package iii.unipv.soproject;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static int NUM_CLIENTI_INIZIALE=5;
    private static int NUM_CLIENTI_FINALE=10;
    
    public static void main(String[] args) {
        BlockingQueue<Ordine> ordini = new LinkedBlockingQueue<>();
        BlockingQueue<Ordine> ordiniDaPreparare = new LinkedBlockingQueue<>();

        ExecutorService executor = Executors.newFixedThreadPool(7);
        executor.execute(new Cucina(ordiniDaPreparare));
        executor.execute(new Cameriere(ordini, ordiniDaPreparare));

        // Arrivo iniziale di 5 clienti
        for (int i = 1; i <= NUM_CLIENTI_INIZIALE; i++) {
            executor.execute(new Cliente(i, ordini));
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Arrivo di altri 5 clienti a intervalli di tempo successivi
        for (int i = NUM_CLIENTI_INIZIALE+1; i <= NUM_CLIENTI_FINALE; i++) {
            int idCliente = i;
            scheduler.schedule(() -> {
                executor.execute(new Cliente(idCliente, ordini));
            }, ThreadLocalRandom.current().nextInt(10, 30), TimeUnit.SECONDS);
        }

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
        }, 60, TimeUnit.SECONDS);

        scheduler.shutdown();
    }
}
