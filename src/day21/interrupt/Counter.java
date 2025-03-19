package day21.interrupt;

/*
class Counter   extends Thread {	
	
	public void run() {
		
		for(int i=10; i > 0; i--) { 
				try {
					sleep(1000);
					System.out.println(   i  + "!!");
				} catch (InterruptedException e) {				 
					  	                          // 인터럽트 발생했을 때 종료하게 하기	   인터럽트가 발생한다고 멈추지 않는다 ,쓰레드가 멈추지 않는다.
				  // e.printStackTrace();        //인터럽트게 발생했을 때 해야할 일 코드로 작성
				   System.out.println("인터럽트 발생 하여 종료하겠음");
				   return;	
				}			 
		} 
		 
	}
}
*/
class Counter extends Thread {
    public void run() {
        for (int i = 10; i > 0; i--) {
            // isInterrupted()로 체크하여 인터럽트 발생 시 즉시 종료
            if (isInterrupted()) {
                System.out.println("인터럽트 발생하여 종료하겠음!!");
                return;
            }

            try {
                sleep(1000);
                System.out.println(i + "!!");
            } catch (InterruptedException e) {
                // sleep 중 인터럽트 발생 시 예외 처리
                System.out.println("인터럽트 발생하여 종료하겠음");
                return;
            }
        }
    }
}
