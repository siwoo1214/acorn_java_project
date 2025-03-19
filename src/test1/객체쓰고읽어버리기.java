package test1;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class 객체쓰고읽어버리기 {
    public static void main(String[] args) {
    	ArrayList<Job> list = new ArrayList<>();
    	Job j1 = new Job("유튜버","동영상만들기",10000000);
    	list.add(j1);
    	Job j2 = new Job("개발자","코딩노예가 되",3400000);
    	list.add(j2);
    	Job j3 = new Job("축구선수","본인역할에 최선을 다하기",34000000);
    	list.add(j3);
    	
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ras/Job.txt"))){
			
				for(Job tmp : list) {
					oos.writeObject(tmp);
				}
				oos.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ras/Job.txt"))) {
            while (true) {  // 🚨 파일 끝까지 반복
                try {
                    Job j = (Job) ois.readObject();
//                    int tmp = j.salary;
//                    String tmp2 = j.doWhat;
//                    String tmp3 = j.job;
//                    System.out.println(tmp3+"는 "+tmp2+"를 하고 월급은 "+tmp+"원 입니다");
                    System.out.println(j);
                } catch (EOFException e) {
                    break;  // ✅ 파일 끝에 도달하면 반복문 종료
                } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        } catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

    }

//	@Override
//	public String toString() {
//		return "Test3 []";
//	}
    
    
}
