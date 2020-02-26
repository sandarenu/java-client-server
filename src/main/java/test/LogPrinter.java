package test;

public class LogPrinter {

    public static void logMsg(String src, String msg) {
        System.out.println("[" + System.currentTimeMillis() + "] [" + Thread.currentThread().getName() + "] [" +  src + "] [" + msg + "]");
    }

    public static void logMsg(String id, String src, String msg) {
        System.out.println("[" + System.currentTimeMillis() + "] [" + Thread.currentThread().getName() + "][" +  id + "] ["  +  src + "] [" + msg + "]");
    }
}
