package ir.phgint;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        HandleServer singleThread = new HandleServer();
        singleThread.start();
        try {
            ServerSocket serverSocket = new ServerSocket(1100, 100, InetAddress.getByName("localhost"));
            System.out.println("Server started at: " + serverSocket);
            while (true) {
                System.out.println("Waiting for a connection...");
                final Socket activeSocket = serverSocket.accept();
                singleThread.addSocket(activeSocket);
                System.out.println("Received a connection from " + activeSocket);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
