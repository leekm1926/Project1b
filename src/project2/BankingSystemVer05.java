package project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import project2.ver01.Account;
import project2.ver01.MenuChoice;

//계좌 : account, 계좌번호 : accountNum, 계좌개설 : Make, 이름 : name, 잔액 : change, 입금 : deposit
//출금 : withDraw, 전체계좌정보출력 : inquire, 프로그램종료 exit

public class BankingSystemVer05 implements MenuChoice {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Account ac = new Account(50);
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while (run) {

			ac.showMenu();
			System.out.print("선택:");
			int select = sc.nextInt();
			sc.nextLine();
			switch (select) {

			case MenuChoice.MAKE:
				//ac.makeAccount();
				insert();

				break;
			case MenuChoice.DEPOSIT:
				updateD();
//				ac.depositMoney();
				break;
			case MenuChoice.WITHDRAW:
				updateW();
//				ac.withdrawMoney();
				break;
			case MenuChoice.INQUIRE:
//				ac.showAccInfo();
				select();
				break;
			case MenuChoice.EXIT:
				run = false;
				break;
			}

		}
		System.out.println("프로그램 종료");

		
		
	}
	
	static void select()
	{
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userid = "kosmo";
			String userpw = "1234";

			Connection con = DriverManager.getConnection(url, userid, userpw);
			if (con != null) {
				System.out.println("Oracle DB 연결성공");
				Statement stmt = con.createStatement();
				System.out.print("이름검색 : ");
				String name1 = sc.nextLine();
				String query = "SELECT ACCOUNTNUM, NAME, BALANCE FROM banking_tb WHERE NAME like '%" 
						+name1+"%'";
				
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next())
				{
					String accountNum = rs.getString("ACCOUNTNUM");
					String name = rs.getString("NAME");
					int balance = rs.getInt("BALANCE");
					
					System.out.printf("계좌번호 : %s 이름 : %s 잔고 : %d\n", accountNum, name, balance);
				}
				stmt.close();
				
			} else {
				System.out.println("연결실패 ㅜㅜ");
			}
			

		} catch (SQLException e) {
			System.out.println("SQLException 예외발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException 예외발생");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("DB연결시 예외발생");
			e.printStackTrace();
		}
		
	}
	
	static void insert()
	{
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userid = "kosmo";
			String userpw = "1234";

			Connection con = DriverManager.getConnection(url, userid, userpw);
			
			if (con != null) {
				System.out.println("Oracle DB 연결성공");
				String query = "insert into banking_tb values (?,?,?,seq_banking.nextval)";
				PreparedStatement psmt = con.prepareStatement(query);
				
				Scanner sc = new Scanner(System.in);
				System.out.print("계좌번호:");
				String accountNum = sc.next();
				System.out.print("이름:");
				String name = sc.next();
				System.out.print("잔고:");
				int balance = sc.nextInt();
				psmt.setString(1, accountNum);
				psmt.setString(2, name);
				psmt.setInt(3, balance);
				
				int affected = psmt.executeUpdate();
				System.out.println(affected + "행이 입력되었습니다.");
			
				psmt.close();
			} else {
				System.out.println("연결실패 ㅜㅜ");
			}
			

		} catch (SQLException e) {
			System.out.println("SQLException 예외발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException 예외발생");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("DB연결시 예외발생");
			e.printStackTrace();
		}
		
		
	}
	
	//입금 업데이트
	static void updateD()
	{
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userid = "kosmo";
			String userpw = "1234";

			Connection con = DriverManager.getConnection(url, userid, userpw);
			
			if (con != null) {
				System.out.println("Oracle DB 연결성공");
				String query = "UPDATE banking_tb SET balance=balance + ? WHERE accountNum=?";
				PreparedStatement psmt = con.prepareStatement(query);
				
				
				
				Scanner sc = new Scanner(System.in);
				System.out.print("계좌번호:");
				String accountNum = sc.next();
				System.out.print("입금액:");
				int balance = sc.nextInt() ;
				sc.nextLine();
				
				
				
				psmt.setInt(1, balance);
				psmt.setString(2, accountNum);
				
				int affected = psmt.executeUpdate();
				System.out.println(affected + "행이 입력되었습니다.");
			
				psmt.close();
			} else {
				System.out.println("연결실패 ㅜㅜ");
			}
			

		} catch (SQLException e) {
			System.out.println("SQLException 예외발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException 예외발생");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("DB연결시 예외발생");
			e.printStackTrace();
		}
		
	}
	static void updateW()//출금업데이트
	{
		try {

			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userid = "kosmo";
			String userpw = "1234";

			Connection con = DriverManager.getConnection(url, userid, userpw);
			
			if (con != null) {
				System.out.println("Oracle DB 연결성공");
				String query = "UPDATE banking_tb SET balance=balance - ? WHERE accountNum=?";
				PreparedStatement psmt = con.prepareStatement(query);
				
				
				
				Scanner sc = new Scanner(System.in);
				System.out.print("계좌번호:");
				String accountNum = sc.next();
				System.out.print("출금액:");
				int balance = sc.nextInt() ;
				sc.nextLine();
								
				
				psmt.setInt(1, balance);
				psmt.setString(2, accountNum);
				
				int affected = psmt.executeUpdate();
				System.out.println(affected + "행이 입력되었습니다.");
			
				psmt.close();
			} else {
				System.out.println("연결실패 ㅜㅜ");
			}
			

		} catch (SQLException e) {
			System.out.println("SQLException 예외발생");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException 예외발생");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("DB연결시 예외발생");
			e.printStackTrace();
		}
		
	}


}
