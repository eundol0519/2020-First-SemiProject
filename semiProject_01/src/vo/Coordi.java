/**
	Date : 2020-06-16
	Author : 김태석, 오은희, 윤희영, 임형민
	Description : coordination field
	Version : 1.0
*/
package vo;

public class Coordi {

	private String topCloth, underCloth, earring, necklace, shoes, hat;

// 상의	
	public String getTopCloth() {
		return topCloth;
	}
	public void setTopCloth(String topCloth) {
		this.topCloth = topCloth;
	}
	
// 하의	
	public String getUnderCloth() {
		return underCloth;
	}
	public void setUnderCloth(String underCloth) {
		this.underCloth = underCloth;
	}

// 귀걸이	
	public String getEarring() {
		return earring;
	}
	public void setEarring(String earring) {
		this.earring = earring;
	}

// 목걸이	
	public String getNecklace() {
		return necklace;
	}
	public void setNecklace(String necklace) {
		this.necklace = necklace;
	}

// 신발
	public String getShoes() {
		return shoes;
	}
	public void setShoes(String shoes) {
		this.shoes = shoes;
	}

// 모자	
	public String getHat() {
		return hat;
	}
	public void setHat(String hat) {
		this.hat = hat;
	}
	
	
// 매개변수 생성자	
	public Coordi(String topCloth, String underCloth, String earring, String necklace, String shoes, String hat) {
		super();
		this.topCloth = topCloth;
		this.underCloth = underCloth;
		this.earring = earring;
		this.necklace = necklace;
		this.shoes = shoes;
		this.hat = hat;
	}
// 기본 생성자
	public Coordi() {
		super();
	}
	
	
	
	
}
