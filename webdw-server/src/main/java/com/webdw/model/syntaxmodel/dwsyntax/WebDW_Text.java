package com.webdw.model.syntaxmodel.dwsyntax;

//'text��֧�����Զ���
//'�������ļ��ж��뵽����ṹ��
//'��������ṹ����ͼ��ʾ
//'���������������ļ��ж������ͬ������ؼ�����һ��
//'������ȫ��Сд
public class WebDW_Text {
	public 	String band = "";

	public int alignment = 0;

	public String text = "";

	public int border = 0;

	public int color = 0;

	public int x = 0;

	public int y = 0;

	public int height = 0;

	public int width = 0;

	public String Name = "";

	public WebDW_Font font = new WebDW_Font(); // '�¶����font����

	public int background_mode = 0;

	public int background_color = 0;

	public WebDW_Text Clone() {

		WebDW_Text newOne = new WebDW_Text();

		newOne.band = band;

		newOne.alignment = alignment;

		newOne.text = text;

		newOne.border = border;

		newOne.color = color;

		newOne.x = x;

		newOne.y = y;

		newOne.height = height;

		newOne.width = width;

		newOne.Name = Name;

		newOne.font = font.Clone(); // '�¶����font����

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

	public int getAlignment() {
		return alignment;
	}

	public void setAlignment(int alignment) {
		this.alignment = alignment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getBorder() {
		return border;
	}

	public void setBorder(int border) {
		this.border = border;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public WebDW_Font getFont() {
		return font;
	}

	public void setFont(WebDW_Font font) {
		this.font = font;
	}

	public int getBackground_mode() {
		return background_mode;
	}

	public void setBackground_mode(int background_mode) {
		this.background_mode = background_mode;
	}

	public int getBackground_color() {
		return background_color;
	}

	public void setBackground_color(int background_color) {
		this.background_color = background_color;
	}

}

