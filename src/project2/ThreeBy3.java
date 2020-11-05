package project2;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class ThreeBy3 {
	/*
	 * 셔플이 완벽하지않아서 수정중입니다.
	 */
	static Scanner sc = new Scanner(System.in);
	static String game[][] = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "X" } };
	static int x, y;

	public static void showBoard() {
		// System.out.println("배열 초기값:" + game);
		System.out.println("=========");
		System.out.println(game[0][0] + " " + game[0][1] + " " + game[0][2]);
		System.out.println(game[1][0] + " " + game[1][1] + " " + game[1][2]);
		System.out.println(game[2][0] + " " + game[2][1] + " " + game[2][2]);
		System.out.println("=========");

	}

	// 셔플부분 보완필요.
	public static void shuffle() {// 위, 아래, 오른쪽, 왼쪽 4가지로 섞이는 4개의 경우의 수
		Random rand = new Random();
		int mixA = 0;
		// for문을 while안에 넣기
		while (mixA <= 3) {
			int mix = rand.nextInt(3);// 0~3 4가지 숫자중 하나 초기화
			for (int i = 0; i < 1; i++) {
				if (mix == 0) {
					// System.out.println("왼쪽");
					moveLeft(true);
				} else if (mix == 1) {
					// System.out.println("오른쪽");
					moveRight(true);
				} else if (mix == 2) {
					// System.out.println("위");
					moveUp(true);
				} else if (mix == 3) {
					// System.out.println("아래");
					moveDown(true);
				}
			}
			mixA++;

		}

	}

	// x인덱스 찾는함수
	public static void findX() {
		for (int i = 0; i < game.length; i++) {
			for (int j = 0; j < game[i].length; j++) {
				if (game[i][j].equals("X")) {
					x = i;
					y = j;

				}
			}
		}

	}

	public static void moveLeft(boolean flag) {
		if (y <= 0) {
			if (flag == true) {
				moveRight(true);
			} else {
				System.out.println("----이동불가----");
			}
		} else {
			String temp = game[x][y - 1];// x,y-1(8)을 temp에 넣어준다
			game[x][y - 1] = game[x][y];// X가 8 자리에 들어간다
			game[x][y] = temp;// temp가 X자리에 들어간다

		}

		// findX();

	}

	public static void moveRight(boolean flag) {
		if (y >= 2) {
			if (flag == true) {
				moveLeft(true);
			} else {
				System.out.println("---이동불가----");
			}
		} else {
			String temp = game[x][y];
			game[x][y] = game[x][y + 1];
			game[x][y + 1] = temp;
		}
	}

	public static void moveUp(boolean flag) {
		if (x <= 0) {
			if (flag == true) {
				moveDown(true);
			} else {
				System.out.println("---이동불가----");
			}
		} else {
			String temp = game[x - 1][y];
			game[x - 1][y] = game[x][y];
			game[x][y] = temp;

		}

	}

	public static void moveDown(boolean flag) {
		if (x >= 2) {
			if (flag == true) {
				moveUp(true);
			} else {
				System.out.println("---이동불가----");
			}
		} else {
			String temp = game[x + 1][y];
			game[x + 1][y] = game[x][y];
			game[x][y] = temp;
		}
	}

	static String mArr[][] = { { "1", "2", "3" }, { "4", "5", "6" }, { "7", "8", "X" } };

	public static void startGame() {

		findX();// 셔플전 X 고정시키기 위해
		shuffle();
		findX();// 셔플 후 X 좌표잡기

		while (true) {

			showBoard();
			System.out.println("[이동] a: Left / d:Right / w:Up / s:Down");
			System.out.println("[종료] x :Exit");
			System.out.print("키를 입력해주세요!");
			String choiceKey = sc.nextLine();

			if (choiceKey.equalsIgnoreCase("d")) {
				if (y == 2) {
					System.out.println("----이동불가----");
				} else {
					String temp = game[x][y + 1];// x,y-1(8)을 temp에 넣어준다
					game[x][y + 1] = game[x][y];// X가 8 자리에 들어간다
					game[x][y] = temp;// temp가 X자리에 들어간다
//               x=x;
					y = y + 1;

					// findX();
					// System.out.println(x + " " + y);
				}
			}
			if (choiceKey.equalsIgnoreCase("a")) {
				if (y == 0) {
					System.out.println("---이동불가----");
				} else {
					String temp = game[x][y];
					game[x][y] = game[x][y - 1];
					game[x][y - 1] = temp;
//               x=x;
					y = y - 1;
					// findX();
					// System.out.println(x + " " + y);
				}
			}
			if (choiceKey.equalsIgnoreCase("s")) {
				if (x == 2) {
					System.out.println("---이동불가----");
				} else {
					String temp = game[x + 1][y];
					game[x + 1][y] = game[x][y];
					game[x][y] = temp;
					x = x + 1;
					// y=y;
					// findX();
					// System.out.println(x + " " + y);
				}
			}
			if (choiceKey.equalsIgnoreCase("w")) {
				if (x == 0) {
					System.out.println("---이동불가----");
				} else {
					String temp = game[x][y];
					game[x][y] = game[x - 1][y];
					game[x - 1][y] = temp;
					x = x - 1;
//               y=y;
					// findX();
					// System.out.println(x + " " + y);
				}
			}
			if (choiceKey.equalsIgnoreCase("x")) {
				System.out.println("--게임 종료--");
				break;
			}
			boolean flag = Objects.deepEquals(game, mArr);
			if (flag == true) {
				showBoard();
				System.out.println("==정답입니다!==");
				System.out.println("재시작 하시겠습니까?(y/Y는 재시작, 그 외는 종료)");
				Scanner sc = new Scanner(System.in);
				choiceKey = sc.nextLine();

				if (choiceKey.equalsIgnoreCase("y")) {
					startGame();
				} else {
					System.out.println("--게임종료--");
					System.exit(0);
				}
			}

		}

	}

	public static void main(String[] args) {

		startGame();
	}
}
