package ir.phgint;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SingleThread extends Thread {

    Collection<Socket> socketList = new ArrayList<Socket>();
    private boolean isRunning = true;

    void addSocket(Socket socket) {
       socketList.add(socket);
    }

    void removeSocket(Socket socket){
        socketList.remove(socket);
    }

//    public ArrayList<Socket> getSocketList() {
//        return socketList;
//    }

    public void setSocketList(List<Socket> socketList) {
        this.socketList = socketList;
    }

    public void run(){
        while(isRunning){
            for(Socket socket:socketList) {

                handleClientRequest(socket);

            }
        }
    }

    public void handleClientRequest(Socket socket) {
        BufferedReader socketReader = null;
        BufferedWriter socketWriter = null;
        try {
// Create a buffered reader and writer for teh socket
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String inMsg = null;
            while ((inMsg = socketReader.readLine()) != null) {
                System.out.println("Received from client: " + inMsg);
// Echo the received message to the client
                for(Socket socketGuest:socketList){
                    if(socketGuest != socket){
                        String outMsg = inMsg;
                        socketWriter.write(outMsg);
                        socketWriter.write("\n");
                        socketWriter.flush();
                    }
                    else{
                        continue;
                    }
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
