package ir.phgint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HandleConsoleWrite extends Thread {
    int len;
    boolean isRunning = true;
    BufferedReader socketReader = null;
    private Socket socket;

    public HandleConsoleWrite(Socket socket) {
        this.socket = socket;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        try {
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        while (isRunning) {
            try {
                String contentLine = socketReader.readLine();
                if (contentLine.equals("bye")) {
                    isRunning = false;
                }
                System.out.println("\nrecived data :" + contentLine);
                System.out.print("Please enter a message : ");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
