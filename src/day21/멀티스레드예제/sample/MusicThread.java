package day21.멀티스레드예제.sample;

public class MusicThread extends Thread{
	@Override
	public void run() {
		for(int i=0;i<1000;i++) {
			System.out.println("음악재생중: "+i);
		}		
	}
}
