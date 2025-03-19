package 건강관리프로그램;

public class User {

	double height; // 키(cm)
	double weight; // 몸무게(kg)
	int age; // 나이
	String gender; // 성별(남/여)

	// 생성자
	public User(double height, double weight, int age, String gender) {
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.gender = gender;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
