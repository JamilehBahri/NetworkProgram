package ir.phgint;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Ready implements ClientState {

    private boolean isRunning = true;

    @Override
    public boolean handle(ContextClient context) {

        Reader consoleReader = new InputStreamReader(System.in);
        System.out.print("Please enter a message : ");
        char[] chars = new char[1024];
        try {
            while (isRunning) {
                if (consoleReader.ready()) {
                    int count = consoleReader.read(chars);
                    String str = (String.valueOf(chars , 0, count));
                    if ( str.substring(0,str.length()-1).equalsIgnoreCase("bye"))
                        break;
                    context.getNetClient().WriteData(str);
                }
                String recivedData = context.getNetClient().ReadData();
                if (recivedData != null) {
                    System.out.println("\nrecived data :" + recivedData);
                    System.out.print("Please enter a message : ");
                }
                Thread.sleep(500);
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
