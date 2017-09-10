package ir.phgint;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart {

    public static void main(String[] args) {

        SubjectHandler subjectHandler = new SubjectHandler();
        subjectHandler.start();
        try {
            ServerSocket serverSocket = new ServerSocket(1100, 100, InetAddress.getByName("localhost"));
            System.out.println("Server started at: " + serverSocket);
            while (true) {
                System.out.println("Waiting for a connection...");
                final Socket activeSocket = serverSocket.accept();

                ObserverHandler observerHandler = new ObserverHandler(activeSocket,subjectHandler);
                subjectHandler.setObservers(observerHandler);
                observerHandler.start();
                System.out.println("Received a connection from " + activeSocket);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
