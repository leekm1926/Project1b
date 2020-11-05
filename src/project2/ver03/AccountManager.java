package project2.ver03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager implements CustomSpecialRate {

	String accountNum, name; // 계좌번호, 이름
	int balance; // 잔액
	int sel;// 신용신뢰계자선택
	int rate;// 이자
	String grade;// 신용등급
	int customrate; //
	static Scanner sc = new Scanner(System.in);

	Account[] accArr;

	public AccountManager(int num) {
		accArr = new Account[num];
	}

	static int cnt = 0;

	public void showMenu()// 메뉴출력
	{
		boolean run = true;
		while (run) {

			System.out.println("-----Menu-----");
			System.out.print("1.계좌개설\n2.입 금\n3.출 금\n4.계좌정보출력\n5.프로그램종료\n");
			System.out.print("선택:");
			try {

				int select = sc.nextInt();
				if (select > 5) {
					MenuSelectException exc = new MenuSelectException();
					throw exc;

				}
				switch (select) {

				case MenuChoice.MAKE:
					makeAccount();
					break;
				case MenuChoice.DEPOSIT:
					depositMoney();
					break;
				case MenuChoice.WITHDRAW:
					withdrawMoney();
					break;
				case MenuChoice.INQUIRE:
					showAccInfo();
					break;
				case MenuChoice.EXIT:
					run = false;
					break;
				}
			} catch (InputMismatchException e) {
				// e.printStackTrace();
				sc.nextLine();
			} catch (MenuSelectException e) {
				e.printStackTrace();
			}
		}
	}

	public void makeAccount() // 계좌개설을 위한 함수
	{

		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		int sel = sc.nextInt();

		if (sel == 1) {

			System.out.print("계좌번호:");
			String accountNum = sc.next();
			System.out.print("고객이름:");
			String name = sc.next();
			System.out.print("잔고:");
			int balance = sc.nextInt();

			System.out.print("기본이자%(정수형태로입력):");
			int rate = sc.nextInt();

			NormalAccount normal = new NormalAccount(accountNum, name, balance, rate);

			accArr[cnt] = normal;
			cnt++;
		}

		else if (sel == 2) {
			System.out.print("계좌번호:");
			String accountNum = sc.next();
			System.out.print("고객이름:");
			String name = sc.next();
			System.out.print("잔고:");
			int balance = sc.nextInt();

			System.out.print("추가이자%(정수형태로입력):");
			int rate = sc.nextInt();

			System.out.print("신용등급(A,B,C등급):");
			String grade = sc.next();

			HighCreditAccount high = new HighCreditAccount(accountNum, name, balance, rate, grade);

			accArr[cnt] = high;
			cnt++;
		}

		System.out.println("계좌개설이  완료되었습니다.");

	}

	
	public void depositMoney() // 입금
	{
		System.out.println("***입 금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accountNum = sc.next();
		System.out.print("입금액:");

		try {
			int dMoney = sc.nextInt(); // 입금액 : dMoney
			if (dMoney < 0 || dMoney % 500 != 0) {
				MenuSelectException exc = new MenuSelectException();
				throw exc;
			}

			// 계좌번호 일치하는지 확인 후 그 계좌번호에 입금을 한다.
			for (int j = 0; j < accArr.length; j++) {
				balance = accArr[j].getBalance();
				rate = accArr[j].getRate();
				grade = accArr[j].getGrade();

				if (accountNum.equals(accArr[j].getAccountNum())) {

					if (grade != null) {
						if (grade.equals("A")) {
							customrate = CustomSpecialRate.A;
						} else if (grade.equals("B")) {
							customrate = CustomSpecialRate.B;
						} else if (grade.equals("C")) {
							customrate = CustomSpecialRate.C;
						}

						accArr[j].setBalance((int) (Math
								.floor((balance + (balance * rate * 0.01) + (balance * customrate * 0.01) + dMoney))));

					} else {
						accArr[j].setBalance((int) (Math.floor((balance + (balance * (rate * 0.01)) + dMoney))));
					}

				}

				else {
					System.out.println("계좌번호가 틀렸습니다. 처음부터 다시 시작하세요.");
				}

				System.out.println("입금이 완료되었습니다.");
			}

		} 
		catch (InputMismatchException e) {
			sc.nextLine();
		}
		catch (MenuSelectException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e)
		{
			
		}
		

	}

	/*
YES : 금액전체 출금처리
NO : 출금요청취소


	 */
	public void withdrawMoney() // 출금
	{

		System.out.println("***출 금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accountNum = sc.next();
		System.out.print("출금액:");
		
		
		try {
			
		int wMoney = sc.nextInt(); // 출금액 : wMoney
		//출금액이 음수일 경우
		if(wMoney<0 || wMoney%1000 != 0) {
			MenuSelectException exc = new MenuSelectException();
			throw exc;
		}
		
	
		// 계좌번호 일치하는지 확인 후 그 계좌번호에 입금을 한다.
		
			for (int j = 0; j < accArr.length; j++) {
				
				if (accountNum.equals(accArr[j].getAccountNum())) {
					// 출금액이 잔고보다 클 때
					
					if(balance < wMoney)
					{
						// 금액전체를 출금할꺼니 출금요청을 취소할꺼니?
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
						System.out.println("-YES : 금액전체 출금처리");
						System.out.println("-NO : 출금요청취소");
						String A = sc.next();
						
						if(A.equals("YES"))
						{
							accArr[j].setBalance(0);
						}
						else if(A.equals("NO"))
						{
							System.out.println("출금요청이 취소되었습니다.");
						}
						
					}
					else
					accArr[j].setBalance(accArr[j].getBalance() - wMoney);
				}
			
				else {
					System.out.println("처음부터 다시 시작하세요.");
				}
			}
			System.out.println("출금이 완료되었습니다.");
		} 
		catch(InputMismatchException e)
		{
			
		}
		catch (NullPointerException e) {

		}
		catch(MenuSelectException e)
		{
			System.out.println("출금액은 0원 이상이어야 하고 1000원 단위로 출금해야합니다.");
		}
	}

	public void showAccInfo() // 전체계좌정보출력
	{

		for (int i = 0; i < cnt; i++) {

			System.out.println("-----------");
			System.out.println("계좌번호 : " + accArr[i].getAccountNum());
			System.out.println("고객이름 : " + accArr[i].getName());
			System.out.println("잔고 : " + accArr[i].getBalance());
			System.out.println("기본이자 : " + accArr[i].getRate());

			if (accArr[i].getGrade() != null) {
				System.out.println("신용등급(A,B,C등급) : " + accArr[i].getGrade());
			}
			System.out.println("-----------");
		}

		System.out.println("전체계좌정보 출력이 완료되었습니다.");

	}
}
