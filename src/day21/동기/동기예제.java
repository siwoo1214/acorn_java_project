package day21.동기;

import javax.swing.JOptionPane;

public  class 동기예제 {
	public static void main(String[] args) throws Exception
	{
		
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요."); 
		System.out.println("입력하신 값은 " + input + "입니다.");

		
		// 사용자 입력을 받을 때 까지 기다린다.		
		for(int i=10; i > 0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch(Exception e ) {}
		}
	}
}