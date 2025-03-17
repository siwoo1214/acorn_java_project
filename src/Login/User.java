package Login;

import java.io.Serializable;

public class User implements Serializable {
	String id;
	String pwd;
	int coin = 0;

	public User(String id, String pwd, int coin) {
		this.id = id;
		this.pwd = pwd;
		this.coin = coin;
	}

	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	@Override
	public String toString() {
		return  id + "님의 현재 보유 coin: " + coin ;
	}
	
	

}
