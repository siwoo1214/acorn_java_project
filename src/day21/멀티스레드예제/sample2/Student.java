package day21.멀티스레드예제.sample2;

public class Student {

	
	StudyThread studyThread;
	YouTubeThread youtubeThread;
	 MusicThread  musicThread;
	
	
	
	public Student() {
		 
		studyThread  = new StudyThread();
		youtubeThread  = new YouTubeThread();
		musicThread = new MusicThread();
		
		
		
	}
	
	
	
	public void 질문받기() {
		
		System.out.println("질문받았습니다");
		youtubeThread.interrupt();   //유튜브 스레드에 신호보내기
		musicThread.interrupt();   //유튜브 스레드에 신호보내기
	}
	
	
	
	
	// 시작점
	public void run() {
		studyThread.start();
		youtubeThread.start();
		musicThread.start();
		
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//3초 후 질문받기
		질문받기();
		
	}
	
	public static void main(String[] args) {
		 
		Student s  = new  Student();
		s.run();
		

	}

}
