package ir.phgint.Server;

import java.net.ServerSocket;

public interface Observer {

    String read ();
    void write(String str);
}
