package ReaderWriter;

import java.util.concurrent.Semaphore;

public class VariablesGlob {
    static int readerCount = 0;
    static Semaphore serviceQueue = new Semaphore(1);
    static Semaphore rmutex = new Semaphore(1);
    static Semaphore resource = new Semaphore(1);
}
