package day21.join;

class SumTask extends Thread {
    private int start;
    private int end;
    private int sum;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
    }
}

public class 합계구하기 {
    public static void main(String[] args) throws InterruptedException {
        // 1부터 50까지의 합을 구할 스레드
        SumTask thread1 = new SumTask(1, 50);
        // 51부터 100까지의 합을 구할 스레드
        SumTask thread2 = new SumTask(51, 100);

        // 스레드 실행
        thread1.start();
        thread2.start();

        // 두 스레드가 작업을 끝내기를 기다린 후
        thread1.join();
        thread2.join();

        // 두 스레드의 결과 합산
        int totalSum = thread1.getSum() + thread2.getSum();
        System.out.println("1부터 100까지의 합은: " + totalSum);
    }
}
