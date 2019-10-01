{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf830
{\fonttbl\f0\fnil\fcharset0 Monaco;\f1\fnil\fcharset0 Menlo-Regular;}
{\colortbl;\red255\green255\blue255;\red0\green0\blue255;\red0\green0\blue0;\red255\green255\blue255;
\red255\green0\blue0;}
{\*\expandedcolortbl;;\csgenericrgb\c0\c0\c100000;\csgray\c0;\csgray\c100000;
\csgenericrgb\c100000\c0\c0;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab720
\pard\pardeftab720\partightenfactor0

\f0\fs22 \cf2 Compiling:\
\cf0 (upload all the input files, random-numbers.txt, and Scheduling.java onto cims server)\
\
javac Scheduling.java\
\
\cf2 Running:\cf0 \
java Scheduling 
\f1 \cf3 \cb4 \CocoaLigature0 input-1
\f0 \cf0 \cb1 \CocoaLigature1 \
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\pardirnatural\partightenfactor0

\f1 \cf3 \cb4 \CocoaLigature0 java Scheduling --verbose input-1 //
\f0 \cf0 \cb1 \CocoaLigature1 double hyphens before verbose to run the detailed input for every scheduling algorithms \
\pard\pardeftab720\partightenfactor0
\cf0 \
\cf2 Here is one sample input and output:\
\cf5 java Scheduling 
\f1 \cb4 \CocoaLigature0 input-1\
\cf3 \
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\pardirnatural\partightenfactor0
\cf3 The original input was: 1  0 1 5 1   \
The (sorted) input  is: 1  0 1 5 1   \
\
\
The scheduling algorithm used was First Come First Serve\
\
Process 0:\
        (A,B,C,IO) = (0,1,5,1)\
        Finishing time: 9\
        Turnaround time: 9\
        I/O time: 4\
        Waiting time: 0\
\
Summary Data:\
        Finishing time: 9\
        CPU Utilization: 0.555556\
        I/O Utilization: 0.444444\
        Throughput: 11.111111 processes per hundred cycles\
        Average turnaround time: 9.000000\
        Average waiting time: 0.000000\
\
\
The scheduling algorithm used was Round Robin\
\
Process 0:\
        (A,B,C,IO) = (0,1,5,1)\
        Finishing time: 9\
        Turnaround time: 9\
        I/O time: 4\
        Waiting time: 0\
\
Summary Data:\
        Finishing time: 9\
        CPU Utilization: 0.555556\
        I/O Utilization: 0.444444\
        Throughput: 11.111111 processes per hundred cycles\
        Average turnaround time: 9.000000\
        Average waiting time: 0.000000\
\
\
The scheduling algorithm used was Uniprocesser\
\
Process 0:\
        (A,B,C,IO) = (0,1,5,1)\
        Finishing time: 9\
        Turnaround time: 9\
        I/O time: 4\
        Waiting time: 0\
\
Summary Data:\
        Finishing time: 9\
        CPU Utilization: 0.555556\
        I/O Utilization: 0.444444\
        Throughput: 11.111111 processes per hundred cycles\
        Average turnaround time: 9.000000\
        Average waiting time: 0.000000\
\
\
The scheduling algorithm used was Shortest Job First\
\
Process 0:\
        (A,B,C,IO) = (0,1,5,1)\
        Finishing time: 9\
        Turnaround time: 9\
        I/O time: 4\
        Waiting time: 0\
\
Summary Data:\
        Finishing time: 9\
        CPU Utilization: 0.555556\
        I/O Utilization: 0.444444\
        Throughput: 11.111111 processes per hundred cycles\
        Average turnaround time: 9.000000\
        Average waiting time: 0.000000\
\
\cf5 java Scheduling --verbose input-1\
\
\cf3 The original input was: 1  0 1 5 1   \
The (sorted) input  is: 1  0 1 5 1   \
\
\
The scheduling algorithm used was First Come First Serve\
\
\
This detailed printout gives the state and remaining burst for each process\
\
Before cycle    0:   unstarted  0.\
Before cycle    1:     running  1.\
Before cycle    2:     blocked  1.\
Before cycle    3:     running  1.\
Before cycle    4:     blocked  1.\
Before cycle    5:     running  1.\
Before cycle    6:     blocked  1.\
Before cycle    7:     running  1.\
Before cycle    8:     blocked  1.\
Before cycle    9:     running  1.\
The scheduling algorithm used was First Come First Serve\
\
Process 0:\
        (A,B,C,IO) = (0,1,5,1)\
        Finishing time: 9\
        Turnaround time: 9\
        I/O time: 4\
        Waiting time: 0\
\
Summary Data:\
        Finishing time: 9\
        CPU Utilization: 0.555556\
        I/O Utilization: 0.444444\
        Throughput: 11.111111 processes per hundred cycles\
        Average turnaround time: 9.000000\
        Average waiting time: 0.000000\
\
\
The scheduling algorithm used was Round Robin\
\
\
This detailed printout gives the state and remaining burst for each process\
\
Before cycle    0:   unstarted  0.\
Before cycle    1:     running  1.\
Before cycle    2:     blocked  1.\
Before cycle    3:     running  1.\
Before cycle    4:     blocked  1.\
Before cycle    5:     running  1.\
Before cycle    6:     blocked  1.\
Before cycle    7:     running  1.\
Before cycle    8:     blocked  1.\
Before cycle    9:     running  1.\
The scheduling algorithm used was Round Robin\
\
Process 0:\
        (A,B,C,IO) = (0,1,5,1)\
        Finishing time: 9\
        Turnaround time: 9\
        I/O time: 4\
        Waiting time: 0\
\
Summary Data:\
        Finishing time: 9\
        CPU Utilization: 0.555556\
        I/O Utilization: 0.444444\
        Throughput: 11.111111 processes per hundred cycles\
        Average turnaround time: 9.000000\
        Average waiting time: 0.000000\
\
\
The scheduling algorithm used was Uniprocesser\
\
\
This detailed printout gives the state and remaining burst for each process\
\
Before cycle    0:   unstarted  0.\
Before cycle    1:     running  1.\
Before cycle    2:     blocked  1.\
Before cycle    3:     running  1.\
Before cycle    4:     blocked  1.\
Before cycle    5:     running  1.\
Before cycle    6:     blocked  1.\
Before cycle    7:     running  1.\
Before cycle    8:     blocked  1.\
Before cycle    9:     running  1.\
The scheduling algorithm used was Uniprocessor\
\
Process 0:\
        (A,B,C,IO) = (0,1,5,1)\
        Finishing time: 9\
        Turnaround time: 9\
        I/O time: 4\
        Waiting time: 0\
\
Summary Data:\
        Finishing time: 9\
        CPU Utilization: 0.555556\
        I/O Utilization: 0.444444\
        Throughput: 11.111111 processes per hundred cycles\
        Average turnaround time: 9.000000\
        Average waiting time: 0.000000\
\
\
The scheduling algorithm used was Shortest Job First\
\
\
This detailed printout gives the state and remaining burst for each process\
\
Before cycle    0:   unstarted  0.\
Before cycle    1:     running  1.\
Before cycle    2:     blocked  1.\
Before cycle    3:     running  1.\
Before cycle    4:     blocked  1.\
Before cycle    5:     running  1.\
Before cycle    6:     blocked  1.\
Before cycle    7:     running  1.\
Before cycle    8:     blocked  1.\
Before cycle    9:     running  1.\
The scheduling algorithm used was Shortest Job First\
\
Process 0:\
        (A,B,C,IO) = (0,1,5,1)\
        Finishing time: 9\
        Turnaround time: 9\
        I/O time: 4\
        Waiting time: 0\
\
Summary Data:\
        Finishing time: 9\
        CPU Utilization: 0.555556\
        I/O Utilization: 0.444444\
        Throughput: 11.111111 processes per hundred cycles\
        Average turnaround time: 9.000000\
        Average waiting time: 0.000000\
\
}