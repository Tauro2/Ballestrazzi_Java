public class Consumer implements Runnable {

    private final Box box;

    public Consumer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            try {
                box.full.acquire();     //Attende che ci sia almeno un elemento nel buffer.
                                        //Se la coda è vuota, il thread si blocca.

                box.mutex.acquire();    //Entra nella sezione critica.

                int value = box.queue.poll(); //Estrae e rimuove il primo elemento della coda (FIFO).
                System.out.println("Consumer consuma: " + value);

                box.mutex.release();        //Esce dalla sezione critica.
                box.empty.release();        //Segnala che si è liberato uno spazio nel buffer.

                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
