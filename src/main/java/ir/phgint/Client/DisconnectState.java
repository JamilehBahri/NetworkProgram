package ir.phgint.Client;


public class DisconnectState implements ClientState {

    @Override
    public boolean handle(ContextClient context) {
        if(context.getNetClient().isConnect()){
          return context.getNetClient().Disconnect();
        }
        return true;
    }
}
