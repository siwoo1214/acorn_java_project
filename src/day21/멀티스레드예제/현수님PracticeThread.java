package day21.멀티스레드예제;

public class 현수님PracticeThread {

	public static void main(String[] args) {

		Thread01 th1 = new Thread01();
		th1.start();

		Thread th2 = new Thread(new RunnableYoutubeImp());
		th2.start();

		Thread th3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(1000);
						System.out.println((i + 1) + "번째 음악 재생을 재생합니다.");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

}

class Thread01 extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				sleep(1500);
				System.out.println((i + 1) + "번째 다운로드 완료.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class RunnableYoutubeImp implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(500);
				System.out.println((i + 1) + "번째 영상을 재생합니다.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
