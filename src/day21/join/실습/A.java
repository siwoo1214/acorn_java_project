package day21.join.실습;

//1부터 50까지 더하기
public class A extends Thread{
	
	private int sum;
	
	public int getSum() {
		return sum;
	}

	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			sum+=i;
			System.out.println(getName()+" "+i);
		}
	}
	
}
