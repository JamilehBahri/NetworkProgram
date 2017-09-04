package ir.phgint;

public class Connecting implements ClientState {

    @Override
    public boolean handle(ContextClient context) {
        if (context.getNetClient().Connecting("localhost",1100))
            return true;
        return false;

    }
}
