package day21.interrupt;

class MyThread extends Thread {
    public void run() {
        // 작업을 시작하기 전에 인터럽트 상태 확인
        if (Thread.interrupted()) {
            System.out.println("처음에 인터럽트 상태가 있었습니다.");
        } else {
            System.out.println("처음에는 인터럽트 상태가 아닙니다.");
        }

        // 인터럽트 상태를 설정
        interrupt();
        
        
        // 인터럽트 상태를 확인하고 리셋
        if (Thread.interrupted()) {
            System.out.println("인터럽트가 걸린 상태입니다.");
        }else {
        	System.out.println("인터럽트가 걸린 상태 아닙니다.");
        }

        // 이제 인터럽트 상태는 리셋되었으므로 다시 설정할 수 있음
        interrupt();
        
        // 마지막으로 인터럽트 상태 확인
        if (Thread.interrupted()) {
            System.out.println("다시 인터럽트가 걸렸습니다.");
        } else {
            System.out.println("인터럽트 상태가 리셋된 후 다시 확인.");
        }
    }
}

public class 인터럽트Sample {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
