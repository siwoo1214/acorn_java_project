package day21.비동기;

public class 멀티Thread3 {

	public static void main(String[] args) {
		
		// 3. Runnable 인터페이스를 익명으로 구현하기
		
		// Thread th1 = new Thread( new Runnable() { });
		 
		 Thread th1 = new Thread( new Runnable() {
			@Override
			public void run() {

				for(int i=1 ;  i<=1000 ; i++) {			
					System.out.println( "하이 ^^");
				}
				
			}} );
		 
		 
		 
		 
		 Thread th2 = new Thread( new Runnable() {

			@Override
			public void run() {
				for(int i=1 ;  i<=1000 ; i++) {			
					System.out.println( "바이 ~");
				}
				
			}});
		 
		 
		 th1.start();
		 th2.start();
		 

	}

}

 
 