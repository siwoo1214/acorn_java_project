package day21.멀티스레드예제.sample2;

public class StudyThread  extends Thread{

	
	 @Override
	public void run() {
		 
		 
		 while( !isInterrupted()) {
			 
			System.out.println("공부하기");
			
			
			for( int i=1;  i< 2100000000; i++);
		 }
		 
		 
	}
}
