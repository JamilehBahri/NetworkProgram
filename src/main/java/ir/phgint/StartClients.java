package ir.phgint;

public class StartClients {

    public static void main(String[] args) {
        NetClient netClient = new TcpClient();
        ContextClient context = new ContextClient(netClient);

        boolean isRunning = true;
        while (isRunning) {
            context.switchState(context);
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            System.out.println("Clients can not switchState ");
//            isRunning = false;
//        }
    }

}
