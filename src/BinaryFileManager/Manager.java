package BinaryFileManager;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Manager  {
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
}
