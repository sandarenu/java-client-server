package test;


import java.io.*;
import java.net.*;

public class SimpleClient {

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 5000; i++) {
            final String cId = "C-" + i;
            Thread t = new Thread(() -> clientSender(cId));
            t.setDaemon(false);
            t.setName("client-" + i);
            t.start();
        }

        System.out.println("================ Client Finishes ===================");

        System.in.read();
    }

    private static void clientSender(String clientId) {
        LogPrinter.logMsg("Client Started", clientId);

        String hostName = "127.0.0.1";
        int portNumber = 20001;

        try {
            Socket kkSocket = new Socket(hostName, portNumber);
//            kkSocket.setSoTimeout(4000);
            try (
                    PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            ) {

                for (int i = 0; i < 1; i++) {
                    out.println("Test " + clientId + i);
                    String fromServer = in.readLine();
                    LogPrinter.logMsg(clientId, "Server reply: ", fromServer);
                }
            } catch (Exception e) {
                LogPrinter.logMsg(clientId, "Couldn't get I/O for the connection to ", e.getMessage());
                System.exit(1);
            }
        } catch (Exception ex) {
            LogPrinter.logMsg(clientId, "Couldn't get I/O for the connection to ", ex.getMessage());
        }

        LogPrinter.logMsg("Client finished", clientId);
    }
}
