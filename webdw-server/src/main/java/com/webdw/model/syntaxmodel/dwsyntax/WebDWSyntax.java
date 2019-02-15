package com.webdw.model.syntaxmodel.dwsyntax;

public class WebDWSyntax {
	public String ReadMe = ("WebDW Syntax model, define according to PB7.0");

	public WebDW_DataWindow datawindow = new WebDW_DataWindow();

	public WebDW_Header header = new WebDW_Header();

	public WebDW_Summary summary = new WebDW_Summary();

	public WebDW_Footer footer = new WebDW_Footer();

	public WebDW_Detail detail = new WebDW_Detail();

	public WebDW_Table table = new WebDW_Table();

	public WebDW_Text text[] = new WebDW_Text[101];

	public WebDW_Column column[] = new WebDW_Column[101];

	public WebDW_Line lineinfo[] = new WebDW_Line[101];

	public String column_dddw_syntax[] = new String[101];

	public String column_dddw_data[] = new String[101];

	public String SelectSQL = "";

	/**
	 * ��ʼ���Ĺ��캯��
	 * 
	 */
	public WebDWSyntax() {
		int i = 0;
		for (i = 0; i < 101; i++) {
			text[i] = new WebDW_Text();
		}
		for (i = 0; i < 101; i++) {
			column[i] = new WebDW_Column();
		}
		for (i = 0; i < 101; i++) {
			lineinfo[i] = new WebDW_Line();
		}
		for (i = 0; i < 101; i++) {
			column_dddw_syntax[i] = "";
		}
		for (i = 0; i < 101; i++) {
			column_dddw_data[i] = "";
		}
	}

	public WebDWSyntax Clone() {
		WebDWSyntax newOne = new WebDWSyntax();

		newOne.datawindow = datawindow.Clone();

		newOne.header = header.Clone();

		newOne.summary = summary.Clone();

		newOne.footer = footer.Clone();

		newOne.detail = detail.Clone();

		newOne.table = table.Clone();

		int i = 0;
		for (i = 0; i < 101; i++) {
			newOne.text[i] = text[i].Clone();
		}
		for (i = 0; i < 101; i++) {
			newOne.column[i] = column[i].Clone();
		}
		for (i = 0; i < 101; i++) {
			newOne.lineinfo[i] = lineinfo[i].Clone();
		}
		for (i = 0; i < 101; i++) {
			newOne.column_dddw_syntax[i] = column_dddw_syntax[i];
		}
		for (i = 0; i < 101; i++) {
			newOne.column_dddw_data[i] = column_dddw_data[i];
		}
		return newOne;
	}

	/**
	 * Warning:Test only,Donot Use This Function in Program!!!
	 * 
	 * @return
	 */
	public int getColumnNumber() {
		for (int i = 1; i <= 100; i++) {
			if (column[i].Name.length() == 0) {
				return i - 1;
			}
		}
		return 100;
	}

	public WebDW_DataWindow getDatawindow() {
		return datawindow;
	}

	public void setDatawindow(WebDW_DataWindow datawindow) {
		this.datawindow = datawindow;
	}

	public WebDW_Header getHeader() {
		return header;
	}

	public void setHeader(WebDW_Header header) {
		this.header = header;
	}

	public WebDW_Summary getSummary() {
		return summary;
	}

	public void setSummary(WebDW_Summary summary) {
		this.summary = summary;
	}

	public WebDW_Footer getFooter() {
		return footer;
	}

	public void setFooter(WebDW_Footer footer) {
		this.footer = footer;
	}

	public WebDW_Detail getDetail() {
		return detail;
	}

	public void setDetail(WebDW_Detail detail) {
		this.detail = detail;
	}

	public WebDW_Table getTable() {
		return table;
	}

	public void setTable(WebDW_Table table) {
		this.table = table;
	}

	public WebDW_Text[] getText() {
		return text;
	}

	public void setText(WebDW_Text[] text) {
		this.text = text;
	}

	public WebDW_Column[] getColumn() {
		return column;
	}

	public void setColumn(WebDW_Column[] column) {
		this.column = column;
	}

	public WebDW_Line[] getLineinfo() {
		return lineinfo;
	}

	public void setLineinfo(WebDW_Line[] lineinfo) {
		this.lineinfo = lineinfo;
	}

	public String[] getColumn_dddw_syntax() {
		return column_dddw_syntax;
	}

	public void setColumn_dddw_syntax(String[] column_dddw_syntax) {
		this.column_dddw_syntax = column_dddw_syntax;
	}

	public String[] getColumn_dddw_data() {
		return column_dddw_data;
	}

	public void setColumn_dddw_data(String[] column_dddw_data) {
		this.column_dddw_data = column_dddw_data;
	}

	public String getSelectSQL() {
		return SelectSQL;
	}

	public void setSelectSQL(String selectSQL) {
		SelectSQL = selectSQL;
	}
}
