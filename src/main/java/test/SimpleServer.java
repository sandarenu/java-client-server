package test;


import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        LogPrinter.logMsg("Server Started", "");
        int portNumber = 20001;
        ServerSocket serverSocket = new ServerSocket(portNumber);

        ItemStoreImpl sockerStore = new ItemStoreImpl();
        RequestCordinator rc = new RequestCordinator(sockerStore);
        rc.start();

        while (true) {
            final Socket clientSocket = serverSocket.accept();
            sockerStore.newRequestReceived(clientSocket);
//            Thread t = new Thread(() -> processRequest(clientSocket));
//            t.start();
        }
    }

    private static void processRequest(Socket clientSocket) {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

            String clientPort = String.valueOf(clientSocket.getPort());
            LogPrinter.logMsg(clientPort, "Client connected", clientPort);

            String inputLine = in.readLine();

            if (inputLine != null) {
                LogPrinter.logMsg(clientPort, "Request received", inputLine);
                String outputLine = "Reply from server :" + inputLine.toUpperCase();
                out.println(outputLine);
                TimeUnit.SECONDS.sleep(5);
                LogPrinter.logMsg(clientPort, "Reply sent", outputLine);
            }
            LogPrinter.logMsg(clientPort, "Finish", "");
        } catch (Exception e) {
            System.out.println("Exception caught when trying to listen on port or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
