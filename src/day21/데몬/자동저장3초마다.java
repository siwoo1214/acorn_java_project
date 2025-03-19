package day21.데몬;

public class 자동저장3초마다   {
	 
  
	
	
	public static void main(String[] args) {
		 
		
		AutoSave  t = new AutoSave ();
		t.setDaemon(true);		// 이 부분이 없으면 종료되지 않는다. , Daemon 확인!!
		                        // 이 부문이 있어야 데몬쓰레드가 됨 (함께 종료된다)
		t.start();
		 
		
		for(int i=1; i <= 10; i++) {
			System.out.println(i + "작업을 진행합니다.");
			try{
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
		 	
		 
		}

		System.out.println("프로그램을 종료합니다.");
	}
}




class AutoSave extends Thread{	
	
	public void autoSave() {
		System.out.println("작업파일이 자동저장되었습니다.");
	}	
	
	
	public void run() {
		while(true) {
			try { 
				Thread.sleep(3 * 1000);	// 3초마다 autoSave 호출함 
			} catch(InterruptedException e) {
				
			}	
			
				autoSave();
			}
		}		
}