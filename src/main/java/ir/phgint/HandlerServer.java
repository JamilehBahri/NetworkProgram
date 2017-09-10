package ir.phgint;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
//subject
public class HandlerServer extends Thread {

    public Collection<Socket> getSocketList() {
        return socketList;
    }

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

        try {
            // Create a buffered reader and writer for teh socket
            byte[] buffer = new byte[1024];
            InputStream is = socket.getInputStream();
            is.read(buffer, 0, buffer.length);
            String s = new String(buffer);
            System.out.println("Received from client: " + s);
            // Echo the received message to the client   //broad cast
            Iterator<Socket> it = socketList.iterator();
            while (it.hasNext()) {
                Socket item = it.next();
                if (item.isConnected()) {
                    if (item != socket) {
                        item.getOutputStream().write(buffer);
                    }
                } else {
                    removeSocket(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
