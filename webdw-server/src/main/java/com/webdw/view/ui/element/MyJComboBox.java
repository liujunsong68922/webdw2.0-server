package com.webdw.view.ui.element;

import java.util.ArrayList;

import com.webdw.view.ui.MyUIComponentConst;
import com.webdw.view.ui.container.MyUIContainer;

/**
 * �Զ����JPanel��,�����򻯴����Ǩ�ƹ���
 * 
 * @author liujunsong
 * 
 */
public class MyJComboBox extends MyUIElement {
	public String _ReadMe="MyJComboBox";
	//values�������п�ѡ�����ϣ����ݵ�ǰ���γ���������б�����
	private String values="";
	
	//selectedIndex，默认值设置为-1
	public int selectedIndex=-1;	

	//selectedItem
//	public String selectedItem="";
	
	public MyJComboBox(String name, ArrayList targetControls,MyUIContainer parent) {
		super(name);
		super.classname = MyUIComponentConst.UITYPE_COMBOBOX; 
		super.setName(name);
		Refresh();
		targetControls.add(this);
		parent.add(this);
	}	

//	public boolean Enabled;
//	
//	public int ListIndex = 0;
	

	

	
	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

//	public boolean isEnabled() {
//		return Enabled;
//	}

//	public void setEnabled(boolean enabled) {
//		Enabled = enabled;
//	}
//
//	public int getListIndex() {
//		return ListIndex;
//	}

//	public void setListIndex(int listIndex) {
//		ListIndex = listIndex;
//	}

//	public String getSelectedItem() {
//		return selectedItem;
//	}
//
//	public void setSelectedItem(String selectedItem) {
//		this.selectedItem = selectedItem;
//	}

	public void Refresh() {
		try{
//		Text = getSelectedItem().toString();
		}catch(Exception e){
			Text="";
		}
//		Enabled = super.isEnabled();
//		ListIndex = getSelectedIndex();
	}

//	public void Enabled(boolean invalue){
//		super.setEnabled(invalue);
//	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

}
