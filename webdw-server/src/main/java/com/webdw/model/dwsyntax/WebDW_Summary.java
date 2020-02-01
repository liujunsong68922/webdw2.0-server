package com.webdw.model.dwsyntax;

//'summary���Զ���
public class WebDW_Summary {
	public int height = 0;

	public int color = 0;

	public WebDW_Summary Clone() {

		WebDW_Summary newOne = new WebDW_Summary();

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
