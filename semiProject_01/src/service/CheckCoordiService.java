package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import connection.DBConnection;

public class CheckCoordiService {

	public void Coordi(String TC_CLOTH_CODE, String UC_CLOTH_CODE) {

		Scanner sc = new Scanner(System.in);

		Connection con = DBConnection.DBconnect();
		boolean run = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs_top = null;
		ResultSet rs_under = null;
		int num = 0;

		String machingSql = "SELECT * FROM COORDI WHERE TOP_CLOTH_CODE = ? AND UNDER_CLOTH_CODE = ?";
		String recommendTopSql = "SELECT TC_NAME, UC_NAME FROM TOP_CLOTH, UNDER_CLOTH, COORDI WHERE TOP_CLOTH_CODE LIKE ? AND TC_CODE LIKE ? AND UNDER_CLOTH_CODE=?  AND UC_CODE=? "
				+ "AND TOP_CLOTH.HAVE IS NOT NULL AND UNDER_CLOTH.HAVE IS NOT NULL";
		String recommendUnderSql = "SELECT TC_NAME, UC_NAME FROM TOP_CLOTH, UNDER_CLOTH, COORDI WHERE TOP_CLOTH_CODE=? AND TC_CODE=? AND UNDER_CLOTH_CODE LIKE ?  AND UC_CODE LIKE ? "
				+ "AND TOP_CLOTH.HAVE IS NOT NULL AND UNDER_CLOTH.HAVE IS NOT NULL";
		String selectSql = "SELECT TC_NAME, UC_NAME FROM TOP_CLOTH, UNDER_CLOTH WHERE TC_CODE=? AND UC_CODE=?";
		// ?에 값을 넣을 때
		// sql 쿼리문에 값 넣는 법
		try {
			pstmt = con.prepareStatement(machingSql);
			pstmt.setString(1, TC_CLOTH_CODE);
			pstmt.setString(2, UC_CLOTH_CODE);
			int count = pstmt.executeUpdate();

			
			// NAME을 담을 변수들
			String selectTC = null;
			String selectUC = null;
			String recommendTC = null;
			String recommendUC = null;

			// 상의 고정 하의 고정 (내가 선택한 옷)
			pstmt = con.prepareStatement(selectSql);
			pstmt.setString(1, TC_CLOTH_CODE);
			pstmt.setString(2, UC_CLOTH_CODE);
			int enter = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			if (enter > 0) {
				while (rs.next()) {
					selectTC = rs.getString("TC_NAME");
					selectUC = rs.getString("UC_NAME");
				}
			}
			pstmt.close();
			
			
			if (count > 0) {
				System.out.println("오늘 코디가 좋습니다.");
				System.out.println("선택한 상의 : "+ selectTC);
				System.out.println("선택한 하의 : "+ selectUC);
			} else {
				System.out.println("오늘 코디가 별로입니다.");

				int countUpdate_top = 0;
				int countUpdate_under = 0;



				// 상의 고정 하의 추천
				run = true;
				while (run) {
					num = (int) ((Math.random() * 16) + 201);
					String randomUnderClothCode = Integer.toString(num);
					pstmt = con.prepareStatement(recommendUnderSql);
					pstmt.setString(1, TC_CLOTH_CODE);
					pstmt.setString(2, TC_CLOTH_CODE);
					pstmt.setString(3, randomUnderClothCode + "%");
					pstmt.setString(4, randomUnderClothCode + "%");
					countUpdate_under = pstmt.executeUpdate();
					rs_under = pstmt.executeQuery();

					if (countUpdate_under > 0) {
						run = false;
						while (rs_under.next()) {
							recommendUC = rs_under.getString("UC_NAME");
						}
					}
					pstmt.close();
				} // end while(run)
				

				// 하의 고정 상의 추천
				run=true;
				while(run) {
					num = (int) ((Math.random() * 30) + 101);
					String randomTopClothCode = Integer.toString(num);

					pstmt = con.prepareStatement(recommendTopSql);
					pstmt.setString(1, randomTopClothCode + "%");
					pstmt.setString(2, randomTopClothCode + "%");
					pstmt.setString(3, UC_CLOTH_CODE);
					pstmt.setString(4, UC_CLOTH_CODE);
					countUpdate_top = pstmt.executeUpdate();
					rs_top = pstmt.executeQuery();

					if (countUpdate_top > 0) {
						run = false;
						while (rs_top.next()) {
							recommendTC = rs_top.getString("TC_NAME");
						} // end while
					} // end if
					pstmt.close();
				}//end while(run)
		
				if (countUpdate_top > 0) {
					System.out.println("다음과 같이 입는것은 어떠신가요?");
					System.out.println("1번 >>  ");
					System.out.println("선택한 상의 : " + selectTC + "\n추천 하의 : " + recommendUC);
					if (countUpdate_under > 0) {
						System.out.println("2번 >>  ");
						System.out.println("선택한 하의 : " + selectUC + "\n추천 상의 : " + recommendTC);
						System.out.println("3번 >>  ");
						System.out.println("선택한 상의 : " + selectTC + "\n선택한 하의 : " + selectUC);

						int menu = 0;
						run = true;
						while (run) {
							System.out.print(">>  ");
							menu=sc.nextInt();
						
							switch (menu) {
							case 1:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + recommendUC);
								run = false;
								break;
							case 2:
								System.out.println("< 오늘의 코디 >\n상의 : " + recommendTC + "\n하의 : " + selectUC);
								run = false;
								break;
							case 3:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + selectUC);
								run = false;
								break;
							default:
								System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
								break;
							}// end switch
						} // end while(run)

					} else {
						System.out.println("2번 >>  ");
						System.out.println("선택한 상의 : " + selectTC + "\n선택한 하의 : " + selectUC);
						int menu = 0;
						run = true;
						while (run) {
							System.out.print(">>  ");
							menu=sc.nextInt();
							switch (menu) {
							case 1:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + recommendUC);
								run = false;
								break;
							case 2:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + selectUC);
								run = false;
								break;
							default:
								System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
								break;
							}// end switch
						} // end while(run)
					} // end if

				} else {
					if (countUpdate_under > 0) {
						System.out.println("다음과 같이 입는것은 어떠신가요?");
						System.out.println("1번 >>  ");
						System.out.println("선택한 하의 : " + selectUC + "\n추천 상의 : " + recommendTC);
						System.out.println("2번 >>  ");
						System.out.println("선택한 상의 : " + selectTC + "\n선택한 하의 : " + selectUC);

						System.out.print("오늘의 코디를 선택해주세요  >>  ");
						int menu = 0;
						run = true;
						while (run) {
							System.out.print(">>  ");
							menu=sc.nextInt();
							switch (menu) {
							case 1:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectUC + "\n하의 : " + recommendTC);
								run = false;
								break;
							case 2:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + selectUC);
								run = false;
								break;
							default:
								System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
								break;
							}// end switch(menu)
						} // end while
					} else {
						System.out.println("추천할만한 코디가 없습니다 T^T. . . . ");
					} // end if(countUpdate_under)
				} // end if(counUpdate_top) - else
			} // end if(count) - else
			con.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end try

	}// end Coordi
	
// non-weather	
	public void Coordi_nonWeather(String TC_CLOTH_CODE, String UC_CLOTH_CODE) {

		Scanner sc = new Scanner(System.in);

		Connection con = DBConnection.DBconnect();
		boolean run = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs_top = null;
		ResultSet rs_under = null;
		int num = 0;

		String machingSql = "SELECT * FROM COORDI WHERE TOP_CLOTH_CODE = ? AND UNDER_CLOTH_CODE = ?";
		String recommendTopSql = "SELECT TC_NAME, UC_NAME FROM TOP_CLOTH, UNDER_CLOTH, COORDI WHERE TOP_CLOTH_CODE LIKE ? AND TC_CODE LIKE ? AND UNDER_CLOTH_CODE=?  AND UC_CODE=? "
				+ "AND TOP_CLOTH.HAVE IS NOT NULL AND UNDER_CLOTH.HAVE IS NOT NULL";
		String recommendUnderSql = "SELECT TC_NAME, UC_NAME FROM TOP_CLOTH, UNDER_CLOTH, COORDI WHERE TOP_CLOTH_CODE=? AND TC_CODE=? AND UNDER_CLOTH_CODE LIKE ?  AND UC_CODE LIKE ? "
				+ "AND TOP_CLOTH.HAVE IS NOT NULL AND UNDER_CLOTH.HAVE IS NOT NULL";
		String selectSql = "SELECT TC_NAME, UC_NAME FROM TOP_CLOTH, UNDER_CLOTH WHERE TC_CODE=? AND UC_CODE=?";
		// ?에 값을 넣을 때
		// sql 쿼리문에 값 넣는 법
		try {
			pstmt = con.prepareStatement(machingSql);
			pstmt.setString(1, TC_CLOTH_CODE);
			pstmt.setString(2, UC_CLOTH_CODE);
			int count = pstmt.executeUpdate();

			
			// NAME을 담을 변수들
			String selectTC = null;
			String selectUC = null;
			String recommendTC = null;
			String recommendUC = null;

			// 상의 고정 하의 고정 (내가 선택한 옷)
			pstmt = con.prepareStatement(selectSql);
			pstmt.setString(1, TC_CLOTH_CODE);
			pstmt.setString(2, UC_CLOTH_CODE);
			int enter = pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			if (enter > 0) {
				while (rs.next()) {
					selectTC = rs.getString("TC_NAME");
					selectUC = rs.getString("UC_NAME");
				}
			}
			pstmt.close();
			
			
			if (count > 0) {
				System.out.println("오늘 코디가 좋습니다.");
				System.out.println("선택한 상의 : "+ selectTC);
				System.out.println("선택한 하의 : "+ selectUC);
			} else {
				System.out.println("오늘 코디가 별로입니다.");

				int countUpdate_top = 0;
				int countUpdate_under = 0;


			
				// 상의 고정 하의 추천
				run = true;
				while (run) {
					System.out.println("여기까지");
					num = (int) ((Math.random() * 16) + 201);
					String randomUnderClothCode = Integer.toString(num);
					pstmt = con.prepareStatement(recommendUnderSql);
					pstmt.setString(1, TC_CLOTH_CODE);
					pstmt.setString(2, TC_CLOTH_CODE);
					pstmt.setString(3, randomUnderClothCode + "%");
					pstmt.setString(4, randomUnderClothCode + "%");
					countUpdate_under = pstmt.executeUpdate();
					rs_under = pstmt.executeQuery();

					if (countUpdate_under > 0) {
						run = false;
						while (rs_under.next()) {
							recommendUC = rs_under.getString("UC_NAME");
						}
					}
					pstmt.close();
				} // end while(run)
				

				// 하의 고정 상의 추천
				run=true;
				while(run) {
					num = (int) ((Math.random() * 30) + 101);
					String randomTopClothCode = Integer.toString(num);

					pstmt = con.prepareStatement(recommendTopSql);
					pstmt.setString(1, randomTopClothCode + "%");
					pstmt.setString(2, randomTopClothCode + "%");
					pstmt.setString(3, UC_CLOTH_CODE);
					pstmt.setString(4, UC_CLOTH_CODE);
					countUpdate_top = pstmt.executeUpdate();
					rs_top = pstmt.executeQuery();

					if (countUpdate_top > 0) {
						run = false;
						while (rs_top.next()) {
							recommendTC = rs_top.getString("TC_NAME");
						} // end while
					} // end if
					pstmt.close();
				}//end while(run)
	
				if (countUpdate_top > 0) {
					System.out.println("다음과 같이 입는것은 어떠신가요?");
					System.out.println("1번 >>  ");
					System.out.println("선택한 상의 : " + selectTC + "\n추천 하의 : " + recommendUC);
					if (countUpdate_under > 0) {
						System.out.println("2번 >>  ");
						System.out.println("선택한 하의 : " + selectUC + "\n추천 상의 : " + recommendTC);
						System.out.println("3번 >>  ");
						System.out.println("선택한 상의 : " + selectTC + "\n선택한 하의 : " + selectUC);

						int menu = 0;
						run = true;
						while (run) {
							System.out.print(">>  ");
							menu=sc.nextInt();
						
							switch (menu) {
							case 1:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + recommendUC);
								run = false;
								break;
							case 2:
								System.out.println("< 오늘의 코디 >\n상의 : " + recommendTC + "\n하의 : " + selectUC);
								run = false;
								break;
							case 3:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + selectUC);
								run = false;
								break;
							default:
								System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
								break;
							}// end switch
						} // end while(run)

					} else {
						System.out.println("2번 >>  ");
						System.out.println("선택한 상의 : " + selectTC + "\n선택한 하의 : " + selectUC);
						int menu = 0;
						run = true;
						while (run) {
							System.out.print(">>  ");
							menu=sc.nextInt();
							switch (menu) {
							case 1:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + recommendUC);
								run = false;
								break;
							case 2:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + selectUC);
								run = false;
								break;
							default:
								System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
								break;
							}// end switch
						} // end while(run)
					} // end if

				} else {
					if (countUpdate_under > 0) {
						System.out.println("다음과 같이 입는것은 어떠신가요?");
						System.out.println("1번 >>  ");
						System.out.println("선택한 하의 : " + selectUC + "\n추천 상의 : " + recommendTC);
						System.out.println("2번 >>  ");
						System.out.println("선택한 상의 : " + selectTC + "\n선택한 하의 : " + selectUC);

						System.out.print("오늘의 코디를 선택해주세요  >>  ");
						int menu = 0;
						run = true;
						while (run) {
							System.out.print(">>  ");
							menu=sc.nextInt();
							switch (menu) {
							case 1:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectUC + "\n하의 : " + recommendTC);
								run = false;
								break;
							case 2:
								System.out.println("< 오늘의 코디 >\n상의 : " + selectTC + "\n하의 : " + selectUC);
								run = false;
								break;
							default:
								System.out.println("잘못 눌렀습니다. 다시 입력해주세요.");
								break;
							}// end switch(menu)
						} // end while
					} else {
						System.out.println("추천할만한 코디가 없습니다 T^T. . . . ");
					} // end if(countUpdate_under)
				} // end if(counUpdate_top) - else
			} // end if(count) - else
			con.close();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end try

	}// end Coordi
	
}