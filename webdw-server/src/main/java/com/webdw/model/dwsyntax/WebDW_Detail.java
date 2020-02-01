package com.webdw.model.dwsyntax;

//'detail���Զ���
public class WebDW_Detail {
	public int height = 0;

	public int color = 0;

	public WebDW_Detail Clone() {
		WebDW_Detail newOne = new WebDW_Detail();

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
