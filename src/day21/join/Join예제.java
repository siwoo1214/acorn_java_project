package day21.join;

 

public  class Join예제 {
	public static void main(String[] args)  	{
		 
 
		//스레드 실행 요청 ,스레드시작됨
		Counter   counter = new Counter();
		counter.start();		
		
		//시간지연을 시키기 위해 추가된 코드 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		 
			e.printStackTrace();
		}
			 	
		
		// main이 가다려주기 , Counter스레드가 종료되기를
		try {
			counter.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       		
		
		System.out.println("프로그램 종료");
	}
}



