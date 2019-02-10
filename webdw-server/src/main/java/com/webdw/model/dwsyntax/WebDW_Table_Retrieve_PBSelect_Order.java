package com.webdw.model.dwsyntax;

//'���ݱ������OrderBy��������
public class WebDW_Table_Retrieve_PBSelect_Order {
	public String Name = "";// As String '�����������

	public String Asc = "";// As String '�Ƿ�������yes/no

	public WebDW_Table_Retrieve_PBSelect_Order Clone() {

		WebDW_Table_Retrieve_PBSelect_Order newOne = new WebDW_Table_Retrieve_PBSelect_Order();

		newOne.Name = Name;

		newOne.Asc = Asc;

		return newOne;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAsc() {
		return Asc;
	}

	public void setAsc(String asc) {
		Asc = asc;
	}

}

