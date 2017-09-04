package ir.phgint;


public interface NetClient {

     boolean Connecting(String address , int port);
     boolean ReadData(String data);
     String WriteData();
     boolean Disconnect();
}
