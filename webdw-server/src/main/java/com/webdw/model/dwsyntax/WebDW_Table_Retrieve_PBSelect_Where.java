package com.webdw.model.dwsyntax;

//'�������where��������
public class WebDW_Table_Retrieve_PBSelect_Where {
	public String exp1 = "";

	public String op = "";

	public String exp2 = "";

	public String logic = "";

	public WebDW_Table_Retrieve_PBSelect_Where() {

	}

	public WebDW_Table_Retrieve_PBSelect_Where Clone() {

		WebDW_Table_Retrieve_PBSelect_Where newOne = new WebDW_Table_Retrieve_PBSelect_Where();

		newOne.exp1 = exp1;

		newOne.op = op;

		newOne.exp2 = exp2;

		newOne.logic = logic;

		return newOne;
	}

	public String getExp1() {
		return exp1;
	}

	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getExp2() {
		return exp2;
	}

	public void setExp2(String exp2) {
		this.exp2 = exp2;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

}
