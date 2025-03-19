package day21.공유자원문제;

public class 비동기문제_공유자원해결 {
	


	static int gIndex=0;   // 데이터/정적영역 static, 같이 사용하는 자원
	
	
	
	public static void print() {
		int index=0;  // 스택영역  <= 각 스레드마다 스택공간 확보
		
		Thread th = Thread.currentThread();		
		for( int i=0; i<100; i++){
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		  synchronized (비동기문제_공유자원해결.class) {
			  index++;
			  gIndex++;			
			  System.out.printf("%s[%d] : %d, index: %d , gIndex: %d\n", th.getName() , th.getId() , i +1,  index, gIndex);
				
		  }
		  
		}		
		
	}
	
	
	
	
	
	public static void main(String[] args){
		
		
		//인터페이스 구현한 객체
		Runnable r  = new Runnable(){

			@Override
			public void run() {				
				 print();
			}	 
 
		};
		
		
		
	 Thread th1 = new Thread(r);
	 Thread th2 = new Thread(r);
	 Thread th3  = new Thread(r);
		
	 
	 th1.start();
	 th2.start();
	 th3.start();
	 
	 
	}


}



 