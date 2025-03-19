package Login;

import java.util.List;
import java.util.Scanner;

public class Login {

	public static int login() {
		List<User> users = UserFileReader.loadUsers();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("아이디를 입력하세요");
			String id = sc.nextLine();
			System.out.println("비밀번호를 입력하세요");
			String pwd = sc.nextLine();

			User target =UserAuthenticator(id, pwd);
			if(   target!= null) { 
				
				return target.coin;
			}
			
			System.out.println("아이디 혹은 비밀번호를 잘못 입력했습니다. 다시 입력하세요");
		}

	}

	public static User UserAuthenticator(String id, String password) {
		List<User> users = UserFileReader.loadUsers();
		if (users != null) {
			for (User user : users) {
				if (user.getId().equals(id) && user.getPwd().equals(password)) {
					return user;
				}
			}
		}
		return null;
	}

}
