package day21.비동기;

public class 멀티Thread2 {

	public static void main(String[] args) {
		 // 2. Runnable인터페이스 구현한 클래스 만들고 사용하기

		Thread th1  = new Thread( new R1());
		Thread th2  = new Thread( new R2());
		
		
		th1.start();
		th2.start();
		 
		
	}
	
}

 class R1   implements     Runnable  {
			@Override
			public void run() {

				for(int i=1 ;  i<=1000 ; i++) {			
					System.out.println( "하이 ^^");
				}
				
			}
			
 }
		 
		 
		 
		 
 class R2   implements     Runnable  {
		@Override
		public void run() {

			for(int i=1 ;  i<=1000 ; i++) {			
				System.out.println( "바이 !!!");
			}
			
		}
		
}
	 

	
	
	
 
