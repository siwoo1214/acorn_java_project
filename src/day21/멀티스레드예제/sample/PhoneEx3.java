package day21.멀티스레드예제.sample;

public class PhoneEx3 {
	//3. Runnable인터페이스 익명클래스로 구현
	public static void main(String[] args) {
		
		Thread mt = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("음악 재생중: " + i);
				}
			}
		});
		Thread ut = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("비디오 재생중: " + i);
				}
			}
		});
		Thread ct = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("**********찰칵***********");
			}
		});
		
		mt.start();
		ct.start();
		ut.start();
		
	}

}

