package day21.json;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class 사용자정보읽어오기_json {

	public static void main(String[] args) {
		    Scanner scanner = new Scanner( System.in);
	
		    
		    System.out.print("아이디를 입력하세요: ");
	        String id = scanner.nextLine();
	        System.out.print("비밀번호를 입력하세요: ");
	        String password = scanner.nextLine();
	        String filePath = "res/acornInfo.json";  // JSON 파일 경로
	        
	        
	        
	        try {
	            // JSON 파일 읽기
	            FileReader reader = new FileReader(filePath);
	            Gson gson = new Gson();
	            
	            Type type = new TypeToken<Map<String, User>>() {}.getType();
	            Map<String, User> users = gson.fromJson(reader, type); 
	            reader.close();
	            
	            //모든 회원 확인하기	            
	            System.out.println("모든 유저 확인하기");
	            //users.forEach( (key, user) -> System.out.println( key + user));
	            
	            for(  Entry<String ,User> user  :  users.entrySet()) {
	            	System.out.println( user);
	            }
	             
	            
	            
	            
	            // Map을 순회하며 id와 password 비교
	            if (users.containsKey(id)) {  // 입력된 id가 Map에 있는지 확인
	         
	            	User user = users.get(id);  // 해당 id의 User 객체 가져오기	             
	                System.out.println(user);
	                
	                if (user.getPassword().equals(password)) {  // 비밀번호 비교
	                    System.out.println("로그인 성공! 환영합니다, " + user.getName() + "님.");
	                } else {
	                    System.out.println("비밀번호가 일치하지 않습니다. 다시 시도해 주세요.");	                    
	                }
	                
	                
	            } else {
	            	
	                System.out.println("입력한 아이디가 존재하지 않습니다.");
	            }
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
