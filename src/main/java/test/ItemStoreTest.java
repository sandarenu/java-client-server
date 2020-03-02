package test;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ItemStoreTest {

    public static void main(String[] args) throws IOException {

//        LogPrinter.logMsg("Waitting....", "");
//        System.in.read();
//
//
//        ItemStore<Integer> itemStore = new ItemStoreImpl<>();
//        int maxSuppliers = 10;
//        int maxItemsToInsert = 1000;
//        IntStream.range(0, maxSuppliers)
//                .forEach(supplierNo -> {
//                    Thread supplier = new Thread(() -> {
//                        for (int insertNo = 0; insertNo < maxItemsToInsert; insertNo++) {
//                            itemStore.newRequestReceived(insertNo);
//                        }
//                        LogPrinter.logMsg("Inserted ", String.valueOf(maxItemsToInsert));
//                    });
//                    supplier.setName("Supplier: " + supplierNo);
//                    supplier.start();
//                });
//
//
//        // Threads upto maxConsumers will spawn and attempt to peek all of the items
//        int maxConsumers = 10;
//        int totalExpectedItems = maxItemsToInsert * maxSuppliers;
//        IntStream.range(0, maxConsumers).forEach(consumer -> {
//            Thread aConsumer = new Thread(() -> {
//
//                    Integer count = IntStream.range(0, totalExpectedItems)
//                            .mapToObj(ignored -> itemStore.getRequest()
//                                    .map(ignored2 -> 1)
//                                    .orElse(0))
//                            .reduce(0, Integer::sum);
//                    LogPrinter.logMsg("Counted items: ", String.valueOf(count));
//
//            });
//            aConsumer.setName("Consumer: " + consumer);
//            aConsumer.start();
//        });
//
//        System.in.read();

    }

}
