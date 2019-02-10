package com.webdw.view.ui.element;

import java.util.ArrayList;

import com.webdw.view.ui.MyUIComponentConst;
import com.webdw.view.ui.container.MyUIContainer;

/**
 * �Զ����JPanel��,�����򻯴����Ǩ�ƹ���
 */
public class MyJLabel extends MyUIElement {
	public String _ReadMe = ("MyJLabel define");

	// constructor
	public MyJLabel(String s1, String name, ArrayList targetControls, MyUIContainer parent) {
		super(s1);
		super.classname = MyUIComponentConst.UITYPE_LABEL;
		super.setName(name);
		targetControls.add(this);
		parent.add(this);
	}
}
