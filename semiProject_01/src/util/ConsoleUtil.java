/**
	Date : 2020-06-16
	Author : 김태석, 오은희, 윤희영, 임형민
	Description : 
	Version : 1.0
*/
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import connection.DBConnection;
import vo.Coordi;

public class ConsoleUtil {
	
	
	static Connection con = DBConnection.DBconnect();


	public void conclose() { // DB접속해지
		try {
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
		

	// 입력 성공/실패
	public void printRegisSuccessMessage() {
		System.out.println("입력에 성공했습니다!");
	}

	public void printRegisFailMessage() {
		System.out.println("입력에 실패했습니다. . . ");
	}
	
	
	
	
	
	
	
	// 리스트 도출

	public void printTopClothList() {
	
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "SELECT TC_NAME FROM TOP_CLOTH WHERE HAVE IS NULL";
		int count=1;
		try {		
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			while(rs.next()) {
				System.out.println(count+". "+rs.getString("TC_NAME"));
				count++;
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}// end printTopClothList
	public void printTopClothHaveList() {
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "SELECT TC_NAME FROM TOP_CLOTH WHERE HAVE IS NOT NULL";
		int count=1;
		try {		
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			while(rs.next()) {
				System.out.println(count+". "+rs.getString("TC_NAME"));
				count++;
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}// end printTopClothHaveList
	
	
	public void printUnderClothList() {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "SELECT UC_NAME FROM UNDER_CLOTH WHERE HAVE IS NULL";
		int count=1;
		try {		
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			while(rs.next()) {
				System.out.println(count+". "+rs.getString("UC_NAME"));
				count++;
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	} // printUnderClothList
	public void printUnderClothHaveList() {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "SELECT UC_NAME FROM UNDER_CLOTH WHERE HAVE IS NOT NULL";
		int count=1;
		try {		
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			while(rs.next()) {
				System.out.println(count+". "+rs.getString("UC_NAME"));
				count++;
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	} // printUnderClothHaveList
	
	
	
	public void printEarringList() {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "SELECT A_NAME FROM ACC WHERE HAVE IS NULL AND A_CODE LIKE '4%'";
		int count=1;
		try {		
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			while(rs.next()) {
				System.out.println(count+". "+rs.getString("A_NAME"));
				count++;
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}// end printEarringList

	
	public void printNecklaceList() {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "SELECT A_NAME FROM ACC WHERE HAVE IS NULL AND A_CODE LIKE '6%'";
		int count=1;
		try {		
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			while(rs.next()) {
				System.out.println(count+". "+rs.getString("A_NAME"));
				count++;
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}// end printNecklaceList


	
	public void printShoesList() {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "SELECT A_NAME FROM ACC WHERE HAVE IS NULL AND A_CODE LIKE '3%'";
		int count=1;
		try {		
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(count+". "+rs.getString("A_NAME"));
				count++;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}// end printShoesList

	
	
	public void printHatList() {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String sql = "SELECT A_NAME FROM ACC WHERE HAVE IS NULL AND A_CODE LIKE '5%'";
		int count=1;
		try {		
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(count+". "+rs.getString("A_NAME"));
				count++;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}// end printHatList

	
	
}
