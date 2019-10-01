import java.io.*;
import java.util.*;

public class Scheduling {
	public static ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<Process> jobList = new ArrayList<Process>(); //store objects of processes 
	public static int INF = 1000000;
	public static int numProcess;	
	public static String[][] status = new String[numProcess][INF];
	public static int[][] time = new int[numProcess][INF];    //store the time of all processes
	public static int[] ready = new int[numProcess];          //store the total ready time   
	public static int[] terminated = new int[numProcess];     //store the total terminate time
	public static int[] block = new int[numProcess];  //store how many cycles each process has been blocked
	public static int[] CPU_remaining = new int[numProcess]; //store the remaining CPU time 
	public static  int cputimes=0;//use for calculating CPU utilization
	public static  int iotimes=0;
	public static int runFlag=-1;
	public static int cycle;
	public static Scanner rd;
	public static Scanner reader;
	public static String file_input = null;

	public static void main(String[] args) throws FileNotFoundException {
		file_input = null;
		if(args.length == 2) {
			if (args[0].equals("--verbose")) {
				file_input = args[1];
				input();
				System.out.println("\n\nThe scheduling algorithm used was First Come First Serve");
				input_rand();
				fcfs();
				fcfs_detailed();
				output();

				System.out.println("\n\nThe scheduling algorithm used was Round Robin");
				input_rand();
				rr();
				rr_detailed();
				output();

				System.out.println("\n\nThe scheduling algorithm used was Uniprocesser");
				input_rand();
				uni();
				uni_detailed();
				output();

				System.out.println("\n\nThe scheduling algorithm used was Shortest Job First");
				input_rand();
				sjf();
				sjf_detailed();
				output();
			}
		}

		if(args.length == 1) {
			file_input = args[0];
			input();
			System.out.println("\n\nThe scheduling algorithm used was First Come First Serve\n");
			input_rand();
			fcfs();
			output();

			System.out.println("\n\nThe scheduling algorithm used was Round Robin\n");
			input_rand();
			rr();
			output();

			System.out.println("\n\nThe scheduling algorithm used was Uniprocesser\n");
			input_rand();
			uni();
			output();

			System.out.println("\n\nThe scheduling algorithm used was Shortest Job First\n");
			input_rand();
			sjf();
			output();

		}
	}

	public static void input_rand() throws FileNotFoundException{
		try {
			rd = new Scanner(new File("random-numbers.txt"));
		}catch(IOException ioException){
			System.out.println("No random-numbers.txt file.");
		}
	}

	public static void input() throws FileNotFoundException {
		
		try {
			reader = new Scanner(new File(file_input));
		}catch(IOException ioException){
			System.out.println("No input process file.");
		}
		
		numProcess = reader.nextInt();

		//accept the original input 
		for(int i = 0; i < numProcess; i++) {
			input.add(new ArrayList<Integer>());
			for(int j = 0; j < 4; j++) {
				input.get(i).add(reader.nextInt());
			}
		}
		//print out the original input 
		System.out.print("The original input was: " + numProcess + "  ");
		for(int i = 0; i < numProcess; i++) {			 
			for(Integer k : input.get(i)) {					 			
				System.out.print(k + " ");
			}
			System.out.print("  ");
		} 
		System.out.println("");

		//sort the input by the arrival time of each process
		input.sort(new CustomComparator(0));

		//print out the sorted input 
		System.out.print("The (sorted) input  is: " + numProcess + "  ");
		for(int i = 0; i < numProcess; i++) {
			for(Integer k : input.get(i)) {					 			
				System.out.print(k + " ");
			}
			System.out.print("  ");
		} 
		System.out.print("\n");

		//create job list according to the sorted sequence 
		for(int i = 0; i < numProcess; i++) {
			int A = input.get(i).get(0);
			int B = input.get(i).get(1);
			int C = input.get(i).get(2);
			int io = input.get(i).get(3);
			jobList.add(new Process(i,A,B,C,io));
		} 

	}

	public static int randomOS(int U)  {
		int X = rd.nextInt();
		return 1 + (X % U);
	}

	public static void fcfs() {
		//create new empty lists
		status = new String[numProcess][INF]; //store the status of all processes: unstarted, ready, running, blocked, terminated
		time = new int[numProcess][INF];    //store the time of all processes (CPU burst and io burst)
		ready = new int[numProcess];          //store the total ready time
		terminated = new int[numProcess];     //count how long each process has terminated
		block = new int[numProcess];  			//count how long each process has been blocked 
		CPU_remaining = new int [numProcess];
		cputimes = 0;
		iotimes = 0;
		ArrayList<Process> readyQ = new ArrayList<Process>();

		//first store the initial total CPU time for each process
		for (int i=0;i<numProcess;i++) {
			CPU_remaining [i]= jobList.get(i).CPU_time;
			terminated[i] = 0;
		}

		//record all "unstarted"
		for (int i=0;i<numProcess;i++) {
			for (int j=0 ; j <= jobList.get(i).arrival_time ; j++) {
				status[i][j]="unstarted";
				//initialize the time, ready and list 
				time[i][j]=0;
				ready[i] = 0;
				block[i] = 0;
			}
		}

		//set all the first arriving processes to ready
		for (int i=0;i<numProcess;i++) {
			if(status[i][jobList.get(0).arrival_time + 1] == null) {
				if(i == 0) {  //set the first process to running 
					status[i][jobList.get(0).arrival_time + 1] = "running";
					CPU_remaining[i]--;
					time[i][jobList.get(0).arrival_time + 1]=randomOS(jobList.get(0).CPU_burst);
					cputimes++;
				}else { //the other processes arrive the same time as the first process to readyQ
					status[i][jobList.get(0).arrival_time + 1] = "ready";
					time[i][jobList.get(0).arrival_time + 1] = 0;
					ready[i] ++;
					readyQ.add(jobList.get(i));
				}
			}
		}

		int size = 0;
		cycle = 0; //store cycles
		while(size != numProcess) {
			size = 0;
			cycle ++;

			for (int i = 0; i < numProcess; i++) {
				if(CPU_remaining[i] == 0) { //when a process has terminated
					status[i][cycle] = "terminated";
					terminated[i] ++;
				}

				if(status[i][cycle] == null) { 

					if(time[i][cycle - 1] > 1) { //when a process has remaining time either for CPU burst or io burst

						if(status[i][cycle - 1].equals("running")) { //has remaining time for CPU burst 
							status[i][cycle] = "running";
							time[i][cycle] = time[i][cycle - 1] - 1;
							CPU_remaining[i] --;
							cputimes ++;

						}else{ //have remaining time for io burst
							status[i][cycle] = status[i][cycle - 1];
							time[i][cycle] = time[i][cycle - 1] - 1;
							block[i] ++;
						}

					}else {	//no remaining time either for CPU burst or io burst

						if(status[i][cycle - 1].equals("running")) { 	//transit from running into io block 
							status[i][cycle] = "blocked";
							time[i][cycle] = randomOS(jobList.get(i).io_burst);
							block[i] ++;
						}

						if(status[i][cycle - 1].equals("blocked")) { 	//io block into ready   
							status[i][cycle] = "ready";
							ready[i] ++;
							time[i][cycle] = 0;
							readyQ.add(jobList.get(i));
						}

						if(status[i][cycle-1].equals("unstarted")) { //unstarted into ready 
							time[i][cycle]=0; 
							status[i][cycle]="ready";
							ready[i]++;
							readyQ.add(jobList.get(i));
						}

						if(status[i][cycle-1].equals("ready")) {  //ready still remains ready
							status[i][cycle]="ready";
							time[i][cycle]=0; 
							ready[i]++;
						}						
					}
				}
			}

			runFlag = -1; //use a runflag to check whether to push a ready process into running status
			for(int i = 0; i < numProcess; i++) {
				if (status[i][cycle].equals("ready")) { //if process i in this cycle is ready 
					for(int j = 0;j<numProcess; j++) {
						if (status[j][cycle].equals("running")) { //if exits another process is running at the same time
							runFlag = 0;
							break;
						}
					}

					if(runFlag != 0 && readyQ.isEmpty() != true) { //if no other process is currently running && readyQ is not empty
						int index = readyQ.get(0).index; //push the process with the longest waiting time into running 
						readyQ.remove(0);
						status[index][cycle]="running";
						cputimes++;
						CPU_remaining[index]--;
						time[index][cycle]=randomOS(jobList.get(index).CPU_burst);
						ready[index]--; 
						runFlag = 0;
					}

					if(runFlag != 0 && readyQ.isEmpty() == true) { //if no other process is currently running && readyQ is empty
						//push the current process itself into running  
						status[i][cycle]="running";
						cputimes++;
						CPU_remaining[i]--;
						time[i][cycle]=randomOS(jobList.get(i).CPU_burst);
						ready[i]--; 
						runFlag = 0;
					}

					if(runFlag == 0) { //make sure only push one process into running in a single cycle
						break;
					}
				}			
			}

			for(int i=0;i< numProcess;i++) { //calculate the total io time
				if(status[i][cycle].equals("blocked")) {
					iotimes++;
					break;
				}
			}

			for(int k = 0; k < numProcess; k++) { //check the size of total remaining job List
				if(CPU_remaining[k] == 0) {
					size++;
				}				
			}

		}
	}

	public static void fcfs_detailed() {

		System.out.println("\n\nThis detailed printout gives the state and remaining burst for each process\n");
		for (int i=0;i<=cycle;i++) {
			System.out.printf("Before cycle"+"%6s",i+":");
			for(int k=0;k<numProcess;k++) {
				System.out.printf("%12s"," "+status[k][i]);
				System.out.printf("%3s",time[k][i]);
			}
			System.out.println(".");
		}	
		System.out.println("The scheduling algorithm used was First Come First Serve\n");
	}

	public static void rr() {

		//create new empty lists
		status = new String[numProcess][INF]; //store the status of all processes: unstarted, ready, running, blocked, terminated
		time = new int[numProcess][INF];    //store the time of all processes (CPU burst and io burst)
		ready = new int[numProcess];          //store the total ready time
		terminated = new int[numProcess];     //count how long each process has terminated
		block = new int[numProcess];  			//count how long each process has been blocked 
		CPU_remaining = new int [numProcess];
		int [] runtime = new int [numProcess];   //keep track of the current run time of each process
		cputimes = 0;
		iotimes = 0;
		ArrayList<Process> readyQ = new ArrayList<Process>();

		//first store the initial total CPU time for each process
		for (int i=0;i<numProcess;i++) {
			CPU_remaining [i]= jobList.get(i).CPU_time;
			terminated[i] = 0;
		}

		//record all "unstarted"
		for (int i=0;i<numProcess;i++) {
			for (int j=0 ; j <= jobList.get(i).arrival_time ; j++) {
				status[i][j]="unstarted";
				//initialize the time, ready and list 
				time[i][j]=0;
				ready[i] = 0;
				block[i] = 0;
			}
		}

		runtime[0]=randomOS(jobList.get(0).CPU_burst);

		//set all the first arriving processes to ready
		for (int i=0;i<numProcess;i++) {
			if(status[i][jobList.get(0).arrival_time + 1] == null) {
				if(i == 0) {  //set the first process to running 
					status[i][jobList.get(0).arrival_time + 1] = "running";
					CPU_remaining[i]--;
					if(runtime[i] > 2) { //check if the run time exceeds the quantum 
						time[i][jobList.get(0).arrival_time + 1] = 2;
					}else {
						time[i][jobList.get(0).arrival_time + 1] = runtime[i];
					}
					runtime[i]--;
					cputimes++;
				}else { //the other processes arrive the same time as the first process to readyQ
					status[i][jobList.get(0).arrival_time + 1] = "ready";
					time[i][jobList.get(0).arrival_time + 1] = 0;
					runtime[i] = 0;
					ready[i] ++;
					readyQ.add(jobList.get(i));
				}
			}
		}

		int size = 0;
		cycle = 0; //store cycles
		while(size != numProcess) {
			size = 0;
			cycle ++;

			for (int i = 0; i < numProcess; i++) {
				if(CPU_remaining[i] == 0) { //when a process has terminated
					status[i][cycle] = "terminated";
					terminated[i] ++;
					time[i][cycle]=0;
				}

				if(status[i][cycle] == null) { 

					if(time[i][cycle - 1] > 1) { //when a process has remaining time either for CPU burst or io burst

						if(status[i][cycle - 1].equals("running") && time[i][cycle-1]==2) { //has remaining time for CPU burst 	
							status[i][cycle]="running"; 
							CPU_remaining[i]--;
							cputimes++;
							runtime[i]--;
							time[i][cycle]=1;
						}else{ //have remaining time for io burst
							status[i][cycle] = "blocked";
							time[i][cycle] = time[i][cycle - 1] - 1;
							block[i] ++;
						}

					}else {	//no remaining time either for CPU burst or io burst

						if(status[i][cycle - 1].equals("running")) { 
							if(runtime[i]==0) { //if current run time has already finished transit from running into io block 
								status[i][cycle]="blocked";
								time[i][cycle]=randomOS(jobList.get(i).io_burst);	
								block[i]++;
							}
							else {
								status[i][cycle]="ready";
								ready[i]++;
								readyQ.add(jobList.get(i));
								time[i][cycle]=0;
							}
						}

						if(status[i][cycle - 1].equals("blocked")) { 	//io block into ready   
							status[i][cycle] = "ready";
							ready[i] ++;
							time[i][cycle] = 0;;
							readyQ.add(jobList.get(i));
						}

						if(status[i][cycle-1].equals("unstarted")) { //unstarted into ready 
							time[i][cycle]=0; 
							status[i][cycle]="ready";
							ready[i]++;
							readyQ.add(jobList.get(i));
						}

						if(status[i][cycle-1].equals("ready")) {  //ready still remains ready
							status[i][cycle]="ready";
							time[i][cycle]=time[i][cycle -1]; 
							ready[i]++;
						}						
					}
				}
			}

			runFlag = -1; //use a runflag to check whether to push a ready process into running status
			for(int i = 0; i < numProcess; i++) {
				if (status[i][cycle].equals("ready")) { //if process i in this cycle is ready 
					for(int j = 0;j<numProcess; j++) {
						if (status[j][cycle]=="running") { //if exits another process is running at the same time
							runFlag = 0;
							break;
						}
					}

					if(runFlag != 0 && readyQ.isEmpty() != true) { //if no other process is currently running && readyQ is not empty
						int index = readyQ.get(0).index; //push the process with the longest waiting time into running 
						readyQ.remove(0);
						status[index][cycle]="running";
						if (runtime[index] != 0) {
							if (runtime[index] > 2) {
								time[index][cycle] = 2;
							}else {
								time[index][cycle] = runtime[index];
							}
						}else {
							runtime[index] = randomOS(jobList.get(index).CPU_burst);
							if (runtime[index] > 2) {
								time[index][cycle] = 2;
							}else {
								time[index][cycle] = runtime[index];
							}
						}
						cputimes++;
						CPU_remaining[index]--;
						ready[index]--; 
						runtime[index]--;
						runFlag = 0;
					}

					if(runFlag != 0 && readyQ.isEmpty() == true) { //if no other process is currently running && readyQ is empty
						//push the current process itself into running  
						status[i][cycle]="running";	
						if (runtime[i] != 0) {
							if (runtime[i] > 2) {
								time[i][cycle] = 2;
							}else {
								time[i][cycle] = runtime[i];
							}
						}else {
							runtime[i] = randomOS(jobList.get(i).CPU_burst);
							if (runtime[i] > 2) {
								time[i][cycle] = 2;
							}else {
								time[i][cycle] = runtime[i];
							}
						}
						cputimes++;
						CPU_remaining[i]--;
						runtime[i]--;
						ready[i]--; 
						runFlag = 0;
					}

					if(runFlag == 0) { //make sure only push one process into running in a single cycle
						break;
					}
				}			
			}

			for(int i=0;i< numProcess;i++) { //calculate the total io time
				if(status[i][cycle].equals("blocked")) {
					iotimes++;
					break;
				}
			}

			for(int k = 0; k < numProcess; k++) { //check the size of total remaining job List
				if(CPU_remaining[k] == 0) {
					size++;
				}				
			}
		}
	}

	public static void rr_detailed() {
		System.out.println("\n\nThis detailed printout gives the state and remaining burst for each process\n");

		for (int i=0;i<=cycle;i++) {
			System.out.printf("Before cycle"+"%6s",i+":");
			for(int k=0;k<numProcess;k++) {
				System.out.printf("%12s"," "+status[k][i]);
				System.out.printf("%3s",time[k][i]);
			}
			System.out.println(".");
		}	
		System.out.println("The scheduling algorithm used was Round Robin\n");
	}

	public static void uni() {

		//create new empty lists
		status = new String[numProcess][INF]; //store the status of all processes: unstarted, ready, running, blocked, terminated
		time = new int[numProcess][INF];    //store the time of all processes (CPU burst and io burst)
		ready = new int[numProcess];          //store the total ready time
		terminated = new int[numProcess];     //count how long each process has terminated
		block = new int[numProcess];  			//count how long each process has been blocked 
		CPU_remaining = new int [numProcess];
		cputimes = 0;
		iotimes = 0;
		ArrayList<Process> readyQ = new ArrayList<Process>();

		//first store the initial total CPU time for each process
		for (int i=0;i<numProcess;i++) {
			CPU_remaining [i]= jobList.get(i).CPU_time;
			terminated[i] = 0;
		}

		//record all "unstarted"
		for (int i=0;i<numProcess;i++) {
			for (int j=0 ; j <= jobList.get(i).arrival_time ; j++) {
				status[i][j]="unstarted";
				//initialize the time, ready and list 
				time[i][j]=0;
				ready[i] = 0;
				block[i] = 0;
			}
		}

		//set all the first arriving processes to ready
		for (int i=0;i<numProcess;i++) {
			if(status[i][jobList.get(0).arrival_time + 1] == null) {
				if(i == 0) {  //set the first process to running 
					status[i][jobList.get(0).arrival_time + 1] = "running";
					CPU_remaining[i]--;
					time[i][jobList.get(0).arrival_time + 1]=randomOS(jobList.get(0).CPU_burst);
					cputimes++;
				}else { //the other processes arrive the same time as the first process to readyQ
					status[i][jobList.get(0).arrival_time + 1] = "ready";
					time[i][jobList.get(0).arrival_time + 1] = 0;
					ready[i] ++;
					readyQ.add(jobList.get(i));
				}
			}
		}

		int size = 0;
		cycle = 0; //store cycles
		while(size != numProcess) {
			size = 0;
			cycle ++;

			for (int i = 0; i < numProcess; i++) {
				if(CPU_remaining[i] == 0) { //when a process has terminated
					status[i][cycle] = "terminated";
					terminated[i] ++;
				}

				if(status[i][cycle] == null) { 

					if(time[i][cycle - 1] > 1) { //when a process has remaining time either for CPU burst or io burst

						if(status[i][cycle - 1].equals("running")) { //has remaining time for CPU burst 
							status[i][cycle] = "running";
							time[i][cycle] = time[i][cycle - 1] - 1;
							CPU_remaining[i] --;
							cputimes ++;

						}else{ //have remaining time for io burst
							status[i][cycle] = status[i][cycle - 1];
							time[i][cycle] = time[i][cycle - 1] - 1;
							block[i] ++;
						}

					}else {	//no remaining time either for CPU burst or io burst

						if(status[i][cycle - 1].equals("running")) { 	//transit from running into io block 
							status[i][cycle] = "blocked";
							time[i][cycle] = randomOS(jobList.get(i).io_burst);
							block[i] ++;
						}

						if(status[i][cycle - 1].equals("blocked")) { 	//io block into ready   
							status[i][cycle] = "ready";
							ready[i] ++;
							time[i][cycle] = 0;
							//readyQ.add(jobList.get(i));
						}

						if(status[i][cycle-1].equals("unstarted")) { //unstarted into ready 
							time[i][cycle]=0; 
							status[i][cycle]="ready";
							ready[i]++;
							readyQ.add(jobList.get(i));
						}

						if(status[i][cycle-1].equals("ready")) {  //ready still remains ready
							status[i][cycle]="ready";
							time[i][cycle]=0; 
							ready[i]++;
						}						
					}
				}
			}

			runFlag = -1; //use a runflag to check whether to push a ready process into running status
			for(int i = 0; i < numProcess; i++) {
				if (status[i][cycle].equals("ready")) { //if process i in this cycle is ready 
					for(int j = 0;j<numProcess; j++) {
						if (status[j][cycle]=="running" || status[j][cycle]=="blocked") { //if exits another process is running at the same time
							runFlag = 0;
							break;
						}
					}

					if(runFlag != 0) { //if no other process is currently running && readyQ is empty
						//push the current process itself into running  
						status[i][cycle]="running";
						cputimes++;
						CPU_remaining[i]--;
						time[i][cycle]=randomOS(jobList.get(i).CPU_burst);
						ready[i]--; 
						runFlag = 0;
					}


					if(runFlag == 0) { //make sure only push one process into running in a single cycle
						break;
					}
				}			
			}

			for(int i=0;i< numProcess;i++) { //calculate the total io time
				if(status[i][cycle].equals("blocked")) {
					iotimes++;
					break;
				}
			}

			for(int k = 0; k < numProcess; k++) { //check the size of total remaining job List
				if(CPU_remaining[k] == 0) {
					size++;
				}				
			}
		}
	}

	public static void uni_detailed() {
		System.out.println("\n\nThis detailed printout gives the state and remaining burst for each process\n");
		for (int i=0;i<=cycle;i++) {
			System.out.printf("Before cycle"+"%6s",i+":");
			for(int k=0;k<numProcess;k++) {
				System.out.printf("%12s"," "+status[k][i]);
				System.out.printf("%3s",time[k][i]);
			}
			System.out.println(".");
		}	
		System.out.println("The scheduling algorithm used was Uniprocessor\n");
	}

	public static void sjf() {

		//create new empty lists
		status = new String[numProcess][INF]; //store the status of all processes: unstarted, ready, running, blocked, terminated
		time = new int[numProcess][INF];    //store the time of all processes (CPU burst and io burst)
		ready = new int[numProcess];          //store the total ready time
		terminated = new int[numProcess];     //count how long each process has terminated
		block = new int[numProcess];  			//count how long each process has been blocked 
		CPU_remaining = new int [numProcess];
		cputimes = 0;
		iotimes = 0;
		ArrayList<Process> readyQ = new ArrayList<Process>();

		//first store the initial total CPU time for each process
		for (int i=0;i<numProcess;i++) {
			CPU_remaining [i]= jobList.get(i).CPU_time;
			terminated[i] = 0;
		}

		//record all "unstarted"
		for (int i=0;i<numProcess;i++) {
			for (int j=0 ; j <= jobList.get(i).arrival_time ; j++) {
				status[i][j]="unstarted";
				//initialize the time, ready and list 
				time[i][j]=0;
				ready[i] = 0;
				block[i] = 0;
			}
		}

		//set all the first arriving processes to ready
		for (int i=0;i<numProcess;i++) {
			if(status[i][jobList.get(0).arrival_time + 1] == null) {
				if(i == 0) {  //set the first process to running 
					status[i][jobList.get(0).arrival_time + 1] = "running";
					CPU_remaining[i]--;
					time[i][jobList.get(0).arrival_time + 1]=randomOS(jobList.get(0).CPU_burst);
					cputimes++;
				}else { //the other processes arrive the same time as the first process to readyQ
					status[i][jobList.get(0).arrival_time + 1] = "ready";
					time[i][jobList.get(0).arrival_time + 1] = 0;
					ready[i] ++;
					readyQ.add(jobList.get(i));
				}
			}
		}
		
		int size = 0;
		cycle = 0; //store cycles
		while(size != numProcess) {
			size = 0;
			cycle ++;

			for (int i = 0; i < numProcess; i++) {
				if(CPU_remaining[i] == 0) { //when a process has terminated
					status[i][cycle] = "terminated";
					terminated[i] ++;
				}

				if(status[i][cycle] == null) { 

					if(time[i][cycle - 1] > 1) { //when a process has remaining time either for CPU burst or io burst

						if(status[i][cycle - 1].equals("running")) { //has remaining time for CPU burst 
							status[i][cycle] = "running";
							time[i][cycle] = time[i][cycle - 1] - 1;
							CPU_remaining[i] --;
							cputimes ++;

						}else{ //have remaining time for io burst
							status[i][cycle] = status[i][cycle - 1];
							time[i][cycle] = time[i][cycle - 1] - 1;
							block[i] ++;
						}

					}else {	//no remaining time either for CPU burst or io burst

						if(status[i][cycle - 1].equals("running")) { 	//transit from running into io block 
							status[i][cycle] = "blocked";
							time[i][cycle] = randomOS(jobList.get(i).io_burst);
							block[i] ++;
						}

						if(status[i][cycle - 1].equals("blocked")) { 	//io block into ready   
							status[i][cycle] = "ready";
							ready[i] ++;
							time[i][cycle] = 0;
							readyQ.add(jobList.get(i));
						}

						if(status[i][cycle-1].equals("unstarted")) { //unstarted into ready 
							time[i][cycle]=0; 
							status[i][cycle]="ready";
							ready[i]++;
							readyQ.add(jobList.get(i));
						}

						if(status[i][cycle-1].equals("ready")) {  //ready still remains ready
							status[i][cycle]="ready";
							time[i][cycle]=0; 
							ready[i]++;
						}						
					}
				}
			}

			runFlag = -1; //use a runflag to check whether to push a ready process into running status
			for(int i = 0; i < numProcess; i++) {
				if (status[i][cycle].equals("ready")) { //if process i in this cycle is ready 
					for(int j = 0;j<numProcess; j++) {
						if (status[j][cycle]=="running") { //if exits another process is running at the same time
							runFlag = 0;
							break;
						}
					}

					if(runFlag != 0 && readyQ.isEmpty() != true) { //if no other process is currently running && readyQ is not empty
						int min = CPU_remaining[readyQ.get(0).index];
						int index = readyQ.get(0).index;
						int idx = 0;
						for (int r = 0; r <readyQ.size(); r++) { //push the process in the ready queue with the shortest cpu remaining time into running 
							int a = readyQ.get(r).index;
							if (CPU_remaining[a] < min) {
								min = CPU_remaining[a];
								index = a;
								idx = r;
							}
						}

						readyQ.remove(idx);
						status[index][cycle]="running";
						cputimes++;
						CPU_remaining[index]--;
						time[index][cycle]=randomOS(jobList.get(index).CPU_burst);
						ready[index]--; 
						runFlag = 0;
					}

					if(runFlag != 0 && readyQ.isEmpty() == true) { //if no other process is currently running && readyQ is empty
						//push the current process itself into running  
						status[i][cycle]="running";
						cputimes++;
						CPU_remaining[i]--;
						time[i][cycle]=randomOS(jobList.get(i).CPU_burst);
						ready[i]--; 
						runFlag = 0;
					}

					if(runFlag == 0) { //make sure only push one process into running in a single cycle
						break;
					}
				}			
			}

			for(int i=0;i< numProcess;i++) { //calculate the total io time
				if(status[i][cycle].equals("blocked")) {
					iotimes++;
					break;
				}
			}

			for(int k = 0; k < numProcess; k++) { //check the size of total remaining job List
				if(CPU_remaining[k] == 0) {
					size++;
				}				
			}


		}

	}

	public static void sjf_detailed() {
		System.out.println("\n\nThis detailed printout gives the state and remaining burst for each process\n");
		for (int i=0;i<=cycle;i++) {
			System.out.printf("Before cycle"+"%6s",i+":");
			for(int k=0;k<numProcess;k++) {
				System.out.printf("%12s"," "+status[k][i]);
				System.out.printf("%3s",time[k][i]);
			}
			System.out.println(".");
		}	
		System.out.println("The scheduling algorithm used was Shortest Job First\n");
	}

	public static void output() {
		for(int i =0;i<numProcess;i++) {
			System.out.println("Process "+i+":");
			System.out.print("        (A,B,C,IO) = (");
			System.out.print(jobList.get(i).arrival_time +"," + jobList.get(i).CPU_burst +"," + jobList.get(i).CPU_time +"," + jobList.get(i).io_burst + ")\n");
			System.out.println("        Finishing time: "+(cycle-terminated[i]));
			System.out.println("        Turnaround time: "+(cycle-terminated[i]-jobList.get(i).arrival_time));
			System.out.println("        I/O time: "+block[i]);
			System.out.println("        Waiting time: "+ready[i]);
			System.out.println("");
		}
		System.out.println("Summary Data:");
		int maxf=0;

		double ATT=0;   
		double AWT=0;
		for(int i=0;i<numProcess;i++) {
			maxf=Math.max(maxf,(cycle-terminated[i]));

			ATT=ATT+cycle-terminated[i]-jobList.get(i).arrival_time;
			AWT=AWT+ready[i];
		}

		ATT=ATT/numProcess;  //Average turnaround time
		AWT=AWT/numProcess;  //Average waiting time: 
		System.out.println("        Finishing time: "+maxf);
		System.out.printf("        CPU Utilization: "+"%6f\n",(double)cputimes/cycle);
		System.out.printf("        I/O Utilization: "+"%6f\n",(double)iotimes/cycle);
		System.out.printf("        Throughput: "+"%6f",(double)(numProcess/(cycle*0.01)));
		System.out.println(" processes per hundred cycles");
		System.out.printf("        Average turnaround time: "+"%6f\n",ATT);
		System.out.printf("        Average waiting time: "+"%6f\n",AWT);
	}
}

class CustomComparator implements Comparator<ArrayList<Integer>> {

	private final int index;

	public CustomComparator(int index) {
		this.index = index;
	}

	public int compare(ArrayList<Integer> first, ArrayList<Integer> second) {
		return first.get(index).compareTo(second.get(index));
	}
}

class Process {
	int index;
	int arrival_time;
	int CPU_burst;
	int CPU_time;
	int io_burst;

	public Process(int index, int arrival_time, int CPU_burst, int CPU_time, int io_burst) {
		this.index = index;
		this.arrival_time = arrival_time;
		this.CPU_burst = CPU_burst;
		this.CPU_time = CPU_time;
		this.io_burst = io_burst;
	}
}


