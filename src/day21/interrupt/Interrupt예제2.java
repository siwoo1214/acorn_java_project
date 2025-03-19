
package day21.interrupt;

import javax.swing.JOptionPane;

public class Interrupt예제2 {

   public static void main( String[] args) throws Exception{

	   Counter  th = new  Counter();
       th.start();       
       System.out.println( " isInterrupted()  " +  th.isInterrupted() );
       
       
       

       String input = JOptionPane.showInputDialog("아무 값이나 입력하세요");
       
       th.interrupt(); // 신호주기
       

       System.out.println( " isInterrupted() " +  th.isInterrupted() );  

   }
}


/*
 
class Counter extends Thread{

    public void run() {
        int i=10;

        while( i !=0  && !isInterrupted() ) {   //인터럽트가 발생되기전까지 반복
              System.out.println( i--);
              for( long x=0 ; x< 2500000000L; x++);  //시간지연
           
        }
   System.out.println(" 카운트가 종료 되었습니다");
   }

}*/