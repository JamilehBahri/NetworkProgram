package ir.phgint.Server;

import ir.phgint.Client.ContextClient;
import ir.phgint.Client.NetClient;
import ir.phgint.Client.TcpClient;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerStart {

    public static void main(String[] args) {

        SubjectHandler subjectHandler = new SubjectHandler();
        subjectHandler.start();
        ObserverHandler observerHandler = null;
        try {
            ServerSocket serverSocket = new ServerSocket(1100, 100, InetAddress.getByName("localhost"));
            System.out.println("Server started at: " + serverSocket);
            while (true) {
                System.out.println("Waiting for a connection...");
                final Socket activeSocket = serverSocket.accept();
                observerHandler = new ObserverHandler(activeSocket, subjectHandler);
                subjectHandler.setObserversCollection(observerHandler);
                observerHandler.start();
                System.out.println("Received a connection from " + activeSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
