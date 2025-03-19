package day21.공유자원문제;

 

public class 비동기문제_공유자원해결3 {
	
    // 인스턴스 변수로 변경
	private int gIndex = 0;
	
	public void print() {
		
	        int index = 0;  // 스택영역  <= 각 스레드마다 스택공간 확보
			
	        Thread th = Thread.currentThread();		
	        for (int i = 0; i < 100; i++){
	            try {
	                Thread.sleep(20);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            
			
	            //메소드 전체에 동기화 진행, but 단위가 크기때문에 더디다
	            synchronized (this) {  // this로 동기화
	                index++;
	                gIndex++;			
	                System.out.printf("%s[%d] : %d, index: %d , gIndex: %d\n", th.getName(), th.getId(), i + 1, index, gIndex);
	            }
	            
	            
	        }		
	  }
	
	
	

    public static void main(String[] args){
		
        // 객체를 생성
        비동기문제_공유자원해결3 instance = new 비동기문제_공유자원해결3();

        // 인터페이스 구현한 객체
        Runnable r = new Runnable(){

            @Override
            public void run() {				
            	instance.print();
            }	 
        };
		
        Thread th1 = new Thread(r);
        Thread th2 = new Thread(r);
        Thread th3 = new Thread(r);
		
        th1.start();
        th2.start();
        th3.start();
    }

   
}
