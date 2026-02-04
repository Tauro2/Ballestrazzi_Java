import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Box {

    public static final int DIM = 5;

    Queue<Integer> queue = new LinkedList<>(); // Coda condivisa che contiene gli elementi prodotti e consumati.
                                               // LinkedList implementa l'interfaccia Queue ed è usata come buffer FIFO.

    Semaphore empty = new Semaphore(DIM);
    Semaphore full = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);
}
