package project2.ver01;

import java.util.Scanner;

public class Account {

	String accountNum, name; // 계좌번호, 이름
	int balance; // 잔액
	static Scanner sc = new Scanner(System.in);

	Account[] accArr;

	public Account(int num) {
		accArr = new Account[num];
	}

	static int cnt = 0;

	public Account(String accountNum, String name, int balance) {
		this.accountNum = accountNum;
		this.balance = balance;
		this.name = name;
	}

	public void showMenu()// 메뉴출력
	{
		System.out.println("-----Menu-----");
		System.out.print("1.계좌개설\n2.입 금\n3.출 금\n4.계좌정보출력\n5.프로그램종료\n");

	}

	public void makeAccount() // 계좌개설을 위한 함수
	{

		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호:");
		String accountNum = sc.nextLine();

		System.out.print("고객이름:");
		String name = sc.nextLine();

		System.out.print("잔고:");
		int balance = sc.nextInt();

		accArr[cnt] = new Account(accountNum, name, balance);
		cnt++;

		System.out.println("계좌개설이  완료되었습니다.");

	}

	/*
	 * getter/setter
	 */
	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void depositMoney() // 입금
	{
		System.out.println("***입 금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accountNum = sc.next();
		System.out.print("입금액:");
		int dMoney = sc.nextInt(); // 입금액 : dMoney

		// 계좌번호 일치하는지 확인 후 그 계좌번호에 입금을 한다.
		try {
			for (int j = 0; j < accArr.length; j++) {
				if (accountNum.equals(accArr[j].accountNum)) {

					accArr[j].balance += dMoney;
				} else {
					System.out.println("계좌번호가 틀렸습니다. 처음부터 다시 시작하세요.");
				}
			}
			System.out.println("입금이 완료되었습니다.");
		} catch (NullPointerException e) {

		}

	}

	public void withdrawMoney() // 출금
	{

		System.out.println("***출 금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accountNum = sc.next();
		System.out.print("출금액:");
		int wMoney = sc.nextInt(); // 출금액 : wMoney

		// 계좌번호 일치하는지 확인 후 그 계좌번호에 입금을 한다.
		try {
			for (int j = 0; j < accArr.length; j++) {
				if (accountNum.equals(accArr[j].accountNum)) {

					accArr[j].balance -= wMoney;
				} else {
					System.out.println("처음부터 다시 시작하세요.");
				}
			}
		} catch (NullPointerException e) {

		}
		System.out.println("출금이 완료되었습니다.");
	}

	public void showAccInfo() // 전체계좌정보출력
	{

		try {

			for (int cnt = 0; cnt < accArr.length; cnt++) {

				System.out.println("-----------");
				System.out.println("계좌번호:" + accArr[cnt].accountNum);
				System.out.println("고객이름:" + accArr[cnt].name);
				System.out.println("잔고:" + accArr[cnt].balance);
				System.out.println("-----------");
			}
		} catch (NullPointerException e) {
			// 예외처리
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");

	}

}