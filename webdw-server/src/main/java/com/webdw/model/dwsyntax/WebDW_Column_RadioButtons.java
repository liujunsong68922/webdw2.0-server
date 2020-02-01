package com.webdw.model.dwsyntax;

//'�����жԵ�ѡ��ť���֧�ֶ��壬����Column��һ������
public class WebDW_Column_RadioButtons {
	public int Columns = 0; // '��ѡŦ��ʾ�е�����

	public WebDW_Column_RadioButtons Clone() {

		WebDW_Column_RadioButtons newOne = new WebDW_Column_RadioButtons();

		newOne.Columns = Columns;

		return newOne;
	}

	public int getColumns() {
		return Columns;
	}

	public void setColumns(int columns) {
		Columns = columns;
	}

}


