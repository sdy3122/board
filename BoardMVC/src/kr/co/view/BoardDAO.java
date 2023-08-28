package kr.co.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardDAO {
	ResultSet rs;
	Statement stmt;
	String sql;
	
	public void inputInfoBD() {
		int cnt = 0;
		try {
			stmt = JDBCUtill.statement();
			cnt = stmt.executeUpdate("insert into board(title, content, author, click) values('" + BoardView.boardDTO.getTitle() + "', '" + BoardView.boardDTO.getBody()
			+ "', '" + BoardView.boardDTO.getAut() + "', '0')");
			if (cnt > 0) {
				System.out.println("게시글 등록 성공");
			} else {
				System.out.println("게시글 등록 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) JDBCUtill.disconnect(stmt);
		}
	}
	
	public void countingTitle() {
		int cnt = 0;
		try {
			stmt = JDBCUtill.statement();
			rs = stmt.executeQuery(BoardView.sqlTitle);
			while (rs.next()) {
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				JDBCUtill.disconnect(stmt);
		}
		BoardView.cnt =  cnt;
	}
	
	public void searchListOne() {
		try {
			stmt = JDBCUtill.statement();
			stmt.executeUpdate("update board set click = click + 1 where title = '" + BoardView.title + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				JDBCUtill.disconnect(stmt);
		}
		try {
			stmt = JDBCUtill.statement();
			rs = stmt.executeQuery(BoardView.sqlTitle);
			if (rs.next()) {
				System.out.print("번호\t제목\t내용\t작성자\t날짜\t\t조회수\n");
				System.out.print(rs.getInt("no") + "\t" + rs.getString("title") + "\t" + rs.getString("content") + "\t" + rs.getString("author") + "\t" + rs.getString("nal") + "\t" + rs.getString("click") + "\n");
			} else {
				System.out.println("검색 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				JDBCUtill.disconnect(stmt);
		}
	}
	
	public void showAuthorSameTitle() {
		stmt = JDBCUtill.statement();
		try {
			rs = stmt.executeQuery(BoardView.sqlTitle);
			while (rs.next()) {
				System.out.println(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				JDBCUtill.disconnect(stmt);
		}
	}
	
	public void searchListTitleAuthor() {
		stmt = JDBCUtill.statement();
		try {
			stmt.executeUpdate("update board set click = click + 1 where title = '" + BoardView.title + "' and author = '"
					+ BoardView.author + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				JDBCUtill.disconnect(stmt);
		}
		try {
			stmt = JDBCUtill.statement();
			rs = stmt.executeQuery("select * from board where title = '"+BoardView.title+"' and author = '"+BoardView.author+"'");
			if (rs.next()) {
				System.out.print("번호\t제목\t내용\t작성자\t날짜\t\t조회수\n");
				System.out.print(rs.getInt("no") + "\t" + rs.getString("title") + "\t" + rs.getString("content") + "\t" + rs.getString("author") + "\t" + rs.getString("nal") + "\t" + rs.getString("click") + "\n");
			} else {
				System.out.println("검색 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				JDBCUtill.disconnect(stmt);
		}
	}

	public void deleteListIitle() {
		stmt = JDBCUtill.statement();
		int deleteCheck;
		try {
			deleteCheck = stmt.executeUpdate("delete from board where title = '" + BoardView.title + "'");
			if (deleteCheck == 0) System.out.println("제목과 작성자 일치하지 않음, 삭제실패");
			else {
				System.out.println("제목 " + BoardView.title + " 삭제 완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
			}
		}
	}

	public void deleteListTitleAuthor() {
		stmt = JDBCUtill.statement();
		int deleteCheck;
		try {
			deleteCheck = stmt.executeUpdate("delete from board where title = '" + BoardView.title + "' and author = '" + BoardView.author + "'");
			if (deleteCheck == 0) System.out.println("제목과 작성자 일치하지 않음, 삭제실패");
			else {
				System.out.println("제목 " + BoardView.title + " 삭제 완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
			}
		}
	}

	public void countingAll() {
		int cnt = 0;
		try {
			stmt = JDBCUtill.statement();
			rs = stmt.executeQuery("select * from board");
			while (rs.next()) {
				cnt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
				}
			if (stmt != null)
				JDBCUtill.disconnect(stmt);
		}
		BoardView.cnt = cnt;
	}

	public void updateTBTitle() {
		int updateCheck = 0;
		try {
			stmt = JDBCUtill.statement();
			updateCheck = stmt.executeUpdate("update board set title = '" + BoardView.reTitle + "', content = '" + BoardView.reBody + "' where title = '"
					+ BoardView.title + "'");
			if (updateCheck == 0) System.out.println("제목과 작성자 일치하지 않음, 수정실패");
			else {
				System.out.println("제목 " + BoardView.title + " 수정 완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) JDBCUtill.disconnect(stmt);
		}
	}
	
	public void updateTBTitleAuthor() {
		int updateCheck = 0;
		try {
			stmt = JDBCUtill.statement();
			updateCheck = stmt.executeUpdate("update board set title = '" + BoardView.reTitle + "', content = '" + BoardView.reBody + "' where title = '"
					+ BoardView.title + "' and author = '"+BoardView.author+"'");
			if (updateCheck == 0) System.out.println("제목과 작성자 일치하지 않음, 수정실패");
			else {
				System.out.println("제목 " + BoardView.title + " 수정 완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) JDBCUtill.disconnect(stmt);
		}
	}
	
	public void listShowAll() {
		try {
			stmt = JDBCUtill.statement();
			rs = stmt.executeQuery("select * from board");
			while (rs.next()) {
				System.out.print(rs.getInt("no") + "\t" + rs.getString("title") + "\t" + rs.getString("content") + "\t" + rs.getString("author") + "\t" + rs.getString("nal") + "\t" + rs.getString("click") + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (stmt != null) JDBCUtill.disconnect(stmt);
		}
	}
}
