package ReaderWriter;

import ReaderWriter.VariablesGlob;

class Writer implements Runnable {
    @Override
    public void run() {
        try {
            VariablesGlob.serviceQueue.acquire();
            VariablesGlob.resource.acquire();
            System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");
            Thread.sleep(2500);
            System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
            VariablesGlob.serviceQueue.release();
            VariablesGlob.resource.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
