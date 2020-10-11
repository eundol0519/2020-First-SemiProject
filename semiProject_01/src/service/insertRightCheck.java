package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;

public class insertRightCheck {
	PreparedStatement pstmt = null;
	Connection con = DBConnection.DBconnect();
	ResultSet rs=null;
	
	public boolean checkRight_topCloth(String name) {
		String sql = "SELECT*FROM TOP_CLOTH WHERE TC_NAME LIKE ? AND HAVE IS NOT NULL";
		boolean value=false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			int count=pstmt.executeUpdate();
			if(count>0) {
				value=true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return value;
	}// end checkRignt_topCloth
	
	
	public boolean checkRight_underCloth(String name) {
		String sql = "SELECT*FROM UNDER_CLOTH WHERE UC_NAME LIKE ? AND HAVE IS NOT NULL ";
		boolean value = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			int count = pstmt.executeUpdate();
			if (count > 0) {
				value = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return value;
	}// end checkRignt_topCloth
	
	
	public boolean checkRight_acc(String name) {
		String sql = "SELECT*FROM ACC WHERE A_NAME LIKE ? AND HAVE IS NOT NULL";
		boolean value = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			int count = pstmt.executeUpdate();
			if (count > 0) {
				value = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return value;
	}// end checkRignt_topCloth
}
