package BinaryFileManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderManager implements Runnable {

    private String filepath;
    private Object res;

    public ReaderManager(String filepath) {
        this.filepath = filepath;
    }
    @Override
    public void run() {
        try {
            VariableGlobM.serviceQueue.acquire();
            VariableGlobM.rmutex.acquire();
            VariableGlobM.readerCount++;
            if (VariableGlobM.readerCount == 1) {
                VariableGlobM.resource.acquire();
            }
            VariableGlobM.rmutex.release();
           VariableGlobM.serviceQueue.release();
            System.out.println("Thread "+Thread.currentThread().getName() + " is READING");
            // Call Read Function
            this.res = this.read(filepath);
            System.out.println("Thread "+Thread.currentThread().getName() + " has finished READING");
            VariableGlobM.rmutex.acquire();
            VariableGlobM.readerCount--;
            if (VariableGlobM.readerCount == 0) {
                VariableGlobM.resource.release();
            }
            VariableGlobM.rmutex.release();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public Object read (String FilePath) {

        Object Result = null;

        try {
            System.out.println("\nReading from : " + FilePath);
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FilePath));
            Result = reader.readObject();
            reader.close();
        }catch (IOException e){
            System.out.println("\ncan't read : " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }

    public Object getRes() {
        return res;
    }
}
