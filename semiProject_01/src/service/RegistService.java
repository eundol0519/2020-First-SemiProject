/**
	Date : 2020-06-16
	Author : 김태석, 오은희, 윤희영, 임형민
	Description : 내가 가진 옷 입력
	Version : 1.0
*/
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.DBConnection;


public class RegistService {

	// REGIST
	Connection con = DBConnection.DBconnect();
	PreparedStatement pstmt = null;

	public boolean insertMyTopCloth(String name) throws Exception {

		boolean registSuccess = false;
		String sql = "UPDATE TOP_CLOTH SET HAVE = 'o' WHERE TC_NAME LIKE ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			int count = pstmt.executeUpdate();
			if (count > 0) {
				registSuccess = true;
			}
		
			pstmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return registSuccess;
	}

	public boolean insertMyUnderCloth(String name) {
		
		boolean registSuccess = false;
		String sql = "UPDATE UNDER_CLOTH SET HAVE = 'o' WHERE UC_NAME LIKE ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+ name +"%");
			int count = pstmt.executeUpdate();

			if (count > 0) {
				registSuccess = true;
			}
			pstmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return registSuccess;
	}
	
	// 악세서리 추가 - 귀걸이, 신발, 목걸이, 모자
	public boolean insertMyAcc(String name) {
		boolean registSuccess = false;
		String sql = "UPDATE ACC SET HAVE = 'o' WHERE A_NAME LIKE ?";

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			int count = pstmt.executeUpdate();

			if (count > 0) {
				registSuccess = true;
			}
			pstmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return registSuccess;
	}

}
