package scheduling;

public class Process {
	
	private String processName;
	private int burstTime;
	private int arrivalTime;
	private int waitingTime = 0;
	
	public Process(String processName, int burstTime, int arrivalTime)
	{
		this.processName = processName;
		this.burstTime = burstTime;
		this.arrivalTime = arrivalTime;
	}
	
	public String getProcessName(){
		return processName;
	}
	
	public void decBurstTime(){
		this.burstTime = burstTime - 1;
	}
	
	public int getBurstTime(){
		return burstTime;
	}
	
	public void setArrivalTime(int arrivalTime){
		this.arrivalTime = arrivalTime;
	}
	
	public int getArrivalTime(){
		return arrivalTime;
	}
	
	public void setWaitingTime(int waitingTime){
		this.waitingTime = waitingTime;
	}
	
	public int getWaitingTime(){
		return waitingTime;
	}
}
