import java.util.Random;

public class Producer implements Runnable {

    private final Box box;  // Riferimento all'oggetto condiviso Box.
                            // Contiene la coda e i semafori usati per la sincronizzazione.

    private final Random random = new Random();

    public Producer(Box box) {
        this.box = box;     // Riceve il Box condiviso e lo assegna al produttore.
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            try {
                int value = random.nextInt(100); // numero casuale

                box.empty.acquire();  // Attende che ci sia almeno uno spazio libero nel buffer.
                                      // Se il buffer è pieno, il thread si blocca.
                box.mutex.acquire();  //Entra nella sezione critica.

                box.queue.add(value);   //Inserimento FIFO in fondo alla coda
                System.out.println("Producer produce: " + value);

                box.mutex.release();    //Esce dalla sezione critica.
                box.full.release();     //Segnala un nuovo elemento disponibile nel buffer.

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
