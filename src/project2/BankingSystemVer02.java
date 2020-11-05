package project2;

import project2.ver02.MenuChoice;
import project2.ver02.AccountManager;

//계좌 : account, 계좌번호 : accountNum, 계좌개설 : Make, 이름 : name, 잔액 : change, 입금 : deposit
//출금 : withDraw, 전체계좌정보출력 : inquire, 프로그램종료 exit

public class BankingSystemVer02 implements MenuChoice {

	public static void main(String[] args) {

		AccountManager accountmanager = new AccountManager(50);

		accountmanager.showMenu();
	}

}
