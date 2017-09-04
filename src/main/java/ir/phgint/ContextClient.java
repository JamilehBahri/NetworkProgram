package ir.phgint;


import java.util.List;

public class ContextClient {

   private  Connecting connecting = new Connecting();
   private  Disconnect disconnect = new Disconnect();
   private  Ready ready = new Ready();

    private NetClient netClient;
    private ClientState currentState = disconnect;

    public ContextClient(NetClient netClient) {
        this.netClient = netClient;
    }

    public NetClient getNetClient() {
        return netClient;
    }

    public ClientState getCurrentState() {
        return currentState;
    }

    public void switchState(ContextClient contetx) {

        if (currentState == connecting) {
            ready.handle(contetx);
            currentState = ready;
        } else if (currentState == ready) {
            disconnect.handle(contetx);
            currentState = disconnect;
        } else {
            connecting.handle(contetx);
            currentState = connecting;
        }

    }


}
