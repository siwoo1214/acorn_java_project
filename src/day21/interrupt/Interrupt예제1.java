package day21.interrupt;



 

public  class Interrupt예제1 {
	public static void main(String[] args)  {
		 
		// interrupt();
		// 신호보내기, 쿡찌르기
		// "너에게 신호를 보냈으니 알아서 처리해"
		// interrupt() 메서드는 자바에서 쓰레드에 인터럽트를 걸어 특정 작업을 중지하거나 다른 작업을 수행하도록 신호를 보내는 데 사용됩니다.
		// interrupt()는 강제로 쓰레드를 중지시키는 것이 아니라, 쓰레드에 인터럽트 상태를 설정하여 그 쓰레드가 현재 수행 중인 작업을 적절히 중단하도록  하는것이다.
 
		//스레드 실행 요청 ,스레드시작됨
		Counter   counter = new Counter  ();
		counter.start();		
		
		//시간지연을 시키기 위해 추가된 코드 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		 
			e.printStackTrace();
		}
		
		//5초후에 카운터애 인터럽트 발생시킴 그래서 종료시키고 싶다면?? 
		counter.interrupt();      // counter 스레드에  interrupt() 발생시킨다.
		
		System.out.println("프로그램 종료");
	}
}



