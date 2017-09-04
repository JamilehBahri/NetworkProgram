package ir.phgint;


public class Disconnect implements ClientState {

    @Override
    public boolean handle(ContextClient context) {
        if (context.getNetClient().Disconnect())
            return true;
        return false;
    }
}
