package ir.phgint.Client;


public interface NetClient {

     boolean Connecting(String address , int port);
     void WriteData(String data) throws NetClientExcption ;
     String ReadData()throws NetClientExcption ;
     boolean Disconnect();
     boolean isConnect();

}
