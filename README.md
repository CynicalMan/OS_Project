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
        wait(resource);             // request resource access for readers (writers blocked)
    signal(serviceQueue);           // let next in line be serviced
    signal(rmutex);                 // release access to readcount
    
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












