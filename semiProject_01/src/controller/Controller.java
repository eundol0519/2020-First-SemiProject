/**
	Date : 2020-06-16
	Author : 김태석, 오은희, 윤희영, 임형민
	Description : 
	Version : 1.0
*/
package controller;

import java.util.Scanner;

import action.Action;

public class Controller {
	
	public void processRequest(Scanner sc, Action action) {
		// 예외처리
		try {
			action.excute(sc); // 예외 발생 지점
		} catch(Exception e) { // e라고 적어도 개발자들끼리는 다 안다.
			e.printStackTrace(); // 처리 지점
		} // printStackTrace() 전체 에러 발생 경로 및 메세지를 출력
		//                     단계별로 오류가 발생한 위치를 추적
		
	}
}
