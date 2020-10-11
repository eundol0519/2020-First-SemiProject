/**
	Date : 2020-06-16
	Author : 김태석, 오은희, 윤희영, 임형민
	Description : clothUI
	Version : 1.0
*/
package ui;

import java.util.Scanner;

import action.Action;
import action.CheckWeatherAction;
import action.RecommendAccAction;
import action.RegistEarringAction;
import action.RegistTopClothAction;
import action.RegistUnderClothAction;
import action.ReistHatAction;
import action.ReistNecklaceAction;
import action.ReistShoesAction;
import action.WeatherAction;
import connection.DBConnection;
import controller.Controller;
import util.ConsoleUtil;

public class ClothUI {

	public static void main(String[] args) {
		boolean run = true;
		Scanner sc = new Scanner(System.in);

		DBConnection.DBconnect();

		Controller ctrl = new Controller();
		ConsoleUtil console = new ConsoleUtil();

		int menu = 0;
		int select = 0;
		do {
			System.out.println("┌──────────────────────┐");
			System.out.println("| 선택하실 메뉴를 골라주세요. |");
			System.out.println("└──────────────────────┘");

			System.out.println("1.내가 가진 옷 추가하기\t2.내가 가진 악세서리 추가하기\n3.오늘 입을 옷 고르기\t\t4.프로그램 종료");
			System.out.print(">>  ");
			menu = sc.nextInt();
			System.out.println();
			Action action = null;

			switch (menu) {
			case 1: // 내가 가진 옷 추가하기
				boolean clothRun;
				do {
					clothRun = false;
					System.out.println("1.상의 추가하기\n2.하의 추가하기");
					System.out.print(">>  ");
					select = sc.nextInt();
					System.out.println();
					switch (select) {
					case 1:
						// 상의 추가 메소드
						action = new RegistTopClothAction();
						break;
					
					case 2:
						// 하의 추가 메소드
						action = new RegistUnderClothAction();
						break;
					
					default:
						System.out.println("잘못된 입력입니다. 다시 입력 해주세요.");
						clothRun = true;
						break;
					}// end switch(select)
				} while (clothRun); // end while(clothRun)
				break;
			
			
			case 2: // 내가 가진 악세서리 추가하기
				boolean accRun;
				do {
					accRun = false;
					System.out.println("1.귀걸이 추가하기\n2.목걸이 추가하기\n3.신발 추가하기\n4.모자 추가하기");
					System.out.print(">>  ");
					select = sc.nextInt();
					
					switch (select) {
					case 1:
						// 귀걸이 추가 메소드
						action = new RegistEarringAction();
						break;
					
					case 2:
						// 목걸이 추가 메소드
						action = new ReistNecklaceAction();
						break;
					
					case 3:
						// 신발 추가 메소드
						action = new ReistShoesAction();
						break;
					
					case 4:
						// 모자 추가 메소드
						action = new ReistHatAction();
						break;
					
					default:
						System.out.println("잘못된 입력입니다. 다시 입력 해주세요.");
						accRun=true;
						break;
					}// end switch(select)
				} while (accRun); // end do-while(accRun)
				break;

			
			case 3: // 오늘 입을 옷 고르기
				action = new CheckWeatherAction();
				break;
			
			
			case 4: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				run=false;
				// DB접속 해제
				console.conclose();
				break;
			
			
			default:
				System.out.println("잘못된 입력입니다. 다시 입력 해주세요.");
				break;
			} // end switch(menu)
			if (action != null) {
				ctrl.processRequest(sc, action);
			}
		} while (run); // end do-while

	}
}
