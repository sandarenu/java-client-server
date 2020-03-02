package test;

public class RequestCordinator {

    private final ItemStoreImpl socketStore;

    public RequestCordinator(ItemStoreImpl socketStore) {
        this.socketStore = socketStore;
    }

    public void start(){
        for( int i = 0; i<  10 ; i++){
            RequestProcessor rp = new RequestProcessor(i, socketStore);
        }
        System.out.println("Processor started");
    }
}
