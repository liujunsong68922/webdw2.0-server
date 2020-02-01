package com.webdw.model.dwsyntax;

//'���������ݶ�������
public class WebDW_Line {
	public String band = "";

	public int x1 = 0;

	public int y1 = 0;

	public int x2 = 0;

	public int y2 = 0;

	public String Name = "";

	public String pen_style = "";

	public String pen_width = "";

	public String pen_color = "";

	public String background_mode = "";

	public String background_color = "";

	public WebDW_Line Clone() {
		WebDW_Line newOne = new WebDW_Line();

		newOne.band = band;

		newOne.x1 = x1;

		newOne.y1 = y1;

		newOne.x2 = x2;

		newOne.y2 = y2;

		newOne.Name = Name;

		newOne.pen_style = pen_style;

		newOne.pen_width = pen_width;

		newOne.pen_color = pen_color;

		newOne.background_mode = background_mode;

		newOne.background_color = background_color;

		return newOne;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPen_style() {
		return pen_style;
	}

	public void setPen_style(String pen_style) {
		this.pen_style = pen_style;
	}

	public String getPen_width() {
		return pen_width;
	}

	public void setPen_width(String pen_width) {
		this.pen_width = pen_width;
	}

	public String getPen_color() {
		return pen_color;
	}

	public void setPen_color(String pen_color) {
		this.pen_color = pen_color;
	}

	public String getBackground_mode() {
		return background_mode;
	}

	public void setBackground_mode(String background_mode) {
		this.background_mode = background_mode;
	}

	public String getBackground_color() {
		return background_color;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

}
