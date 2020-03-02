package test;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ItemStoreImpl<T> implements ItemStore<T> {
    private LinkedList<T> itemList = new LinkedList<>();

    public void newRequestReceived(T value) {
        System.out.println("New request: " + value);

        synchronized (itemList) {
            itemList.offer(value);
            itemList.notifyAll();
        }
    }

    public T getRequest() throws InterruptedException {
        synchronized (itemList) {
            do {
                if (itemList.size() > 0) {
                    return itemList.remove();
                }
                itemList.wait();
                System.out.println("Notified....");
            } while (itemList.size() == 0);
            return itemList.remove();
        }
    }

}
