package com.webdw.model.syntaxmodel.dwsyntax;

//'table���Զ���
public class WebDW_Table {
	public WebDW_Table_Column Columns[] = new WebDW_Table_Column[101]; // '���100��

	public WebDW_Table_Retrieve retrieve = new WebDW_Table_Retrieve(); // '���¶���retrieve�����������һ���ṹ

	public String update = "";

	public String updatewhere = "";

	public String updatekeyinplace = "";

	/**
	 * ���캯��,��ʼ������
	 * 
	 */
	public WebDW_Table() {
		for (int i = 0; i < 101; i++) {
			Columns[i] = new WebDW_Table_Column();
		}
	}

	public WebDW_Table Clone() {

		WebDW_Table newOne = new WebDW_Table();

		for (int i = 0; i < 101; i++) {
			newOne.Columns[i] = Columns[i].Clone();
		}

		newOne.retrieve = retrieve.Clone();

		newOne.update = update;

		newOne.updatewhere = updatewhere;

		newOne.updatekeyinplace = updatekeyinplace;

		return newOne;
	}

	public WebDW_Table_Column[] getColumns() {
		return Columns;
	}

	public void setColumns(WebDW_Table_Column[] columns) {
		Columns = columns;
	}

	public WebDW_Table_Retrieve getRetrieve() {
		return retrieve;
	}

	public void setRetrieve(WebDW_Table_Retrieve retrieve) {
		this.retrieve = retrieve;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getUpdatewhere() {
		return updatewhere;
	}

	public void setUpdatewhere(String updatewhere) {
		this.updatewhere = updatewhere;
	}

	public String getUpdatekeyinplace() {
		return updatekeyinplace;
	}

	public void setUpdatekeyinplace(String updatekeyinplace) {
		this.updatekeyinplace = updatekeyinplace;
	}

}



