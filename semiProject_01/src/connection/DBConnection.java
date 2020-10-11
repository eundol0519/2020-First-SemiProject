/**
	Date : 2020-06-16
	Author : 김태석, 오은희, 윤희영, 임형민
	Description : 
	Version : 1.0
*/
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection DBconnect() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.0.169:1521:XE", user = "YHY", password = "1111";
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
			System.out.println("DB접속 실패 : 드라이버로딩실패");
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("DB접속 실패 : 잘못된 url, user, password");
		}
		return con;
	}// end DBconnect()
	
}
