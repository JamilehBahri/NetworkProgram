package ir.phgint;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Ready implements ClientState {

    private boolean isRunning = true;
    private  boolean readDataRes = false;

    @Override
    public boolean handle(ContextClient context) {

        Reader consoleReader = new InputStreamReader(System.in);
        System.out.print("Please enter a message : ");
        char[] chars = new char[1024];
//        while (isRunning) {
            try {
//                if (consoleReader.ready()) {
                    consoleReader.read(chars);
                    readDataRes = context.getNetClient().ReadData(String.valueOf(chars));
//                }
                if (readDataRes) {
                    context.getNetClient().WriteData();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
//        }
//        return false;
    }
}
