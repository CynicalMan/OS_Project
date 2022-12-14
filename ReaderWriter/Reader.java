package ReaderWriter;

import ReaderWriter.VariablesGlob;

class Reader implements Runnable {
    @Override
    public void run() {
        try {
            VariablesGlob.serviceQueue.acquire();
            VariablesGlob.rmutex.acquire();
            VariablesGlob.readerCount++;
            if (VariablesGlob.readerCount == 1) {
                VariablesGlob.resource.acquire();
            }
            VariablesGlob.serviceQueue.release();
            VariablesGlob.rmutex.release();
            System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
            Thread.sleep(1500);
            System.out.println("Thread "+Thread.currentThread().getName() + " has finished READING");
            VariablesGlob.rmutex.acquire();
            VariablesGlob.readerCount--;
            if (VariablesGlob.readerCount == 0) {
                VariablesGlob.resource.release();
            }
            VariablesGlob.rmutex.release();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
