import java.util.concurrent.ThreadLocalRandom;

public class Passenger implements Runnable {
    int passId;
    Elevator elev;

    public Passenger(Elevator elev, int id) {
        passId = id;
        this.elev = elev;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1, 5) * 1000);
                elev.enter();
                elev.passListSemaphore.acquire();
                elev.passengersList.add(this);
                elev.passListSemaphore.release();
                Thread.sleep(ThreadLocalRandom.current().nextInt(3, 7) * 1000);
                elev.exit();
                elev.passListSemaphore.acquire();
                elev.passengersList.remove(this);
                elev.passListSemaphore.release();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
