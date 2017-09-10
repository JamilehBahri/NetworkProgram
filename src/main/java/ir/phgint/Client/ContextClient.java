package ir.phgint.Client;


public class ContextClient {

    private ConnectingState connecting = new ConnectingState();
    private DisconnectState disconnect = new DisconnectState();
    private ReadyState ready = new ReadyState();
    private boolean isRunning = true;

    private NetClient netClient;
    private ClientState currentState = connecting;

    public ContextClient(NetClient netClient) {
        this.netClient = netClient;
    }

    public NetClient getNetClient() {
        return netClient;
    }

    public ClientState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ClientState currentState) {
        this.currentState = currentState;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void startContext() {
        while (isRunning) {
            switchState(currentState.handle(this));
        }
    }

    public void switchState(boolean actionResult) {

        if (currentState == connecting) {
            if(actionResult) {
                currentState = ready;
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (currentState == ready) {
                currentState = disconnect;
        } else {
                currentState = connecting;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
