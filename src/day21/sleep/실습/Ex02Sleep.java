package day21.sleep.실습;

public class Ex02Sleep {
	public static void main(String[] args) {
		Counterr th = new Counterr();
	}
}

class Counterr extends Thread {
	@Override
	public void run() {
		for (int i = 10; i < 0; i--) {
			try {
				Thread.sleep(1000);
				System.out.println(i);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		super.run();
	}
}