package ir.phgint;

import java.io.*;
import java.net.Socket;

public class TcpClient implements NetClient {

    private Socket socket;

    @Override
    public boolean Connecting(String address, int port) {
        try {
            socket = new Socket(address, port);
            if (socket != null) {
                System.out.println("Started client socket at " + socket.getLocalSocketAddress());
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("client socket not Connecting ... ");
        return false;
    }

    @Override
    public boolean ReadData(String data) {

        BufferedWriter socketWriter = null;
        try {
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            socketWriter.write(data);
            socketWriter.flush();

            return true;
            } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(" Please Enter a Message : ");

        return false;
    }

    @Override
    public String WriteData() {
        BufferedReader socketReader = null;
        int len;
        try {
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            if ((len= socket.getInputStream().available()) == 0) {
                char[] buffer = new char[1024];
                socketReader.read(buffer, 0, 1024);
                System.out.println("recived data :" + String.valueOf(buffer));
//                System.out.print("Please enter a message : ");
                return String.valueOf(buffer);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean Disconnect() {

        if (socket != null) {
            try {
                socket.close();
                System.out.println("Closed client socket at " + socket.getLocalSocketAddress());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
