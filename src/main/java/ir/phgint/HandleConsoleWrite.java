package ir.phgint;

//read from socketReader then send data to write console

import java.io.*;

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
                String  receivedData = context.getNetClient().ReadData();
                System.out.println("\nrecived data :" + receivedData);
                System.out.print("Please enter a message : ");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}