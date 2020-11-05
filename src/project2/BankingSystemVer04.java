package project2;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import project2.ver04.AccountManager;
import project2.ver04.AutoSaverT;
import project2.ver04.MenuChoice;
import project2.ver04.MenuSelectException;


//계좌 : account, 계좌번호 : accountNum, 계좌개설 : Make, 이름 : name, 잔액 : change, 입금 : deposit
//출금 : withDraw, 전체계좌정보출력 : inquire, 프로그램종료 exit

public class BankingSystemVer04 implements MenuChoice {
	public static int thread = 0;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
	AccountManager accountmanager = new AccountManager();
	accountmanager.save();
	AutoSaverT saverT = null;
	
		boolean run = true;
		while (run) {

			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■MENU■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.print("1.계좌개설 2.입 금 3.출 금 4.계좌정보출력 5.저장옵션 6.프로그램종료\n");
			System.out.print("선택:");
			try {

				int select = sc.nextInt();
				if (select > 6) {
					MenuSelectException exc = new MenuSelectException();
					throw exc;

				}
				switch (select) {
				case MenuChoice.MAKE:
					accountmanager.makeAccount();
//					accountmanager.io();
					break;
				case MenuChoice.DEPOSIT:
					accountmanager.depositMoney();
					break;
				case MenuChoice.WITHDRAW:
					accountmanager.withdrawMoney();
					break;
				case MenuChoice.INQUIRE:
//					accountmanager.save();
					accountmanager.showAccInfo();
					break;
				case MenuChoice.SAVE:
					//인터럽트 후 다시 실행할 때 쓰레드가 꺼져있는지 확인하고 꺼져있으면 새로운 객체를 만들어서 새 쓰레드를 생성한다.
					if(thread==0) {
					saverT=new AutoSaverT(accountmanager);
					}
					accountmanager.autosave(saverT);
//					}catch(Exception e) {
//						e.printStackTrace();
//					}
					break;
				case MenuChoice.EXIT:
					run = false;
					accountmanager.io();
					break;
				}
			} catch (InputMismatchException e) {
				// e.printStackTrace();
				System.out.println("잘못된 문자를 입력하였습니다.");
				sc.nextLine();
			} catch (MenuSelectException e) {
				e.printStackTrace();
			}
		}
	
	
//		accountmanager.save();
//		if(saverT.getState()==Thread.State.TERMINATED) {
//			saverT = new AutoSaverT(accountmanager);
//		}
//		accountmanager.showMenu(saverT);
	}
		

}
