package kr.co.controller;

import kr.co.view.BoardDAO;
import kr.co.view.BoardView;

public class BoardController {
	public BoardView view;
	public BoardDAO dao;
	public BoardController() {
		this.view = new BoardView();
		dao = new BoardDAO();
	}
	
	public void startApp() {
		while (true) {
			view.clientView();
			if (view.menuInput.trim().isEmpty()) {
				view.inputEmpty();
			} else if (!(view.menuInput.charAt(0) > 64 && view.menuInput.charAt(0) < 123)) {
				view.mustInputEnglish();
			} else if (view.menuInput.charAt(0) == 'R' || view.menuInput.charAt(0) == 'r') {
				view.registTitle();
				view.titleBody();
				view.inputNameTitle();
				view.inputName();
				view.timeNow();
				view.inputInfoAl();
				dao.inputInfoBD();
				view.reMenu();
			} else if (view.menuInput.charAt(0) == 'S' || view.menuInput.charAt(0) == 's') {
				view.searchTitle("검색");
				dao.countingTitle();
				if (BoardView.cnt == 0) {
					view.searchTitleFail();
				} else if (BoardView.cnt == 1) {
					dao.searchListOne();
				} else {
					view.searchAuthorTitle();
					dao.showAuthorSameTitle();
					view.inputAuthor();
					dao.searchListTitleAuthor();
				}
				view.reMenu();
			} else if (view.menuInput.charAt(0) == 'D' || view.menuInput.charAt(0) == 'd') {
				view.searchTitle("삭제");
				dao.countingTitle();
				if (BoardView.cnt == 0) {
					view.searchTitleFail();
				} else if (BoardView.cnt == 1) {
					dao.deleteListIitle();
				} else {
					view.deleteAuthorTitle();
					dao.showAuthorSameTitle();
					view.inputAuthor();
					dao.deleteListTitleAuthor();
				}
				view.reMenu();
			} else if (view.menuInput.charAt(0) == 'U' || view.menuInput.charAt(0) == 'u') {
				dao.countingAll();
				if (BoardView.cnt > 0) {
					view.searchTitle("수정");
					dao.countingTitle();
					if (BoardView.cnt == 0) {
						view.searchTitleFail();
					} else if (BoardView.cnt == 1) {
						view.inputTitle();
						view.inputBody();
						dao.updateTBTitle();
					} else {
						view.editAuthorTitle();
						dao.showAuthorSameTitle();
						view.inputAuthor();
						dao.updateTBTitleAuthor();
					}
				} else {
					view.noBoard();
				}
				view.reMenu();
			} else if (view.menuInput.charAt(0) == 'L' || view.menuInput.charAt(0) == 'l') {
				dao.countingAll();
				if (BoardView.cnt == 0) {
					view.noBoard();
				} else {
					view.listTitle();
					dao.listShowAll();
				}
				view.reMenu();
			} else if (view.menuInput.charAt(0) == 'E' || view.menuInput.charAt(0) == 'e') {
				 view.endInput();
				break;
			} else {
				view.alphabetWarning();
			}
		}
	}
}
