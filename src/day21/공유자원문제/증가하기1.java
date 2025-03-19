package day21.공유자원문제;

public class 증가하기1 {

	public static void main(String[] args) {
		int a=0;		
		
		a=a+1;
		
		
		System.out.println(  a );
		
		
		
		/*
		 * 어셈블리어    비교
		 * 
		 * section .data
		    a dd 0  ; 정수형 변수 a를 0으로 초기화
		
		   section .text
		    global _start
		
		   _start:
		    mov eax [a]     ; 현재 a의 값을 레지스터에 로드
		    add eax, 1       ; 값을 1 증가
		    mov [a], eax     ; 결과를 다시 변수 a에 저장
		
		    ; 이후에 변수 a를 ,사용하거나 다른 작업을 수행할 수 있음
				
	    */
		
		

	}

}
