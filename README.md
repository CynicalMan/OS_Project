# Readers and Writers Problem

## Introduction

This problem occurs when many threads of execution try to access the same shared resources at a time. There are N readers to read data and K Writers to write data to shared resources.

## Pseudocode Solution

This pseudocode solution solves all the possible deadlocks and starvation problems that could arise from the Reader-Writer problem. A solution that uses a queue for fairness for both readers and writers will be as follows:

```pseudo

int readcount;                // init to 0; number of readers currently accessing resource

// all semaphores initialised to 1
semaphore resource;           // controls access (read/write) to the resource. Binary semaphore.
semaphore rmutex;             // for syncing changes to shared variable readcount
semaphore serviceQueue;       // FAIRNESS: preserves ordering of requests (signaling must be FIFO)

//READER
reader() {
    <ENTRY Section>
    wait(serviceQueue);           // wait in line to be serviced
    wait(rmutex);                 // request exclusive access to readcount
    readcount++;                // update count of active readers
    if (readcount == 1)         // if I am the first reader
        wait(resource);   
    signal(rmutex);                           // request resource access for readers (writers blocked)
    signal(serviceQueue);           // let next in line be serviced
    // release access to readcount
    
    <CRITICAL Section>
    //reading is performed
    
    <EXIT Section>
    wait(rmutex);                 // request exclusive access to readcount
    readcount--;                // update count of active readers
    if (readcount == 0)         // if there are no readers left
      signal(resource);              // release resource access for all
    signal(rmutex);                // release access to readcount
}

//WRITER
writer() {
    <ENTRY Section>
      wait(serviceQueue);           // wait in line to be serviced
      wait(resource);                // request exclusive access to resource
      signal(serviceQueue);           // let next in line be serviced

    <CRITICAL Section>
    // writing is performed

    <EXIT Section>
      signal(resource);             // release resource access for next reader/writer
      signal(serviceQueue);           // let next in line be serviced
}
```
## Deadlocks in the Readers-Writers Problem:

### Examples of deadlocks

 * Two or more writers write to the same resource at the same time.

 * Reader and writer access the same resource at the same time.

### Solution for the deadlocks

The deadlocks are solved using an exclusive lock for the resource (Semaphore).

## Starvation in the Readers-Writers Problem:

### Examples of starvation

 * In the first variation of the reader-writer problem where no reader kept waiting unless the writer has permission to use a shared object therefore writer will starve.

 * In the second variation of the reader-writer problem where once a writer is ready, it performs the write. In other words, if a writer is waiting to access the object, no new readers may start reading therefore the reader will starve.

### Solution for starvation

The starvation is solved using a queue that preserves ordering of requests (signaling must be FIFO)

# Real-World Application : University Grading System 

## Introduction

It is a System that display the grades of the Students and Enable the Lecturer to display the students of their course, search a student and his grade, and update the grade of the student's grade

## Reader-Writer in University Grading System

Applied the problem in the Binary File Managers : 

* ReaderManager
* WriterManager

## ReaderManager.java

```java

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

```

## WriterManager.java

```java

package BinaryFileManager;

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

```

* Note : VariableGlobM is a class that includes a colllection of static variables imported in both ReaderManager.java and WriterManager.java

```java
package BinaryFileManager;

import java.util.concurrent.Semaphore;

public class VariableGlobM {
    static int readerCount = 0;
    static int sm = 0;
    static Semaphore serviceQueue = new Semaphore(1);
    static Semaphore rmutex = new Semaphore(1);
    static Semaphore resource = new Semaphore(1);
}

```










