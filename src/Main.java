public class Main {

    public static void main(String[] args) {

        Box box = new Box();

        Thread p = new Thread(new Producer(box));
        Thread c = new Thread(new Consumer(box));

        p.start();
        c.start();
    }
}
