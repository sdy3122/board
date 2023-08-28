package kr.co.view;

public class BoardDTO {
	String title;
	String body;
	String aut;
	String day;
	String click = "0";
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAut() {
		return aut;
	}
	public void setAut(String aut) {
		this.aut = aut;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getClick() {
		return click;
	}
	public void setClick(String click) {
		this.click = click;
	}
	
}
