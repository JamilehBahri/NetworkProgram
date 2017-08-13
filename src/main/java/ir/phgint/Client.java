package ir.phgint;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader socketReader = null;
        BufferedWriter socketWriter = null;
        try {
            // Create a socket that will connect to localhost
            socket = new Socket("localhost", 1100);
            System.out.println("Started client socket at " + socket.getLocalSocketAddress());
            // Create a buffered reader and writer using the socket's input and output streams
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // Create a buffered reader for user's input
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String outMsg = null;
            System.out.print("Please enter a message (Bye to quit):");
            while ((outMsg = consoleReader.readLine()) != null) {
                if (outMsg.equalsIgnoreCase("bye")) {
                    break;
                }
                // Add a new line to the message to the server
                socketWriter.write(outMsg);
                socketWriter.write("\n");
                socketWriter.flush();
                System.out.print("Please enter a message (Bye to quit):");

            }
        } catch (IOException e) {
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

    public static void readPm() {
//      int inMsg = socketReader.read();
        System.out.println("Server: ");

    }

}
