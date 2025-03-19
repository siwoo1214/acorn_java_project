package test1;

public class Thread_sleep {
	public static void main(String[] args) throws InterruptedException{
		long start = System.currentTimeMillis();
		
		Thread.sleep(3000);
		
		System.out.println("스레드는 "+(System.currentTimeMillis()-start)+"초 동안 멈춰있었습니다");
	}
}
