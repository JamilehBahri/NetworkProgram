package ir.phgint;


import java.io.*;
import java.net.Socket;

public class HandleConsoleRead extends Thread {
    BufferedWriter socketWriter = null;
    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    private boolean isRunning = true;
    private Socket socket;

    public HandleConsoleRead(Socket socket) {
        this.socket = socket;

    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        try {
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        while (isRunning) {
            try {
                String outMsg = consoleReader.readLine();
                if (outMsg.equalsIgnoreCase("bye")) {
                    isRunning = false;
                }
                socketWriter.write(outMsg);
                socketWriter.write("\n");
                socketWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
