package day21.비동기.실습;

/*
 *  추상메소드
 * interface Runnable{
 * 		void run();
 * 
 * }
 */


public class Ex03 {
	public static void main(String[] args) {
	
		//별도의 흐음으로 만들기(멀티스레드)
		//for(int i=0; i<100; i++){
		//syso(하이)
		//}
		
		//별도의 흐음으로 만들기(멀티스레드)
		//for(int i=0; i<100; i++){
		//syso(바이)
		//}
		
		//              Thread();
		//              Thread(Runnable r)
		Thread th = new Thread(new C());
		Thread th2 = new Thread(new D());
		
		th.start();
		th2.start();
	}
}

class C implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<=100; i++) {
			System.out.println("하이");
		}
	}
}

class D implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<=100; i++) {
			System.out.println("바이");
		}
	}
}
