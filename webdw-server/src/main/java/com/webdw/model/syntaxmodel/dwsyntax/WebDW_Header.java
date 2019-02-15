package com.webdw.model.syntaxmodel.dwsyntax;

//'header���Զ���
public class WebDW_Header {
	public int height = 0;

	public int color = 0;

	public WebDW_Header Clone() {
		WebDW_Header newOne = new WebDW_Header();

		newOne.height = height;

		newOne.color = color;

		return newOne;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
