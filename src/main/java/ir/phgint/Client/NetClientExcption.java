package ir.phgint.Client;

import java.net.SocketException;

public class NetClientExcption extends Exception {

    public  NetClientExcption(Exception exp){
        super(exp);
    }

}
