package day21.비동기;


//멀티프로세스 프로그램 작성하기


public class 멀티Thread1 {

	public static void main(String[] args) {
		
		// 1. 스레드를 상속 받아서  만들기
		 Thread1 th1 = new Thread1();
		 Thread2 th2 = new Thread2();		 
		 
		 th1.start();
		 th2.start();
		 

	}

}



class Thread1 extends Thread{
	@Override
	public void run() {

		for(int i=1 ;  i<=1000 ; i++) {			
			System.out.println( "하이 ^^");
		}
	}
}



class Thread2 extends Thread{
	@Override
	public void run() {

		for(int i=1 ;  i<=1000 ; i++) {			
			System.out.println( "바이!!");
		}
	}
}