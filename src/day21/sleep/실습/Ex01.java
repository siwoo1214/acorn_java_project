package day21.sleep.실습;

public class Ex01 {
	public static void main(String[] args) {
		System.out.println("3초후에 공개한다");
		
		
		//sleep (스레드 일시정지 시키겠다) 일시정지 큐에 들어갈
		//지정된 시간이 지나면 다시 실행가능한 상태(실행 대기열에 들어감)
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("짜잔 축하합니다! 박시우님!~!~");
	}
}

