import java.util.Random;

public class Producer implements Runnable {

    private final Box box;
    private final Random random = new Random();

    public Producer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {   // SOLO 5 VOLTE
            try {
                int value = random.nextInt(100); // numero casuale

                box.empty.acquire();
                box.mutex.acquire();

                box.queue.add(value);   // inserimento FIFO
                System.out.println("Producer produce: " + value);

                box.mutex.release();
                box.full.release();

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
