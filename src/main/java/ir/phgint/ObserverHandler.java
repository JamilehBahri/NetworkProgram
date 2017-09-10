package ir.phgint;

import java.io.*;
import java.net.Socket;

public class ObserverHandler extends Thread implements Observer {

    private Socket socket = null;
    private boolean isRunning = true;
    private SubjectHandler subject;
    private BufferedReader bufferedReader;


    public ObserverHandler(Socket socket, SubjectHandler subject) {
        this.socket = socket;
        this.subject = subject;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        while (isRunning) {
            String readData = read();
            subject.setMessage(readData, this);
            if (!socket.isConnected()) {
                isRunning = false;
                subject.remove(this);
            }
        }
    }

    @Override
    public String read() {
        try {
            char[] buffer = new char[1024];
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedReader.read(buffer, 0, buffer.length);
            String s = new String(buffer);
            System.out.println("Received from client: " + s);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(String str) {
        try {
            socket.getOutputStream().write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
