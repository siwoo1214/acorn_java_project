package day21.멀티스레드예제.sample;

public class PhoneEx {
	//1. Thread클래스 상속받아 재정의

	
	public static void main(String[] args) {
		MusicThread mt = new MusicThread();
		YoutubeThread ut = new YoutubeThread();
		CameraThread ct = new CameraThread();
		
		mt.start();
		ct.start();
		ut.start();
		
	}

}
