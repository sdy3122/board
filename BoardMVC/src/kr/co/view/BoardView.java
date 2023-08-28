package kr.co.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BoardView {
	public BufferedReader br;
	public int indexDTO;
	public String menuInput;
	public String tB;
	public int index;
	public static String title;
	public String body;
	public String name;
	public LocalDateTime now;
	public String day;
	public static String sqlTitle;
	public static int cnt;
	public static String author;
	public static String reTitle;
	public static String reBody;
	public int numChoice;
	public int choiceNum;
	public ArrayList<Integer> alIndex;
	public static BoardDTO boardDTO;
	private static ArrayList<BoardDTO> al = new ArrayList<BoardDTO>();
	
	public BoardView() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void inputInfoAl() {
		boardDTO = new BoardDTO();
		boardDTO.setTitle(title);
		boardDTO.setBody(body);
		boardDTO.setAut(name);
		boardDTO.setDay(day);
		al.add(boardDTO);
	}
	
	public void clientView() {
		System.out.println("=================게시판작성=================");
		System.out.println("R:등록 S:검색 D:삭제 U:수정 L:목록 E:프로그램 종료");
		try {
			menuInput = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void inputEmpty() {
		System.out.println("아무것도 안적으면 에러입니다");
	}
	
	public void mustInputEnglish() {
		System.out.println("영어만 적으세요");
	}
	
	public void registTitle() {
		System.out.println("제목과 내용은 반드시 '|' 로 구분해주세요");
		System.out.println("제목|내용입력:");
	}
	
	public void titleBody() {
		try {
			tB = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		index = tB.indexOf("|");
		if (index == -1) {
			System.out.println("'|' 기호로 제목과 내용을 구분지어주세요");
			titleBody();
		} else {
			title = tB.substring(0, index);
			body = tB.substring(index + 1);
			if (title.trim().isEmpty() || body.trim().isEmpty()) {
				System.out.println("아무것도 안적으면 에러입니다");
				titleBody();
			}
		}
	}

	public void inputNameTitle() {
		System.out.print("작성자 입력(10자 이내로)");
	}

	public void inputName() {
		try {
			name = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (name.trim().isEmpty()) {
			System.out.println("아무것도 안적으면 에러입니다");
			inputName();
		} else if (name.length() > 10) {
			System.out.println("이름이 너무 깁니다 10자이내로 작성해주세요");
			inputName();
		}
	}

	public void timeNow() {
		now = LocalDateTime.now();
		day = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH시 mm분 ss초"));
	}
	
	public void reMenu() {
		System.out.println("메인메뉴로 돌아갑니다.\n");
	}

	public void noBoard() {
		System.out.println("등록된 게시글이 없습니다.");
	}

	public void searchTitle(String str) {
		System.out.print(str + "할 게시글 제목입력:");
		try {
			title = br.readLine();
			if (title.trim().isEmpty()) {
				System.out.println("아무것도 안적으면 에러입니다");
				searchTitle(str);
			}
			sqlTitle = "select * from board where title = '" + title + "'";
		} catch (IOException ioe) {
			ioe.printStackTrace();
			searchTitle(str);
		}
	}
	
	public void searchTitleFail() {
		System.out.println("제목 " + title + "의 게시글이 없습니다");
	}

	public void inputAuthor() {
		System.out.print("작성자 입력:");
		try {
			author = br.readLine();
			if (author.trim().isEmpty()) {
				System.out.println("아무것도 안적으면 에러입니다");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchAuthorTitle() {
		System.out.println("제목-" + title + "의 게시글이 " + cnt + "개 있습니다.");
		System.out.println("작성자를 보고 보고싶은 게시글의 작성자를 입력하세요.");
		System.out.println("작성자를 정확하게 입력하지 않으면 검색이 취소됩니다.");
	}

	public void deleteAuthorTitle() {
		System.out.println("제목 " + title + "의 게시글이 " + cnt + "개 있습니다.");
		System.out.println("작성자를 보고 원하시는 삭제하고싶은 작성자를 입력하세요.");
		System.out.println("작성자를 정확하게 입력하지 않으면 삭제가 취소됩니다.");
	}
	
	public void editAuthorTitle() {
		System.out.println("제목 " + title + "의 게시글이 " + cnt + "개 있습니다.");
		System.out.println("작성자를 보고 원하시는 수정하고싶은 작성자를 입력하세요.");
		System.out.println("작성자를 정확하게 입력하지 않으면 수정이 취소됩니다.");
	}

	public void inputTitle() {
		System.out.print("수정하고싶은 제목 입력 : ");
		try {
			reTitle = br.readLine();
			if (reTitle.trim().isEmpty()) {
				System.out.println("아무것도 안적으면 에러입니다");
				inputTitle();
			}
		} catch (IOException ioe) {
			ioe.getStackTrace();
		}
	}

	public void inputBody() {
		System.out.print("수정하고싶은 내용 입력 : ");
		try {
			reBody = br.readLine();
			if (reBody.trim().isEmpty()) {
				System.out.println("아무것도 안적으면 에러입니다");
				inputBody();
			}
		} catch (IOException ioe) {
			ioe.getStackTrace();
		}
	}

	public void listTitle() {
		System.out.println("=================전체 출력=================");
		System.out.print("번호\t제목\t내용\t작성자\t날짜\t\t조회수\n");
	}
	
	public void endInput() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void alphabetWarning() {
		System.out.println("목록에 있는 알파벳만 입력하세요");
	}
}
