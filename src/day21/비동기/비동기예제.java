package day21.비동기;

import javax.swing.JOptionPane;

public  class 비동기예제 {
	public static void main(String[] args) throws Exception 	{
		MyThread1 th1 = new MyThread1();
		MyThread2 th2 = new MyThread2();
		th1.start();	
		th2.start();
		
	
	}
}
 
class MyThread1 extends Thread {
	public void run() {
		for(int i=10; i > 0; i--) {
			System.out.println(i);
			try {
				sleep(1000);
			} catch(Exception e ) {}
		}
	}
}



class MyThread2 extends Thread{
	@Override
	public void run() {
		 
			String input = JOptionPane.showInputDialog("아무 값이나 입력하세요."); 
			System.out.println("입력하신 값은 " + input + "입니다.");
	}
}