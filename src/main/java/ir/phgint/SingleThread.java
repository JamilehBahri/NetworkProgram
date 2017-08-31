package ir.phgint;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SingleThread extends Thread {

    Collection<Socket> socketList = new ArrayList<Socket>();
    private boolean isRunning = true;

    void addSocket(Socket socket) {
        socketList.add(socket);
    }

    void removeSocket(Socket socket) {
        socketList.remove(socket);
    }

    public void setSocketList(List<Socket> socketList) {
        this.socketList = socketList;
    }

    public void run() {
        while (isRunning) {
            for (Socket socket : socketList) {
                try {
                    if (socket.getInputStream().available() != 0) {
                        handleClientRequest(socket);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleClientRequest(Socket socket) {
        BufferedReader socketReader = null;
        BufferedWriter socketWriter = null;
        try {
            // Create a buffered reader and writer for teh socket
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            byte[] buffer = new byte[1024];
            InputStream is = socket.getInputStream();
            is.read(buffer, 0, buffer.length);
            String s = new String(buffer);
            System.out.println("Received from client: " + s);
            // Echo the received message to the client   //broad cast
            for (Socket socketGuest : socketList) {
                if (socketGuest != socket) {
                socketGuest.getOutputStream().write(buffer);
//                    socketWriter.write(bytesRead);
//                    socketWriter.write("\n");
//                    socketWriter.flush();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
