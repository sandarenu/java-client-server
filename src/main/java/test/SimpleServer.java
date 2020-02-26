package test;


import java.net.*;
import java.io.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        LogPrinter.logMsg("Server Started", "");
        int portNumber = 20001;

        while (true) {
            try (
                    ServerSocket serverSocket = new ServerSocket(portNumber);
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out =
                            new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
            ) {

                String clientPort = String.valueOf(clientSocket.getPort());
                LogPrinter.logMsg(clientPort, "Client connected", clientPort);
                String inputLine, outputLine;


                while ((inputLine = in.readLine()) != null) {
                    LogPrinter.logMsg(clientPort, "Request received", inputLine);
                    outputLine = "Reply from server :" + inputLine.toUpperCase();
                    out.println(outputLine);
                    if (inputLine.equals("Bye.")) {
                        LogPrinter.logMsg(clientPort, "Server close", inputLine);
                        break;
                    }
                    LogPrinter.logMsg(clientPort, "Reply sent", outputLine);
                }
                LogPrinter.logMsg(clientPort, "Finish", "");
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }
}
