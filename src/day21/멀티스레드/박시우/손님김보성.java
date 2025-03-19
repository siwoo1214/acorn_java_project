package day21.멀티스레드.박시우;

public class 손님김보성 {
    static final int his_patience = 5000; // 5초 이상 기다리면 폭발

    public static void main(String[] args) {
        푸드코트중식당 chinafood = new 푸드코트중식당();
        푸드코트한식당 koreanfood = new 푸드코트한식당();

        chinafood.start();
        koreanfood.start();

        try {
            chinafood.join(); // 중식당이 끝날 때까지 대기
            koreanfood.join(); // 한식당이 끝날 때까지 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
