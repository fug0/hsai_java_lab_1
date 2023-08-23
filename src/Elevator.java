import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Elevator implements Runnable {
    int maxCapacity;
    List<Passenger> passengersList;
    Semaphore elevSemaphore;
    Semaphore passListSemaphore;


    public Elevator(int val) {
        maxCapacity = val;
        elevSemaphore = new Semaphore(val);
        passListSemaphore = new Semaphore(1);
        passengersList = new ArrayList<Passenger>(val);
    }

    @Override
    public void run() {
        try {
            while(true) {
                System.out.println("Current passengers in the elevator");
                passListSemaphore.acquire();
                for (int i = 0; i < passengersList.size(); i++)
                    System.out.println(passengersList.get(i).passId);
                passListSemaphore.release();

                System.out.println();
                System.out.println();

                Thread.sleep(1000);
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public void enter() throws InterruptedException {
        elevSemaphore.acquire();
    }

    public void exit() {
        elevSemaphore.release();
    }
}