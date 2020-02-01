package com.webdw.model.dwsyntax;

//'�����ж��������ݴ��ڵ�֧�֣�����Column��һ������
//'20090125�մ�����ʮ�����
public class WebDW_Column_DDDW {
	public String Name = ""; // '���ݴ��ڵ����֣�δ��������ַ��������������ݴ���

	public String DisplayColumn = ""; // '��ʾ�е����֣���һ���������ݴ�����

	public String DataColumn = ""; // '�����е����֣�����һ��������ԭ���ݴ���

	public int PercentWidth = 0; // '�ٷֱȱ�ʾ����ʾ���

	public int Lines = 0;

	public int limit = 0;

	public String allowedit = "";

	public String useasborder = "";

	public String case1 = "";

	public String vscrollbar = ""; // '�Ƿ���ʾ��������

	public WebDW_Column_DDDW Clone() {

		WebDW_Column_DDDW newOne = new WebDW_Column_DDDW();

		newOne.Name = Name; // '���ݴ��ڵ����֣�δ��������ַ��������������ݴ���

		newOne.DisplayColumn = DisplayColumn; // '��ʾ�е����֣���һ���������ݴ�����

		newOne.DataColumn = DataColumn; // '�����е����֣�����һ��������ԭ���ݴ���

		newOne.PercentWidth = PercentWidth; // '�ٷֱȱ�ʾ����ʾ���

		newOne.Lines = Lines;

		newOne.limit = limit;

		newOne.allowedit = allowedit;

		newOne.useasborder = useasborder;

		newOne.case1 = case1;

		newOne.vscrollbar = vscrollbar; // '�Ƿ���ʾ��������

		return newOne;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDisplayColumn() {
		return DisplayColumn;
	}

	public void setDisplayColumn(String displayColumn) {
		DisplayColumn = displayColumn;
	}

	public String getDataColumn() {
		return DataColumn;
	}

	public void setDataColumn(String dataColumn) {
		DataColumn = dataColumn;
	}

	public int getPercentWidth() {
		return PercentWidth;
	}

	public void setPercentWidth(int percentWidth) {
		PercentWidth = percentWidth;
	}

	public int getLines() {
		return Lines;
	}

	public void setLines(int lines) {
		Lines = lines;
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

	public String getUseasborder() {
		return useasborder;
	}

	public void setUseasborder(String useasborder) {
		this.useasborder = useasborder;
	}

	public String getCase1() {
		return case1;
	}

	public void setCase1(String case1) {
		this.case1 = case1;
	}

	public String getVscrollbar() {
		return vscrollbar;
	}

	public void setVscrollbar(String vscrollbar) {
		this.vscrollbar = vscrollbar;
	}

}



