## Threads and Synchronization

This lab illustrates the problem of synchronization when many threads are operating on a shared object.  The general issue is called "thread safety", and it is a major cause of errors in computer software.

## Assignment

To the problems on the lab sheet and record your answers here.

1. Record average run times.
2. Write your explanation of the results.  Use good English and proper grammar.  Also use good Markdown formatting.

## ThreadCount run times

These are the average runtime using 3 or more runs of the application.
The Counter class is the object being shared by the threads.
The threads use the counter to add and subtract values.

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  |      10000000      |    0.020547    |
| Using ReentrantLock     |      10000000      |    1.0841820    |
| Syncronized method      |      10000000      |     0.612513    |
| AtomicLong for total    |      10000000      |     0.333880    |

## 1. Using unsynchronized counter object

1.1 Total is not be zero and total is not same result.
1.2 Average time is 0.00013926666
1.3 Count=Count+1; is consist of 3 operation in CPU is load change replace
if you run 2 thread 2 thread will do in same time and last value of Count is equal
thread that last replace that why not equal zero.
## 2. Implications for Multi-threaded Applications
In the real world Banking application that has a many work.
It could affect to user like this event that when user1 and user2 deposit 100 Baht at the same time and same account. User will lost 200 Baht but bank can be show deposit 100 baht because 2 threads have deposite at the same time.
 

## 3. Counter with ReentrantLock
3.1 Yes it always zero.Average runtime is 1.084182 sec.
3.2 Because total in 1 don't have method to lock thread like this.If you lock thread other thread can't do something until unlock.
3.3 Import ReentrantLock for use lock and unlock method in thread.
3.4 Because if thread end we will use unlock thread in finally.

## 4. Counter with synchronized method

4.1 Yes total is equal zero.Average runtime is 0.612513 sec.
4.2 Because total in 1 is unsynchronized and do 2 thread in same time and this problem is synchronized that will do by one method and when do synchronize method cPU can not do anything method.
4.3 Synchronize that like have auto lock or unlock that can't do any method while synchronize method is run.

## 5. Counter with AtomicLong
5.1 Because class AtomicLong have a atomic method that method will done first and fast
5.2 When you want to do many thread in same value and you will select atomic class type 
consistent of value type at same time.Because atomic class have a atomic method to operation value.

## 6. Analysis of Results
6.1 AtomicLong for total is fastest.Using ReentrantLock is slowest.
6.2 Syncronized method is the best when we have less than 4 thread and fast run time than Using ReentrantLock.and not specified like using AtomicLong for total.
Using ReentrantLock is the best when we have more than 4 thread or many thread and fast run time than Syncronized method.and not specified like using AtomicLong for total.

## 7. Using Many Threads (optional)
Unsynchronized counter average time is 0.064822 sec.
AtomicLong for total average time is 1.670204 sec.
Using ReentrantLock average time is 3.248360 sec.
Syncronized method average time is 4.288606 sec.
AtomicLong for total is fastest.
Syncronized method is slowest.

