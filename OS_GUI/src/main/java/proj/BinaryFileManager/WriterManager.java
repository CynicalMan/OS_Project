package proj.BinaryFileManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriterManager implements Runnable {

    private String filepath;
    private Object data;
    private boolean isWritten;

    public WriterManager(String filepath, Object data){
        this.filepath = filepath;
        this.data = data;
    }
    @Override
    public void run() {
        try {
            VariableGlobM.serviceQueue.acquire();
            VariableGlobM.resource.acquire();
            System.out.println("Thread "+Thread.currentThread().getName() + " is WRITING");
            // Call Write Function
            this.isWritten = this.write(filepath,data);
            // Call Write Function
            System.out.println("Thread "+Thread.currentThread().getName() + " has finished WRITING");
            VariableGlobM.resource.release();
            VariableGlobM.serviceQueue.release();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean write (String FilePath, Object data) {
        try {
            // CRITICAL SECTION
            System.out.println("\nWriting in : " + FilePath);
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(FilePath));
            writer.writeObject(data);
            System.out.println("  Done!");
            writer.close();
            // CRITICAL SECTION

            return true;
        }catch (IOException e){
            System.out.println("\ncan't write : " + e);
        }
        return false;
    }

    public boolean isWritten() {
        return isWritten;
    }
}
