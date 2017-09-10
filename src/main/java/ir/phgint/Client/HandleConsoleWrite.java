package ir.phgint.Client;

//read from socketReader then send data to write console

public class HandleConsoleWrite extends Thread {

    private boolean isRunning = true;
    private ContextClient context;

    public HandleConsoleWrite(ContextClient context) {
        this.context = context;
    }
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        while (isRunning) {
            try {
                String receivedData = context.getNetClient().ReadData();
                System.out.println("\nrecived data :" + receivedData);
                System.out.print("Please enter a message : ");
            } catch (NetClientExcption excption) {
                break;
            }
        }
    }
}