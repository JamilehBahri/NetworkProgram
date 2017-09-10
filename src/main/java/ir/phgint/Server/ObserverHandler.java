package ir.phgint.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ObserverHandler extends Thread implements Observer {

    private Socket socket = null;
    private boolean isRunning = true;
    private SubjectHandler subject;
    private BufferedReader bufferedReader;
    List<Socket> socketList = new ArrayList<>();

    public ObserverHandler(Socket socket, SubjectHandler subject) {
        this.socket = socket;
        this.subject = subject;
        socketList.add(socket);

    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        while (isRunning) {
            String readData = read();
            subject.setMessage(readData, this);
            String data = readData.substring(0, readData.trim().length());
            if (data.equalsIgnoreCase("bye")) {
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

