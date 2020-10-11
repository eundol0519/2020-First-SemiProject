/**
	Date : 2020-06-16
	Author : 김태석, 오은희, 윤희영, 임형민
	Description : 
	Version : 1.0
*/
package action;

import java.sql.Connection;
import java.util.Scanner;

import connection.DBConnection;
import service.CheckCoordiService;
import service.CheckWeatherClothService;
import service.insertRightCheck;
import util.ConsoleUtil;

public class CheckWeatherAction implements Action {
	ConsoleUtil console = new ConsoleUtil();
	Connection con = DBConnection.DBconnect();
	Scanner sc = new Scanner(System.in);
	insertRightCheck irc = new insertRightCheck();
	
	
	@Override
	public void excute(Scanner sc) throws Exception {
		String tCloth=null;
		String uCloth=null;
		WeatherAction wa = new WeatherAction();
		connection.weather wt = new connection.weather(); 
		
		int temp=0;
		String weather=null;
		
		try {
			temp=wa.weather();	// 현재온도		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(temp>30) {	// 여름
			weather = "SM";
		}else if(temp>24) {	// 봄
			weather = "SP";
		}else if(temp>20) {	// 가을
			weather = "FA";
		}else {				// 겨울
			weather = "WT";
		}
		
		CheckWeatherClothService cwc = new CheckWeatherClothService();
		
// 상의 고르기		
		String name = null;
		String reName_top=null;
		String reName_under=null;
		
		boolean run = true;
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("<<  입으실 상의를 골라주세요!  >>");
		console.printTopClothHaveList();
	
		sc.nextLine();

		while (run) {
			System.out.print(">>  ");
			name = sc.nextLine();
			boolean checkSuccess = irc.checkRight_topCloth(name);
			if (checkSuccess) {
				tCloth = cwc.CheckTopCloth(name, weather);
				if (tCloth == null) {
					System.out.println("\n해당 옷은 오늘 날씨에 적합하지 않아요. T^T\t(오늘 최고 온도 : " + temp + "℃ )");
					System.out.println("날씨와 맞는 옷들은 다음과 같아요. \n이 중 옷을 고르시는 건 어떠세요?");
					System.out.println("(선택한 옷을 입고자 하신다면 '그대로'를 입력해주세요)");
					System.out.println();
					cwc.recommendTopClothList(weather);
					System.out.print(">>  ");
					reName_top = sc.nextLine();
					if (reName_top.equals("그대로")) {
						tCloth = cwc.CheckTopCloth_nonWeather(name);
						run = false;
					} else {
						boolean checkSuccess2 = irc.checkRight_topCloth(reName_under);
						if (checkSuccess2) {
							tCloth = cwc.CheckTopCloth(reName_top, weather); // 체크할 필요가 있나?
							run = false;
						} else {
							console.printRegisFailMessage();
						} // end if-else(checkSuccess2)
					} // end if(name)
				} else {
					run = false;
				} // end if(tCloth)
			} else {// end if(checkSuccess)
				console.printRegisFailMessage();
			}
		} // end while
		
// 하의 고르기
		run = true;
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("\n<<  입으실 하의를 골라주세요!  >>");
		console.printUnderClothHaveList();
		while (run) {
			System.out.print(">>  ");
			name = sc.nextLine();
			boolean checkSuccess = irc.checkRight_underCloth(name);
			if (checkSuccess) {
				uCloth = cwc.CheckUnderCloth(name, weather);
				if (uCloth == null) {
					System.out.println("\n해당 옷은 오늘 날씨에 적합하지 않아요. T^T\t(오늘 최고 온도 : " + temp + "℃ )");
					System.out.println("오늘 날씨와 맞는 옷들은 다음과 같아요. \n이 중 옷을 고르시는 건 어떠세요?");
					System.out.println("(선택한 옷을 입고자 하신다면 '그대로'를 입력해주세요)");
					System.out.println();
					cwc.recommendUnderClothList(weather);
					System.out.println(">>  ");
					reName_under = sc.nextLine();
					if (reName_under.equals("그대로")) {
						uCloth = cwc.CheckUnderCloth_nonWeather(name);
						run = false;
					} else {
						boolean checkSuccess2 = irc.checkRight_underCloth(reName_under);
						if (checkSuccess2) {
							uCloth = cwc.CheckTopCloth(reName_under, weather); // 체크할 필요가 있나?
							run = false;
						} else {
							console.printRegisFailMessage();
						} // end if(checkSuccess2)
					} // end if(name)

				} else {
					run = false;
				} // end if
			} else {
				console.printRegisFailMessage();
			} // end if(checkSuccess)
		} // end while
		
		
		
//		if(// 코드끼리 비교해서 코디가 맞지 않을 경우)

		CheckCoordiService ccs = new CheckCoordiService();
		ccs.Coordi(tCloth, uCloth);
		
		
//악세서리 추천		
		System.out.println();
		System.out.println("**************************");
		System.out.println("*  악세서리를 추천해 드릴까요?  *");
		System.out.println("**************************");


		boolean recommend = false;
		do {
			System.out.println("1. Yes\t\t2. No");
			System.out.print(">>  ");
			int select=sc.nextInt();
			switch(select) {
			case 1: // Y
				RecommendAccAction raa = new RecommendAccAction();
				raa.recommend(weather);
				break;
			case 2: // N
				System.out.println("");
				break;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력 해주세요.");
				recommend=true;
				break;
			}// end switch(select)
		} while(recommend);// do-while(recommend)
		
	
	}// end execute

}
