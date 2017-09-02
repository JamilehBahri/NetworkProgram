package ir.phgint;

import java.io.*;
import java.net.Socket;

public class Client {


    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket("localhost", 1100);
            System.out.println("Started client socket at " + socket.getLocalSocketAddress());

            System.out.print("Please enter a message : ");
            HandleConsoleRead handleConsoleRead = new HandleConsoleRead(socket);
            HandleConsoleWrite handleConsoleWrite = new HandleConsoleWrite(socket);

            handleConsoleRead.start();
            handleConsoleWrite.start();

            handleConsoleRead.join(0);
            handleConsoleWrite.join(0);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        } finally {
//            if (socket != null) {
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }


}
