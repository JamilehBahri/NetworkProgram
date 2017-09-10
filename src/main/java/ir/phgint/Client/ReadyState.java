package ir.phgint.Client;

public class ReadyState implements ClientState {


    @Override
    public boolean handle(ContextClient context) {

        try {
            System.out.print("Please enter a message : ");

            HandleConsoleRead handleConsoleRead = new HandleConsoleRead(context);
            HandleConsoleWrite handleConsoleWrite = new HandleConsoleWrite(context);

            handleConsoleRead.start();
            handleConsoleWrite.start();

            handleConsoleRead.join(0);
            handleConsoleWrite.setIsRunning(false);
            handleConsoleWrite.join(0);

            return true;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;

    }
}
