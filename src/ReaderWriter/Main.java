package ReaderWriter;



public class Main {
//    public static void main(String[] args) {
//        //Shared message object between Reader and Writer threads.
//        Message message = new Message();
//
//        Thread readerThread = new Thread(new Reader1(message));
//        Thread writerThread = new Thread(new Writer1(message));
//
//        readerThread.start();
//        writerThread.start();
//    }
    public static void main(String[] args) {
        Reader read = new Reader();
        Writer write = new Writer();
        Thread t1 = new Thread(read);
        t1.setName("thread1");
        Thread t2 = new Thread(write);
        t2.setName("thread2");
        Thread t3 = new Thread(read);
        t3.setName("thread3");
        Thread t4 = new Thread(write);
        t4.setName("thread4");
        Thread t5 = new Thread(read);
        t5.setName("thread5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}