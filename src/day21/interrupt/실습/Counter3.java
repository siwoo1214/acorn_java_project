package day21.interrupt.실습;

public class Counter3 extends Thread{
	@Override
	public void run() {
		for(int i=10; i>0; i--) {
			
			//interrupt가 들어오면 멈추겠다 이거야
			//깨있다가 인터럽트가 걸렸을 때 실행할 수행문
			if(isInterrupted()) {  //interrupt => true(외부에서 신호 옴)/false(신호없음)
				System.out.println("interrupt가 발행하여 종료하겠음");
				return;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {  //자다가 인터럽트 걸렸을 때 실행할 수행문
				e.printStackTrace();
				interrupt();  //인터럽트를 받은 직후 다시 초기화하기 때문에 interrupt를 강제적으로 걸어줘서 상태를 강제해야한다
			}
			
			System.out.println(i);
		}
	}
}
