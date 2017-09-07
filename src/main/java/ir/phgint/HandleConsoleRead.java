package ir.phgint;

import java.io.*;

// Read from console  then write Console(data) to socketWriter
public class HandleConsoleRead extends Thread {
    private BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    private boolean isRunning = true;
    private ContextClient context;

    public HandleConsoleRead(ContextClient context) {
        this.context = context;
    }

    public void run() {
        while (isRunning) {
            try {
                String outMsg = consoleReader.readLine();
                if (outMsg.equalsIgnoreCase("bye")) {
                    context.getNetClient().Disconnect();
                    break;
                }
                if (outMsg != null)
                    System.out.print("Please enter a message : ");
                context.getNetClient().WriteData(outMsg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}