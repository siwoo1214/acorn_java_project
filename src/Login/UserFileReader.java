package Login;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class UserFileReader {
	
	
	public static List<User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("res/user.txt"))) {
        	
            return (List<User>) ois.readObject();
        } catch (IOException e) {
        	
            System.out.println("❌ 파일 로드 중 오류 발생: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
        	
        	System.out.println("❌ 파일 로드 중 오류 발생: " + e.getMessage());
			return null;
		}
    }

	public static void UserFileReader() {
		List<User> users = loadUsers();
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        }
	}

}
