package day21.멀티스레드예제.sample3;

class Student {
    private Thread musicThread;
    private Thread youtubeThread;
    private Thread studyThread;

    public void 음악듣기() {
        musicThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("음악 듣기 중...");
                         
                        for( int i=1; i<2100000000; i++);
                        
                    }
            }
        });
        musicThread.start();
    }

    public void 유튜브보기() {
        youtubeThread = new Thread(new Runnable() {
            @Override
            public void run() {
               
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("유튜브 보기 중...");
                        
                        for( int i=1; i<2100000000; i++);
                    }
                
            }
        });
        youtubeThread.start();
    }

    public void 공부하기() {
        studyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
                    while (true) {
                        System.out.println("공부하기 중...");
                        for( int i=1; i<2100000000; i++);
                    }
                
            }
        });
        studyThread.start();
    }

    public void 질문받기() {
        System.out.println("질문을 받았습니다! 유튜브와 음악 듣기 스레드 중지 요청");
        musicThread.interrupt();
        youtubeThread.interrupt();
    }

    public void run() {
        음악듣기();
        유튜브보기();
        공부하기();

        // 질문을 받는 것을 시뮬레이션 (3초 후 질문 받기)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        질문받기();
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.run();
    }
}
