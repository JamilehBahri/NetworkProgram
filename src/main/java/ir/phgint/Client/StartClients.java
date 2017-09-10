package ir.phgint.Client;

public class StartClients {

    public static void main(String[] args) {
        NetClient netClient = new TcpClient();
        ContextClient context = new ContextClient(netClient);
        context.startContext();

    }


}
