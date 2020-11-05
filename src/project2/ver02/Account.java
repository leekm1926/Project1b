package project2.ver02;

import java.util.Scanner;

public class Account {

	private String accountNum, name; // 계좌번호, 이름
	private int balance; // 잔액
	private int sel;// 신용신뢰계자선택
	private int rate;// 이자
	private String grade;// 신용등급

	static Scanner sc = new Scanner(System.in);

	static int cnt = 0;

	public Account(String accountNum, String name, int balance) {
		this.accountNum = accountNum;
		this.balance = balance;
		this.name = name;

	}

	public int getSel() {
		return sel;
	}

	public void setSel(int sel) {
		this.sel = sel;
	}

	public int getRate() {
		return rate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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

	Account[] accArr;

	public Account(int num) {
		accArr = new Account[num];
	}

}