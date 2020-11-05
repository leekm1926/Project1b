package project2.ver04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import project2.BankingSystemVer04;

public class AccountManager implements CustomSpecialRate {

	HashSet<Account> hSet = new HashSet<Account>();

	String accountNum, name; // 계좌번호, 이름
	int balance; // 잔액
	int sel;// 신용신뢰계자선택
	int rate;// 이자
	String grade;// 신용등급
	int customrate;
	static Scanner sc = new Scanner(System.in);

	public void makeAccount() // 계좌개설을 위한 함수
	{

		System.out.println("■■■■■■■■■■■■■■■■■■■■신규계좌개설■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("▣▣▣▣계좌선택▣▣▣▣");
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

			// set은 오버라이드만 잘 해놓으면 이터레이터를 하지 않아도 알아서 찾아줌
			boolean b = hSet.add(normal);

	
			if (b == false) {
				System.out.println("중복계좌 발견됨, 덮어쓸까요?(y or n)");
				String yes = sc.next();
				if (yes.equals("y")) {
					hSet.remove(normal);
					hSet.add(normal);
					System.out.println("계좌를 덮었습니다.");
				} else if (yes.equals("n")) {
					System.out.println("취소되었습니다.");

				}
			}

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

			boolean b = hSet.add(high);

			if (b == false) {
				System.out.println("중복계좌 발견됨, 덮어쓸까요?(y or n)");
				String yes = sc.next();
				if (yes.equals("y")) {
					hSet.remove(high);
					hSet.add(high);
					System.out.println("계좌를 덮었습니다.");
				} else if (yes.equals("n")) {

					System.out.println("취소되었습니다.");
				}
			}
		}
	}

	public void depositMoney() // 입금
	{
		System.out.println("▣▣▣▣입금▣▣▣▣");
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
			boolean flag = false;
			// 계좌번호 일치하는지 확인 후 그 계좌번호에 입금을 한다.
			for (Account arr : hSet) {

				if (accountNum.equals(arr.getAccountNum())) {
					if (arr.getGrade()!= null) {
						if (arr.getGrade().equals("A")) {
							customrate = CustomSpecialRate.A;
						} else if (arr.getGrade().equals("B")) {
							customrate = CustomSpecialRate.B;
						} else if (arr.getGrade().equals("C")) {
							customrate = CustomSpecialRate.C;
						}

						arr.setBalance((int) (Math.floor((arr.getBalance() + (arr.getBalance() * arr.getRate() * 0.01)// 소수점을
																														// 버리기위해
								+ (arr.getBalance() * customrate * 0.01) + dMoney))));

						System.out.println("입금이 완료되었습니다.");
						flag=true;
						break;
					} else {
						arr.setBalance((int) (Math
								.floor((arr.getBalance() + (arr.getBalance() * (arr.getRate() * 0.01)) + dMoney))));
						System.out.println("입금이 완료되었습니다.");
						flag=true;
						break;
					}
					
				}

				else {
				}

				
			}
			if(flag==false) {
				System.out.println("계좌번호가 틀렸습니다. 처음부터 다시 시작하세요.");
				
			}

		} catch (InputMismatchException e) {
			sc.nextLine();
		} catch (MenuSelectException e) {
			System.out.println("입금액은 0원 이상이어야 하고 500원 단위로 입금해야합니다.");
		} catch (NullPointerException e) {

		}

	}

	/*
	 * YES : 금액전체 출금처리 NO : 출금요청취소
	 * 
	 * 
	 */
	public void withdrawMoney() // 출금
	{

		System.out.println("▣▣▣▣출금▣▣▣▣");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accountNum = sc.next();
		System.out.print("출금액:");
		
		try {

			int wMoney = sc.nextInt(); // 출금액 : wMoney
			// 출금액이 음수일 경우
			if (wMoney < 0 || wMoney % 1000 != 0) {
				MenuSelectException exc = new MenuSelectException();
				throw exc;
			}

			// 계좌번호 일치하는지 확인 후 그 계좌번호에 입금을 한다.

			boolean flag = false;
			for (Account arr : hSet) {

				if (accountNum.equals(arr.getAccountNum())) {
					// 출금액이 잔고보다 클 때
					if (arr.getBalance() < wMoney) {
						// 금액전체를 출금할꺼니 출금요청을 취소할꺼니?
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
						System.out.println("-YES : 금액전체 출금처리");
						System.out.println("-NO : 출금요청취소");
						String A = sc.next();

						if (A.equals("YES")) {
							arr.setBalance(0);
							System.out.println("출금이 완료되었습니다.");
						} 
						else if (A.equals("NO")) {
							System.out.println("출금요청이 취소되었습니다.");
							break;
						}

					} 
					else {
						arr.setBalance(arr.getBalance() - wMoney);
					System.out.println("출금이 완료되었습니다.");
					flag=true;
					break;
					}
				}

			}
//			if(flag==false){
//				//System.out.println("처음부터 다시 시작하세요.");
//				System.out.println("전체 출금이 완료되었습니다.");
//			}
		} catch (InputMismatchException e) {

		} catch (NullPointerException e) {

		} catch (MenuSelectException e) {
			System.out.println("출금액은 0원 이상이어야 하고 1000원 단위로 출금해야합니다.");
		}
	}

	public void showAccInfo() // 전체계좌정보출력
	{

		for (Account arr : hSet) {

			System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
			System.out.println("계좌번호 : " + arr.getAccountNum());
			System.out.println("고객이름 : " + arr.getName());
			System.out.println("잔고 : " + arr.getBalance());
			System.out.println("기본이자 : " + arr.getRate());

			if (arr.getGrade() != null) {
				System.out.println("신용등급(A,B,C등급) : " + arr.getGrade());
			}
			System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		}

		System.out.println("전체계좌정보 출력이 완료되었습니다.");

	}

	public void io() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/project2/ver04/AccountInfo.obj"));

//			for (Account arr : hSet) {
//				out.writeObject(arr);
//			}
			out.writeObject(hSet);

			out.close();
		} catch (Exception e) {
			System.out.println("예외발생" + e);
		}

	}

	public void save() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/project2/ver04/AccountInfo.obj"));

			hSet = (HashSet<Account>) in.readObject();
			in.close();

		} catch (Exception e) {
			System.out.println("불러오기 오류");
		}

	}

	// 스레드에서 호출할용
	public void call() {

		try {
			Iterator<Account> itr = hSet.iterator();
			PrintWriter out = new PrintWriter(new FileWriter("src/project2/ver04/AutoSaveAccount.txt"));

			while (itr.hasNext()) {
				Account acc = itr.next(); // next객체를 가져와서 acc객체를 출력해준다
					if (acc instanceof NormalAccount) {
						out.printf("계좌번호 : %s, 이름 : %s, 잔고 : %d, 이자 : %d\n", acc.getAccountNum(), acc.getName(),
								acc.getBalance(), acc.getRate());
					}
					else if (acc instanceof HighCreditAccount) {
						out.printf("계좌번호 : %s, 이름 : %s, 잔고 : %d, 이자 : %d, 등급 : %s\n", acc.getAccountNum(), acc.getName(),
								acc.getBalance(), acc.getRate(), acc.getGrade());

					}

				
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// autosave() 자동저장을 할지 안할지 선택
	public void autosave(AutoSaverT savert){

		System.out.println("저장옵션을 선택하세요. (1.자동저장 ON)(2.자동저장 Off)");

		int number = sc.nextInt();
		// 쓰레드를 켤지 말지
		if (number == 1) {

			if (savert.getState() == Thread.State.NEW) {
				savert.setDaemon(true);
				savert.start();
				BankingSystemVer04.thread=1;
			} else
				System.out.println("이미 자동저장중입니다.");

		} else if (number == 2) {
			savert.interrupt(); // 인터럽트 실행하면 쓰레드 클래스에 캐치문으로 이동 후 실행
			BankingSystemVer04.thread=0;
		}
	}

}
