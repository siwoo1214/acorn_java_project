package day21.데몬;

 

public  class 데몬예제 {
	public static void main(String[] args)  	{
		 
 
		//스레드 생성
		MyDaemon th1 = new MyDaemon();
	 	th1.setDaemon(true);   // 데몬스레드로 생성, 자동으로 메인 스레드가 끝나면 종료됨
	 	
	 	//스레드 시작 !!! 주의 
		th1.start();		 
		System.out.println("main 종료");
		 
	 
	}
}




class MyDaemon extends Thread {	
	public void run() {
		for(int i=1; i <=10; i++) {		 
				try {
					sleep(2000);
					System.out.println( "2초간 잠자는 중 !!!!!");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 
				}			 
		}
	}
}