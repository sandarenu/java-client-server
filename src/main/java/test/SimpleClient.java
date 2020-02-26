package test;


import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) throws IOException {

        LogPrinter.logMsg("Client Started", "");

        String hostName = "127.0.0.1";
        int portNumber = 20001;

        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));
        ) {
            String fromServer;
            String fromUser;

            for (int i = 0; i < 5; i++) {
                out.println("Test " + i);
                fromServer = in.readLine();
                LogPrinter.logMsg("Server reply: ", fromServer);
            }

            out.println("Bye.");
            out.println("Bye.");

//            while ((fromServer = in.readLine()) != null) {
//                LogPrinter.logMsg("Server: " , fromServer);
//
//                if (fromServer.equals("Bye."))
//                    break;
//
//                out.println("Test");
//
//            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
