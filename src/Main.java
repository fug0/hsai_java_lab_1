public class Main
{
    public static void main(String[] args) throws InterruptedException {
        Elevator mainElevator = new Elevator(Integer.parseInt(args[0]));
        Thread elevatorThread = new Thread(mainElevator);
        elevatorThread.start();

        for(int i = 0; i < Integer.parseInt(args[1]); i++) {
            Passenger p = new Passenger(mainElevator, 13 * i + i);
            Thread t = new Thread(p);
            t.start();
        }
    }
}
