package ir.phgint;

import java.io.*;
import java.net.Socket;

public class TcpClient implements NetClient {

    private Socket socket;

    @Override
    public boolean Connecting(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Started client socket at " + socket.getLocalSocketAddress());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("client socket not Connecting ... ");
        return false;
    }

    @Override
    //write Console(data) to socketWriter
    public void WriteData(String data) {

        Writer socketWriter = null;
        try {
            socketWriter = new OutputStreamWriter(socket.getOutputStream());
            socketWriter.write(data);
            socketWriter.write("\n");
            socketWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    //read from socketReader then send data to write console
    public String ReadData() {
        InputStreamReader socketReader = null;

        try {
            socketReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(socketReader);
            return bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean Disconnect() {
        try {
            socket.close();
            System.out.println("Closed client socket at " + socket.getLocalSocketAddress());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
