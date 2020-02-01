package com.webdw.model.dwsyntax;

//'footer���Զ���
public class WebDW_Footer {
	public int height = 0;

	public int color = 0;

	public WebDW_Footer Clone() {
		WebDW_Footer newOne = new WebDW_Footer();

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


