package day21.join;

 

public  class Join미사용 {
	public static void main(String[] args)  	{		 
 
	 
		Counter  counter = new Counter ();
		counter.start();		
		
		//시간지연을 시키기 위해 추가된 코드 
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		 
			e.printStackTrace();
		}			 	 
	 	
		
		System.out.println("프로그램 종료");
	}
}


 