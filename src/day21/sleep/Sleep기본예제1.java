package day21.sleep;

 

public  class Sleep기본예제1 {
	public static void main(String[] args)  	{
		 
 
		//스레드 실행 요청 
		Counter  th1 = new Counter ();
		th1.start();  // 쓰레드 시작			
		 
	 
	}
}




class Counter  extends Thread {	
	public void run() {
		for(int i=10; i > 0; i--) {		 
				try {
					sleep(1000);
					System.out.println( i  +" count");
				} catch (InterruptedException e) {					 
					e.printStackTrace(); 
				}			 
		}
		
		
	}
}
 