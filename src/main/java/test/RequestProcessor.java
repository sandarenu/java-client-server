package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class RequestProcessor {
    private final Thread t;
    private final ItemStoreImpl<Socket> socketStore;

    public RequestProcessor(int tId, ItemStoreImpl socketStore) {
        this.socketStore = socketStore;
        t = new Thread(() -> process());
        t.setName("Processor-" + tId);
        t.start();
    }

    private void process() {
        while (true) {
            try {
                Socket socket = socketStore.getRequest();
                doProcess(socket);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

//            if (socketOpt.isPresent()) {
//                Socket socket = socketOpt.get();
//                doProcess(socket);
//            } else {
////                Thread.yield();
////                try {
////                    TimeUnit.SECONDS.sleep(1);
////                } catch (InterruptedException e) {
////                    Thread.currentThread().interrupt();
////                }
//                LogPrinter.logMsg("", "No sockets");
//            }
        }
    }

    private void doProcess(Socket socket) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

            String clientPort = String.valueOf(socket.getPort());
            LogPrinter.logMsg(clientPort, "Client connected", clientPort);

            String inputLine = in.readLine();

            if (inputLine != null) {
                LogPrinter.logMsg(clientPort, "Request received", inputLine);
                String outputLine = "Reply from server :" + inputLine.toUpperCase();
                out.println(outputLine);
//                        TimeUnit.SECONDS.sleep(5);
                LogPrinter.logMsg(clientPort, "Reply sent", outputLine);
            }
            LogPrinter.logMsg(clientPort, "Finish", "");
        } catch (Exception e) {
            System.out.println("Exception caught when trying to listen on port or listening for a connection");
            System.out.println(e.getMessage());
        }
    }


}