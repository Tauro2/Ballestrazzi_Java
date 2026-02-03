import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Box {

    public static final int DIM = 5;

    Queue<Integer> queue = new LinkedList<>();

    Semaphore empty = new Semaphore(DIM);
    Semaphore full = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);
}
