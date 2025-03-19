package day21.멀티스레드예제.sample;

public class PhoneEx2 {
	//2. Runnable인터페이스 클래스에 상속받아 구현
	public static void main(String[] args) {
		
		Thread mt = new Thread(new MusicThread());
		Thread ut = new Thread(new YoutubeThread());
		Thread ct = new Thread(new CameraThread());
		
		mt.start();
		ct.start();
		ut.start();
		
	}

}

class CameraThread2 implements Runnable{
	public void run() {
		System.out.println("**********찰칵***********");
	}
}
class MusicThread2 implements Runnable{
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("음악 재생중: " + i);
		}
	}
}
class YoutubeThread2 implements Runnable{
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("비디오 재생중: " + i);
		}
	}
}
