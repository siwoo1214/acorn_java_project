package Toss;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<User> users = new ArrayList<>();
	static ArrayList<Account> accounts = new ArrayList<>();
	static ArrayList<History> histories = new ArrayList<>();
	static User LoggedInUser = null; // 유저가 로그인한 상태를 반환
	static Account AccountIn = null; // 계좌에 들어가 있는 상태를 반환

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AccountManager o = new AccountManager();
		StockAccount sa = new StockAccount();
		EventManage E = new EventManage();
		while (true) {
			if (LoggedInUser == null) {
				System.out.println("1. 회원가입 2. 로그인 3. 종료");
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					signUp(sc);
					break;
				case 2:
					login(sc);
					break;
				case 3:
					System.out.println("프로그램 종료");
					System.exit(0);
				default:
					System.out.println("잘못된 입력입니다");
					return;
				}
			} else {
				if (AccountIn == null) {
					System.out.println("1.계좌개설 2.계좌연결 3.계좌해지 4.계좌조회 5.계좌정보변경 6.이벤트 7.사용자로그아웃");
					int menuchoice = Integer.parseInt(sc.nextLine());
					switch (menuchoice) {
					case 1:
						o.OpenedAccount(sc);
						break;
					case 2:
						o.ConnectAccount(sc);
						break;
					case 3:
						o.CancelAccount(sc);
						break;
					case 4:
						o.CheckAccount(sc);
						break;
					case 5:
						o.UpdateAccount(sc);
						break;
					case 6:
						E.Event();
						break;
					case 7:
						logout(sc);
						break;
					default:
						System.out.println("잘못된 입력입니다");
						return;
					}
				} else {
					if (Main.AccountIn.getAccountType().equals("입출금 계좌")) {
						System.out.println("1.입출금 2.입출금내역 5.계좌로그아웃");
						int accountchoice = Integer.parseInt(sc.nextLine());
						switch (accountchoice) {
						case 1:
							dewiaccount(sc);
							break;
						case 2:
							o.AccountHistory(sc);
							break;
						case 3:
							break;
						case 4:
							break;
						case 5:
							accountlogout(sc);
							break;
						default:
							System.out.println("잘못된 입력입니다");
							return;
						}
					} else if (Main.AccountIn.getAccountType().equals("주식 계좌")) {
						System.out.println("1.주식구매  2.주식판매  3.주식조회 4.계좌로그아웃");
						int choice = Integer.parseInt(sc.nextLine());
						switch (choice) {
						case 1:
							sa.buyStock();
							break;
						case 2:
							sa.sellStock();
							break;
						case 3:
							sa.stockCheck();
							break;
						case 4:
							accountlogout(sc);
							break;
						default:
							System.out.println("잘못된 입력입니다");
							return;
						}
					}
				}
			}
		}
	}

	static void signUp(Scanner sc) {
		System.out.println("이름을 입력하세요:");
		String name = sc.nextLine();
		System.out.println("아이디를 입력하세요:");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요:");
		String password = sc.nextLine();

		for (User User : users) {
			if (User.id.equals(id)) {
				System.out.println("이미 존재하는 아이디입니다.");
				return;
			}
		}
		users.add(new User(name, id, password));
		System.out.println("회원가입 성공!");
	}

	static void login(Scanner sc) {
		System.out.println("아이디를 입력하세요:");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요:");
		String password = sc.nextLine();

		for (User User : users) {
			if (User.id.equals(id) && User.password.equals(password)) {
				LoggedInUser = User;
				System.out.println(User.name + "님 반갑습니다!");
				return;
			}
		}
		System.out.println("로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다");
	}

	static void logout(Scanner sc) {
		System.out.println("사용자 로그아웃");
		LoggedInUser = null;
	}

	static void accountlogout(Scanner sc) {
		System.out.println("계좌 로그아웃");
		AccountIn = null;
	}

	static void dewiaccount(Scanner sc) {
		if (!(AccountIn instanceof BankAccount)) {
			System.out.println("입출금이 가능한 계좌가 없습니다.");
			return;
		}

		System.out.println("1. 입금 2. 출금");
		int choice = Integer.parseInt(sc.nextLine());

		if (choice == 1) { // 입금
			System.out.println("받는 사람 이름:");
			String receiverName = sc.nextLine();
			System.out.println("받는 사람 계좌번호:");
			long receiverAccountNum = Long.parseLong(sc.nextLine());
			System.out.println("입금 금액:");
			int amount = Integer.parseInt(sc.nextLine());

			((BankAccount) AccountIn).deposit(receiverName, receiverAccountNum, amount);

		} else if (choice == 2) { // 출금
			System.out.println("출금 금액:");
			int amount = Integer.parseInt(sc.nextLine());

			((BankAccount) AccountIn).withdraw(amount);
		}
	}

}
