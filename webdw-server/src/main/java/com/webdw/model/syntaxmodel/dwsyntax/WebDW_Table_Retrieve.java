package com.webdw.model.syntaxmodel.dwsyntax;

//'table.retrieveԪ�ض��壬����ֻ��һ��pbselectԪ��
public class WebDW_Table_Retrieve {
	public 	WebDW_Table_Retrieve_PBSelect pbselect = new WebDW_Table_Retrieve_PBSelect();

	public WebDW_Table_Retrieve Clone() {

		WebDW_Table_Retrieve newOne = new WebDW_Table_Retrieve();

		newOne.pbselect = pbselect.Clone();

		return newOne;
	}

	public WebDW_Table_Retrieve_PBSelect getPbselect() {
		return pbselect;
	}

	public void setPbselect(WebDW_Table_Retrieve_PBSelect pbselect) {
		this.pbselect = pbselect;
	}

}




