package test1;

import java.io.Serializable;

public class Job implements Serializable{
	String job;
	String doWhat;
	int salary;
	
	public Job(String job, String doWhat, int salary) {
		super();
		this.job = job;
		this.doWhat = doWhat;
		this.salary = salary;
	}
	
	public String toString() {
		return job+"은 "+doWhat+"을 하고 월급은 "+salary+"입니다.";
	}
}
