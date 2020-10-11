package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import action.Action;
import connection.DBConnection;

public class CheckWeatherClothService {

	PreparedStatement pstmt = null;
	Connection con = DBConnection.DBconnect();
	String weather = null;
	ResultSet rs = null;

	Action action = null;

	int temp = 0;
// 날씨에 관련있는 코디
	public String CheckTopCloth(String name, String weather) {
		con = DBConnection.DBconnect();

		String sql = "SELECT TC_CODE FROM TOP_CLOTH WHERE TC_NAME LIKE ? AND HAVE IS NOT NULL AND TC_CODE LIKE ?";
		String tCloth = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + weather + "%"); // weather에는 온도에 따라 계절 코드가 들어감.
			int enter = pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			if (enter > 0) {
				while (rs.next()) { // 꼭 해줮야 한다. ( 값을 불러오는 역할 )
					tCloth = rs.getString("TC_CODE");
					}
				} // end
				pstmt.close();
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return tCloth;
	}// end CheckTopCloth

	
	
	
	public String CheckUnderCloth(String name, String weather) {
		con = DBConnection.DBconnect();

		String uCloth = null;


		String sql = "SELECT UC_CODE FROM UNDER_CLOTH WHERE UC_NAME LIKE ? AND HAVE IS NOT NULL AND UC_CODE LIKE ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + weather + "%");
			int enter = pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			if (enter > 0) {
				while (rs.next()) {
					uCloth = rs.getString("UC_CODE");
				} // end while
			} // end if(count)
		
			pstmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end try
		return uCloth;
	} // end CheckUnderCloth

	

	
	
	
	

// 코디 (날씨) 체크 한 리스트
	// ResultSet을 이용해서 컬럼의 데이터를 받아온다.
	public void recommendTopClothList(String weather) {
		try {

			String sql = "SELECT TC_NAME FROM TOP_CLOTH WHERE TC_CODE LIKE ? AND HAVE IS NOT NULL";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + weather + "%");
			int enter = pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			int count = 1;
			if (enter > 0) {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				while (rs.next()) {
					System.out.println(count + ". " + rs.getString("TC_NAME"));
					count++;
				} // end while
			} else {
				System.out.println("오늘 날씨에 맞는 상의가 없어요!!! (T^T) !!!");
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			// end if(enter)

			pstmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}// end recommendTopClothList

	
	
	
	public void recommendUnderClothList(String weather) {
		try {

			String sql = "SELECT UC_NAME FROM UNDER_CLOTH WHERE UC_CODE LIKE ? AND HAVE IS NOT NULL";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + weather + "%");
			int enter = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			
			int count = 1;
			if (enter > 0) {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				while (rs.next()) {
					System.out.println(count + ". " + rs.getString("UC_NAME"));
					count++;
				} // end while
			} else {
				System.out.println("오늘 날씨에 맞는 하의가 없어요!!! (T^T) !!!");
			}// end if(enter)
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			pstmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}// end recommendUnderClothList









// 날씨 관련없이 코디 체크
	public String CheckTopCloth_nonWeather(String name) {
		con = DBConnection.DBconnect();

		String sql = "SELECT TC_CODE FROM TOP_CLOTH WHERE TC_NAME LIKE ? AND HAVE IS NOT NULL";
		String tCloth = null;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%"); // weather에는 온도에 따라 계절 코드가 들어감.
			int enter = pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			if (enter > 0) {
				while (rs.next()) { // 꼭 해줮야 한다. ( 값을 불러오는 역할 )
					tCloth = rs.getString("TC_CODE");
				}
			} // end
			pstmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return tCloth;
	}// end CheckTopCloth

	public String CheckUnderCloth_nonWeather(String name) {
		con = DBConnection.DBconnect();

		String uCloth = null;

		String sql = "SELECT UC_CODE FROM UNDER_CLOTH WHERE UC_NAME LIKE ? AND HAVE IS NOT NULL";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			int enter = pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			if (enter > 0) {
				while (rs.next()) {
					uCloth = rs.getString("UC_CODE");
				} // end while
			} // end if(count)
			pstmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end try
		return uCloth;
	} // end CheckUnderCloth


}