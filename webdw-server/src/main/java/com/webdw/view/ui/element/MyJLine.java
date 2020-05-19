package com.webdw.view.ui.element;

import java.util.ArrayList;

import com.webdw.view.ui.MyUIComponentConst;
import com.webdw.view.ui.container.MyUIContainer;

public class MyJLine extends MyUIElement {
	public String _ReadMe = ("MyJLine define");
	
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	/**
	 * MyJLine的构造函数
	 * @param sname
	 * @param targetControls
	 * @param parent
	 */
	public MyJLine(String sname, ArrayList targetControls, MyUIContainer parent) {
		super("");
		super.setName(sname);
		super.classname = MyUIComponentConst.UITYPE_LINE;
		targetControls.add(this);
		parent.add(this);
	}
}
