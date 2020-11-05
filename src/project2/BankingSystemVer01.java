package project2;

import java.util.Scanner;

import project2.ver01.Account;
import project2.ver01.MenuChoice;

//계좌 : account, 계좌번호 : accountNum, 계좌개설 : Make, 이름 : name, 잔액 : change, 입금 : deposit
//출금 : withDraw, 전체계좌정보출력 : inquire, 프로그램종료 exit

public class BankingSystemVer01 implements MenuChoice {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Account ac = new Account(50);
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while (run) {

			ac.showMenu();
			System.out.print("선택:");

			int select = sc.nextInt();

			switch (select) {

			case MenuChoice.MAKE:
				ac.makeAccount();

				break;
			case MenuChoice.DEPOSIT:
				ac.depositMoney();
				break;
			case MenuChoice.WITHDRAW:
				ac.withdrawMoney();
				break;
			case MenuChoice.INQUIRE:
				ac.showAccInfo();
				break;
			case MenuChoice.EXIT:
				run = false;
				break;
			}

		}
		System.out.println("프로그램 종료");
	}

}
