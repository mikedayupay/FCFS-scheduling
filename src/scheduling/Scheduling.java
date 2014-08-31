package scheduling;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class Scheduling {
	public static Scanner input = new Scanner(System.in);
	private static Process pA;
	private static Process pB;
	private static Process pC;
	private static Process pD;
	
	private static LinkedList<Process> ready_Q;
	private static LinkedList<Process> job_Q;
	private static LinkedList<Process> rr_ready_Q;
	private static LinkedList<Process> rr_job_Q;
	private static int burst_time;
	
	public static void main(String args[]) throws IOException{
		
		
		Path source = Paths.get("input.txt");
		System.out.println(source.getFileName());
		
		initData(source);
		//System.out.print(pD.getBurstTime());
		FCFS();
		round_robin();
		
	}
	
	public static void initData(Path source){
		try (BufferedReader reader = Files.newBufferedReader(source)){
			String line = null;
			int b_time, a_time;
			

			//Process A
			line = reader.readLine();
			//System.out.println(line);
			String parts[] = line.split(" ");
			
			b_time = Integer.parseInt(parts[2]);
			a_time = Integer.parseInt(parts[1]);
			pA = new Process(parts[0], b_time, a_time);
			

			//Process B
			line = reader.readLine();
			//System.out.println(line);
			String parts_b[] = line.split(" ");
			b_time = Integer.parseInt(parts_b[2]);
			a_time = Integer.parseInt(parts_b[1]);
			pB = new Process(parts_b[0], b_time, a_time);
			
			//Process C
			line = reader.readLine();
			//System.out.println(line);
			String parts_c[] = line.split(" ");
			b_time = Integer.parseInt(parts_c[2]);
			a_time = Integer.parseInt(parts_c[1]);
			pC = new Process(parts_c[0], b_time, a_time);
			
			//Process D
			line = reader.readLine();
			//System.out.println(line);
			String parts_d[] = line.split(" ");
			b_time = Integer.parseInt(parts_d[2]);
			a_time = Integer.parseInt(parts_d[1]);
			pD = new Process(parts_d[0], b_time, a_time);
			
			System.out.println(pA.getProcessName() + " " + pA.getArrivalTime() + " " + pA.getBurstTime());
			System.out.println(pB.getProcessName() + " " + pB.getArrivalTime() + " " + pB.getBurstTime());
			System.out.println(pC.getProcessName() + " " + pC.getArrivalTime() + " " + pC.getBurstTime());
			System.out.println(pD.getProcessName() + " " + pD.getArrivalTime() + " " + pD.getBurstTime());
			System.out.println();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void FCFS(){
		int processtime = 0;
		int totalProcessTime;
		double fcfs_average_waiting_time;
		
		totalProcessTime = pA.getBurstTime() + pB.getBurstTime() + pC.getBurstTime() + pD.getBurstTime();
		//System.out.println(totalProcessTime);
		ready_Q = new LinkedList<Process>();
		job_Q = new LinkedList<Process>();
		
		while (processtime < totalProcessTime) {
			if(pA.getArrivalTime() == processtime){
				ready_Q.add(pA);
			}
			else if(pB.getArrivalTime() == processtime){
				ready_Q.add(pB);
				//System.out.print(ready_Q.get(0).getProcessName());
			}else if(pC.getArrivalTime() == processtime){
				ready_Q.add(pC);
				//System.out.print(ready_Q.get(1).getProcessName());
			}else if(pD.getArrivalTime() == processtime){
				ready_Q.add(pD);
				//System.out.print(ready_Q.get(2).getProcessName());
			}else{
				//do nothing
			}

				//System.out.println(totalProcessTime);
			if(job_Q.peek() == null){
				
				job_Q.add(ready_Q.remove());
				//System.out.println(job_Q.element().getProcessName());
				job_Q.element().setWaitingTime((processtime) - job_Q.element().getArrivalTime());
				burst_time = job_Q.element().getBurstTime();
				System.out.print(job_Q.element().getProcessName());

			}else{
				
				System.out.print(job_Q.element().getProcessName());
				
			}
			
			processtime++;	
			burst_time--;
			if(burst_time == 0){
				job_Q.remove();
			}
		}
		
		fcfs_average_waiting_time = (pA.getWaitingTime() + pB.getWaitingTime() + pC.getWaitingTime() + pD.getWaitingTime()) / 4.0;
		System.out.println();
		System.out.println("Average Waiting Time for FCFS: " +fcfs_average_waiting_time);
	}
	
	public static void round_robin(){
		int quantum;
		int processtime = 0;
		int totalprocesstime;
		double rr_average_waiting_time;
		int temp;
		
		
		totalprocesstime = pA.getBurstTime() + pB.getBurstTime() + pC.getBurstTime() + pD.getBurstTime();
		
		rr_ready_Q = new LinkedList<Process>();
		rr_job_Q = new LinkedList<Process>();
		System.out.println();
		System.out.println("Round Robin");
		System.out.println("Please enter the quantum: ");
		quantum = input.nextInt();
		temp = quantum;
		//System.out.println(quantum);
		while(processtime < totalprocesstime){
			if(pA.getArrivalTime() == processtime){
				rr_ready_Q.add(pA);
			}
			else if(pB.getArrivalTime() == processtime){
				rr_ready_Q.add(pB);
				//System.out.print(ready_Q.get(0).getProcessName());
			}else if(pC.getArrivalTime() == processtime){
				rr_ready_Q.add(pC);
				//System.out.print(ready_Q.get(1).getProcessName());
			}else if(pD.getArrivalTime() == processtime){
				rr_ready_Q.add(pD);
				//System.out.print(ready_Q.get(2).getProcessName());
			}else{
				//do nothing
			}
			
			if(rr_job_Q.peek() == null){
				rr_job_Q.add(rr_ready_Q.remove());
				rr_job_Q.element().setWaitingTime(rr_job_Q.element().getWaitingTime() + (processtime - rr_job_Q.element().getArrivalTime()));
				System.out.print(rr_job_Q.element().getProcessName());
			}else{
				System.out.print(rr_job_Q.element().getProcessName());
			}
			
			quantum--;
			rr_job_Q.peek().decBurstTime();
			if(rr_job_Q.element().getBurstTime() == 0){
				rr_job_Q.remove();
				quantum = temp;
			}
			if(quantum == 0){
				if(rr_job_Q.peek().getBurstTime() == 0){
					rr_job_Q.remove();
					quantum = temp;
				}else{
					//rr_job_Q.element().setArrivalTime(processtime);
					rr_ready_Q.add(rr_job_Q.remove());
					rr_ready_Q.peekLast().setArrivalTime(processtime);
					quantum = temp;
				}
			}
			processtime++;
		}
		
		rr_average_waiting_time = (pA.getWaitingTime() + pB.getWaitingTime() + pC.getWaitingTime() + pD.getWaitingTime()) / 4.0;
		System.out.println();
		System.out.println("Average Waiting Time for Round Robin: " + rr_average_waiting_time);
	}
}
