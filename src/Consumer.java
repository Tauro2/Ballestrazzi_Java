public class Consumer implements Runnable {

    private final Box box;

    public Consumer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {   // SOLO 5 VOLTE
            try {
                box.full.acquire();
                box.mutex.acquire();

                int value = box.queue.poll(); // estrazione FIFO
                System.out.println("Consumer consuma: " + value);

                box.mutex.release();
                box.empty.release();

                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
