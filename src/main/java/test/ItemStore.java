package test;

import java.net.Socket;
import java.util.Optional;

public interface ItemStore<T> {

    void newRequestReceived(T socket);
    T getRequest() throws InterruptedException;
}
