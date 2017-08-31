package ir.phgint;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader socketReader = null;
        BufferedWriter socketWriter = null;
        boolean isRunning = true;

        try {
            // Create a socket that will connect to localhost
            socket = new Socket("localhost", 1100);
            System.out.println("Started client socket at " + socket.getLocalSocketAddress());
            // Create a buffered reader and writer using the socket's input and output streams
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // Create a buffered reader for user's input
//          BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            Reader consoleReader = new InputStreamReader(System.in);

            System.out.print("Please enter a message : ");
            char[] chars = new char[1024];

            while (isRunning) {

                if (consoleReader.ready()) {
                    consoleReader.read(chars);
                    socketWriter.write(chars);
                    socketWriter.flush();

                }
                int len;

                if ((len= socket.getInputStream().available()) != 0) {
                    char[] buffer = new char[len];
                    socketReader.read(buffer, 0, len);
                    System.out.println("recived data :" + String.valueOf(buffer));
                    System.out.print("Please enter a message : ");

                }


                Thread.sleep(1000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
