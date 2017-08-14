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
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String outMsg = null;
            System.out.print("Please enter a message (Bye to quit):");
            while (isRunning) {
                if (socket.getInputStream().available() != 0) {

                    int bytesRead;
                    byte[] buffer = new byte[1024];
                    InputStream is = socket.getInputStream();
                    bytesRead = is.read(buffer, 0, buffer.length);
                    System.out.println(" from client: " + bytesRead);


                }
                else {
                    outMsg = consoleReader.readLine();
                    socketWriter.write(outMsg);
                    socketWriter.write("\n");
                    socketWriter.flush();
                    System.out.print("Please enter a message (Bye to quit):");
                }

            }
            Thread.sleep(1000);

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
