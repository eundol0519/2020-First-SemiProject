/**
	Date : 2020-06-16
	Author : 김태석, 오은희, 윤희영, 임형민
	Description : 
	Version : 1.0
*/
package action;


import service.RecommendAccService;

public class RecommendAccAction {

	public void recommend(String weather) throws Exception {
		
		RecommendAccService ras = new RecommendAccService();
		System.out.println("오늘의 추천 귀걸이  >>  "+ras.recomendEarring(weather));
		System.out.println("오늘의 추천 목걸이  >>  "+ras.recomendNecklace(weather));
		System.out.println("오늘의 추천 신발     >>  "+ras.recomendShoes(weather));
		System.out.println("오늘의 추천 모자     >>  "+ras.recomendHat(weather));
		
		
		
	}

}
