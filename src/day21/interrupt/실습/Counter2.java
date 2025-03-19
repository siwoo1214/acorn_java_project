package day21.interrupt.실습;

public class Counter2 extends Thread{
	@Override
	public void run() {
		int i=10;
		//isInterrupted(); 활용
		//false => interrupt 안 걸림
		//true => interrupt 걸림
		
		while(i!=0 && !isInterrupted()) {  //조건이 만족할 동안 반복하겠다
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				//자다가 깨면 인터럽트가 들어와서 깨어남
				//인터럽트를 초기화 (false값으로 만듬)
				//종료(return)
				//다시 interrupt 걸기;
				interrupt(); //다시 false값으로 됬던걸 또 interrupt를 걸어서 멈춤
			}
			
			System.out.println(i--);
		}
	}
}
