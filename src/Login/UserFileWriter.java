package Login;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserFileWriter {// txt파일에 user id, pwd 저장해주는 

	public static void saveUsers(List<User> users) {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("res/user.txt"))) {
			
			oos.writeObject(users);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 테스트용 사용자 데이터
		List<User> users = new ArrayList<>();
		users.add(new User("user1", "1234",5));
		users.add(new User("user2", "1234",5));
		users.add(new User("user3", "1234",5));

		saveUsers(users);

	}

}
