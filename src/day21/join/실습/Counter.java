package day21.join.실습;

public class Counter extends Thread{
	@Override
	public void run() {
		for(int i=10; i>0; i--) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
}
