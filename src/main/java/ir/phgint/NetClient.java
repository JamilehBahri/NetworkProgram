package ir.phgint;


public interface NetClient {

     boolean Connecting(String address , int port);
     void WriteData(String data);
     String ReadData();
     boolean Disconnect();
}
