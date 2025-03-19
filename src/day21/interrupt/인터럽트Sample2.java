package day21.interrupt;

class MyThread2 extends Thread {
    public void run() {
        while (true) {
            // 긴 작업 수행 중
            try {
                Thread.sleep(1000);  // 1초 대기
                System.out.println("작업 중...");
            } catch (InterruptedException e) {
                // 인터럽트 발생 시 처리
                System.out.println("자기 자신에게 인터럽트를 걸어 작업을 중단합니다.");
                break;  // 루프 종료
            }

            // 일정 조건을 만족하면 자기 자신에게 인터럽트 걸기
            if (someCondition()) {
                System.out.println("조건을 만족하여 자기 자신에게 인터럽트를 걸겠습니다.");
                interrupt();  // 자기 자신에게 인터럽트 걸기
            }
        }
    }

    private boolean someCondition() {
        // 특정 조건을 체크 (예: 5초가 지났다면 중단)
        return System.currentTimeMillis() % 5000 == 0;
    }
}

public class 인터럽트Sample2 {
    public static void main(String[] args) throws InterruptedException {
        MyThread2 t = new MyThread2();
        t.start();
    }
}
