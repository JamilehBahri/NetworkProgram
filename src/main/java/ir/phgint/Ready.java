package ir.phgint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Ready implements ClientState {

    private boolean isRunning = true;

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
