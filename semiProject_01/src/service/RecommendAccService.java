/**
	Date : 2020-06-16
	Author : 김태석, 오은희, 윤희영, 임형민
	Description : 
	Version : 1.0
*/
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.DBConnection;


public class RecommendAccService {
	
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String recommend=null;
	
	public String recomendEarring(String weather) {
		Connection con = DBConnection.DBconnect();

		String earringSql = "SELECT A_NAME FROM ACC WHERE A_CODE LIKE ? AND A_CODE LIKE ? AND HAVE IS NOT NULL";
		try {
			boolean run = true;
			while (run) {
				int num = (int) ((Math.random() * 10) + 401);
		
				String random = Integer.toString(num);

				pstmt = con.prepareStatement(earringSql);
				pstmt.setString(1, random + "%");
				pstmt.setString(2, "%" + weather + "%");
				int count = pstmt.executeUpdate();
				rs = pstmt.executeQuery();
				if (count > 0) {
					run = false;
					while (rs.next()) {
						recommend = rs.getString("A_NAME");
					} // end while(rs)
				} // end if(count)
				pstmt.close();
			} // end while(run)
			con.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	
	return recommend;
	}
	
	public String recomendNecklace(String weather) {
		Connection con = DBConnection.DBconnect();

		String necklaceSql = "SELECT A_NAME FROM ACC WHERE A_CODE LIKE ? AND HAVE IS NOT NULL";
		try {
			boolean run = true;
			while (run) {
				int num = (int) ((Math.random() * 6) + 601);
				String random = Integer.toString(num);

				pstmt = con.prepareStatement(necklaceSql);
				pstmt.setString(1, random + "%" + weather + "%");
				int count = pstmt.executeUpdate();
				rs = pstmt.executeQuery();
				if (count > 0) {
					run = false;
					while (rs.next()) {
						recommend = rs.getString("A_NAME");
					} // end while(rs)
				} // end if(count)
				pstmt.close();
			} // end while(run)
			con.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return recommend;
	}
	
	
	
	public String recomendShoes(String weather) {
		Connection con = DBConnection.DBconnect();

		String shoesSql = "SELECT A_NAME FROM ACC WHERE A_CODE LIKE ? AND HAVE IS NOT NULL";
		try {
			boolean run = true;
			while (run) {
				int num = (int) ((Math.random() * 11) + 301);
				String random = Integer.toString(num);

				pstmt = con.prepareStatement(shoesSql);
				pstmt.setString(1, random + "%" + weather + "%");
				int count = pstmt.executeUpdate();
				rs = pstmt.executeQuery();
				if (count > 0) {
					run=false;
					while (rs.next()) {
						recommend = rs.getString("A_NAME");
					} // end while(rs)
				} // end if(count)
				pstmt.close();
			} // end while(run)
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return recommend;
	}
	
	
	
	
	
	public String recomendHat(String weather) {
		Connection con = DBConnection.DBconnect();
		
		String hatSql = "SELECT A_NAME FROM ACC WHERE A_CODE LIKE ?  AND HAVE IS NOT NULL";
		try {
			boolean run = true;
			while (run) {
				int num = (int) ((Math.random() * 8) + 501);
				String random = Integer.toString(num);

				pstmt = con.prepareStatement(hatSql);
				pstmt.setString(1, random + "%" + weather + "%");
				int count = pstmt.executeUpdate();
				rs = pstmt.executeQuery();

				if (count>0) {
					run = false;
					while (rs.next()) {
						recommend = rs.getString("A_NAME");
					} // end while(rs.next())
				}
				pstmt.close();
			} // end while(run)
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	return recommend;
	}
	
	
	
}
	
	
	
	
