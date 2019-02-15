package com.webdw.model.syntaxmodel.dwsyntax;

//'�����ж�����ѡ����֧�ֶ��壬����Column��һ������
public class WebDW_Column_ComboBox {
	public int limit = 0; // '���ƣ����岻��

	public String allowedit = ""; // '�Ƿ���Ա༭ yes /no

	public String case1 = ""; // '��Сд,����Ϊcase1

	public String useasborder = ""; // '�Ƿ���ʾ��ͷ yes/no

	public WebDW_Column_ComboBox Clone() {

		WebDW_Column_ComboBox newOne = new WebDW_Column_ComboBox();

		newOne.limit = limit; // '���ƣ����岻��

		newOne.allowedit = allowedit; // '�Ƿ���Ա༭ yes /no

		newOne.case1 = case1; // '��Сд,����Ϊcase1

		newOne.useasborder = useasborder; // '�Ƿ���ʾ��ͷ yes/no
		return newOne;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getAllowedit() {
		return allowedit;
	}

	public void setAllowedit(String allowedit) {
		this.allowedit = allowedit;
	}

	public String getCase1() {
		return case1;
	}

	public void setCase1(String case1) {
		this.case1 = case1;
	}

	public String getUseasborder() {
		return useasborder;
	}

	public void setUseasborder(String useasborder) {
		this.useasborder = useasborder;
	}

}

