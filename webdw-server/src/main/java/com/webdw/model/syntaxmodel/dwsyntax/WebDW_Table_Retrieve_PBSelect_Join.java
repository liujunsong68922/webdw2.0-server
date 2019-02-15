package com.webdw.model.syntaxmodel.dwsyntax;

//'������Ӷ���
public class WebDW_Table_Retrieve_PBSelect_Join {
	public String join_left = "";

	public String join_op = "";

	public String join_right = "";

	public WebDW_Table_Retrieve_PBSelect_Join() {

	}

	public WebDW_Table_Retrieve_PBSelect_Join Clone() {

		WebDW_Table_Retrieve_PBSelect_Join newOne = new WebDW_Table_Retrieve_PBSelect_Join();

		newOne.join_left = join_left;

		newOne.join_op = join_op;

		newOne.join_right = join_right;

		return newOne;
	}

	public String getJoin_left() {
		return join_left;
	}

	public void setJoin_left(String join_left) {
		this.join_left = join_left;
	}

	public String getJoin_op() {
		return join_op;
	}

	public void setJoin_op(String join_op) {
		this.join_op = join_op;
	}

	public String getJoin_right() {
		return join_right;
	}

	public void setJoin_right(String join_right) {
		this.join_right = join_right;
	}

}

