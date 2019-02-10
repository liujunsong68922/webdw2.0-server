package com.webdw.view.ui.container;

import java.util.ArrayList;

import com.webdw.view.ui.MyUIComponentConst;

/**
 * �Զ����JPanel��,�����򻯴����Ǩ�ƹ���
 * 
 * @author liujunsong
 * 
 */
public class MyJPanel extends MyUIContainer {

	public String _ReadMe = "JPanel,this is a DIV in DOM.";

	// constructor
	public MyJPanel() {
		super.classname = MyUIComponentConst.UITYPE_PANEL;
	}

	// constructor
	public MyJPanel(String sname) {
		super.classname = MyUIComponentConst.UITYPE_PANEL;
		super.setName(sname);
	}
	
	// constructor
	public MyJPanel(String name, ArrayList targetControls, MyUIContainer parent) {
		super.classname = MyUIComponentConst.UITYPE_PANEL;
		super.setName(name);
		targetControls.add(this);
		parent.add(this);
	}

}
