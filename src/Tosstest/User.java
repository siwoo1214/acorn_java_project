package Tosstest;

public class User {
	public String name;
	public String id;
	public String password;

	public User(String id, String password, String name) {
		this.name = name;
		this.id = id;
		this.password = password;
	}

	public User() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toFileString() {
		return id + "," + password + "," + name;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", 이름: " + name;
	}

}
