# Operating-Systems-Scheduling
Compiling:
(upload all the input files, random-numbers.txt, and Scheduling.java onto cims server)

javac Scheduling.java

Running:
java Scheduling input-1
java Scheduling --verbose input-1 //double hyphens before verbose to run the detailed input for every scheduling algorithms 

Here is one sample input and output:
java Scheduling input-1

The original input was: 1  0 1 5 1   
The (sorted) input  is: 1  0 1 5 1   


The scheduling algorithm used was First Come First Serve

Process 0:
        (A,B,C,IO) = (0,1,5,1)
        Finishing time: 9
        Turnaround time: 9
        I/O time: 4
        Waiting time: 0

Summary Data:
        Finishing time: 9
        CPU Utilization: 0.555556
        I/O Utilization: 0.444444
        Throughput: 11.111111 processes per hundred cycles
        Average turnaround time: 9.000000
        Average waiting time: 0.000000


The scheduling algorithm used was Round Robin

Process 0:
        (A,B,C,IO) = (0,1,5,1)
        Finishing time: 9
        Turnaround time: 9
        I/O time: 4
        Waiting time: 0

Summary Data:
        Finishing time: 9
        CPU Utilization: 0.555556
        I/O Utilization: 0.444444
        Throughput: 11.111111 processes per hundred cycles
        Average turnaround time: 9.000000
        Average waiting time: 0.000000


The scheduling algorithm used was Uniprocesser

Process 0:
        (A,B,C,IO) = (0,1,5,1)
        Finishing time: 9
        Turnaround time: 9
        I/O time: 4
        Waiting time: 0

Summary Data:
        Finishing time: 9
        CPU Utilization: 0.555556
        I/O Utilization: 0.444444
        Throughput: 11.111111 processes per hundred cycles
        Average turnaround time: 9.000000
        Average waiting time: 0.000000


The scheduling algorithm used was Shortest Job First

Process 0:
        (A,B,C,IO) = (0,1,5,1)
        Finishing time: 9
        Turnaround time: 9
        I/O time: 4
        Waiting time: 0

Summary Data:
        Finishing time: 9
        CPU Utilization: 0.555556
        I/O Utilization: 0.444444
        Throughput: 11.111111 processes per hundred cycles
        Average turnaround time: 9.000000
        Average waiting time: 0.000000

java Scheduling --verbose input-1

The original input was: 1  0 1 5 1   
The (sorted) input  is: 1  0 1 5 1   


The scheduling algorithm used was First Come First Serve


This detailed printout gives the state and remaining burst for each process

Before cycle    0:   unstarted  0.
Before cycle    1:     running  1.
Before cycle    2:     blocked  1.
Before cycle    3:     running  1.
Before cycle    4:     blocked  1.
Before cycle    5:     running  1.
Before cycle    6:     blocked  1.
Before cycle    7:     running  1.
Before cycle    8:     blocked  1.
Before cycle    9:     running  1.
The scheduling algorithm used was First Come First Serve

Process 0:
        (A,B,C,IO) = (0,1,5,1)
        Finishing time: 9
        Turnaround time: 9
        I/O time: 4
        Waiting time: 0

Summary Data:
        Finishing time: 9
        CPU Utilization: 0.555556
        I/O Utilization: 0.444444
        Throughput: 11.111111 processes per hundred cycles
        Average turnaround time: 9.000000
        Average waiting time: 0.000000


The scheduling algorithm used was Round Robin


This detailed printout gives the state and remaining burst for each process

Before cycle    0:   unstarted  0.
Before cycle    1:     running  1.
Before cycle    2:     blocked  1.
Before cycle    3:     running  1.
Before cycle    4:     blocked  1.
Before cycle    5:     running  1.
Before cycle    6:     blocked  1.
Before cycle    7:     running  1.
Before cycle    8:     blocked  1.
Before cycle    9:     running  1.
The scheduling algorithm used was Round Robin

Process 0:
        (A,B,C,IO) = (0,1,5,1)
        Finishing time: 9
        Turnaround time: 9
        I/O time: 4
        Waiting time: 0

Summary Data:
        Finishing time: 9
        CPU Utilization: 0.555556
        I/O Utilization: 0.444444
        Throughput: 11.111111 processes per hundred cycles
        Average turnaround time: 9.000000
        Average waiting time: 0.000000


The scheduling algorithm used was Uniprocesser


This detailed printout gives the state and remaining burst for each process

Before cycle    0:   unstarted  0.
Before cycle    1:     running  1.
Before cycle    2:     blocked  1.
Before cycle    3:     running  1.
Before cycle    4:     blocked  1.
Before cycle    5:     running  1.
Before cycle    6:     blocked  1.
Before cycle    7:     running  1.
Before cycle    8:     blocked  1.
Before cycle    9:     running  1.
The scheduling algorithm used was Uniprocessor

Process 0:
        (A,B,C,IO) = (0,1,5,1)
        Finishing time: 9
        Turnaround time: 9
        I/O time: 4
        Waiting time: 0

Summary Data:
        Finishing time: 9
        CPU Utilization: 0.555556
        I/O Utilization: 0.444444
        Throughput: 11.111111 processes per hundred cycles
        Average turnaround time: 9.000000
        Average waiting time: 0.000000


The scheduling algorithm used was Shortest Job First


This detailed printout gives the state and remaining burst for each process

Before cycle    0:   unstarted  0.
Before cycle    1:     running  1.
Before cycle    2:     blocked  1.
Before cycle    3:     running  1.
Before cycle    4:     blocked  1.
Before cycle    5:     running  1.
Before cycle    6:     blocked  1.
Before cycle    7:     running  1.
Before cycle    8:     blocked  1.
Before cycle    9:     running  1.
The scheduling algorithm used was Shortest Job First

Process 0:
        (A,B,C,IO) = (0,1,5,1)
        Finishing time: 9
        Turnaround time: 9
        I/O time: 4
        Waiting time: 0

Summary Data:
        Finishing time: 9
        CPU Utilization: 0.555556
        I/O Utilization: 0.444444
        Throughput: 11.111111 processes per hundred cycles
        Average turnaround time: 9.000000
        Average waiting time: 0.000000

