package day21.sleep;

public class Sleep기본예제0 {

	public static void main(String[] args) {
		 
		//sleep
		//현재 스레드를 지정된 시간동안 멈추게 한다. 잠잘시간 제공 ( 천분의 일초) 3000 => 3초
		//사용법
		//Tread.sleep(1000); 

		new SleepThread().start();
	}

}




class  SleepThread extends Thread{	
	@Override
	public void run() {
		System.out.println(" 3초후에 공개합니다");
		
		try {
			
			Thread.sleep(3000);
		} catch (InterruptedException e) {       // 이 예외는 외부에서 인터럽트가 걸렸을 때 sleep을 빠져나오기 위한 예외이다 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("짜짠 ~~~  3초가 지났어요  .. ");
	}
}