package day21.멀티스레드.박시우;

import java.util.Random;
import java.util.Scanner;

public class 푸드코트중식당 extends Thread {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        System.out.println("보성이가 먹고싶은 중식은?");
        String menu = sc.next();
        
        long startTime = System.currentTimeMillis(); // 🚀 시작 시간 저장
        int maxCookingTime = rd.nextInt(10) + 1;  

        for (int i = 1; i < maxCookingTime; i++) {
        	
        	
            if (System.currentTimeMillis() - startTime > 손님김보성.his_patience) {
                System.out.println(menu+" 조리 도중 보성님이 폭발하여 요리를 중단합니다...");
                interrupt();
                return; 
            }
            
            if(isInterrupted()) {
        		System.err.println("김보성 폭발 !");
        		return;
        	}

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("중식당: 조리 도중 손님이 폭발하여 요리를 중단합니다...");
                return;
            }

            System.out.println("보성이가 먹고싶어하는 " + menu + "을 중식당에서 열심히 조리중입니다 ...");
        }

        System.out.println(menu + "를 맛있게 먹고 나갔답니다~");
    }
}
