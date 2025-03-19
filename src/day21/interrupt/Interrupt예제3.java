
package day21.interrupt;

import javax.swing.JOptionPane;




public class Interrupt예제3 {

   public static void main( String[] args) throws Exception{

	   Counter2  th = new  Counter2();
       th.start();
       System.out.println( " isInterrupted()  " +  th.isInterrupted() );

       String input = JOptionPane.showInputDialog("아무 값이나 입력하세요");
       th.interrupt(); // 신호주기

       System.out.println( " isInterrupted() " +  th.isInterrupted() );  

   }
}

/*
 *  
 *  Thread.sleep() 메서드를 호출하는 동안
 *  인터럽트가 발생하면 InterruptedException이 던져지고, 
 *  그 시점에서 인터럽트 상태가 초기화 됨(즉, isInterrupted()가 false로 설정됩니다).
 *  인터럽트 상태를 유지하고 싶다면,   다시 interrupt()를 호출하여 인터럽트 상태를 설정해야 함 
  * 
 */



//당장 중요한 것은 아니다 !
//편하게 보자 

class Counter2 extends Thread{

    public void run() {
        int i=10;

        while( i !=0  &&  !isInterrupted() ) {   //인터럽트가 발생되기전까지 반복
        	
              System.out.println( i--);
              try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block   // 잠에서 깨어난 경우   
				 e.printStackTrace();
				 //  interrupt(); //  주석 부분 		 ( 이 부분의  의미 !!)				 
				// return;
			}
           
        }
        
        
   System.out.println( "인터럽트상테:"+ isInterrupted());     
   System.out.println(" 카운트가 종료 되었습니다");
   }

}