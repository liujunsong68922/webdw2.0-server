package com.webdw.model.dwsyntax;

//'table��retrieve��pbselectԪ�����Զ���
//'��һ���ֿ��ܻ������Ҫ�����к�������
//'�����Ŷ���DW�ļ���ʽ������������չ
public class WebDW_Table_Retrieve_PBSelect {
	public String version = "";

	public String table[] = new String[11]; // '�������������10����

	public String column[] = new String[101]; // '�������������100����

	public WebDW_Table_Retrieve_PBSelect_Join join[] = new WebDW_Table_Retrieve_PBSelect_Join[11];// '�����Ӷ��壬���10������

	public WebDW_Table_Retrieve_PBSelect_Where where[] = new WebDW_Table_Retrieve_PBSelect_Where[11]; // 'where�������壬���10��where����

	public WebDW_Table_Retrieve_PBSelect_Order order[] = new WebDW_Table_Retrieve_PBSelect_Order[11]; // 'order

	// �����趨,���10��

	/*
	 * ���캯������ʼ��
	 */
	public WebDW_Table_Retrieve_PBSelect() {
		int i;
		for (i = 0; i < 11; i++) {
			table[i] = "";
		}
		for (i = 0; i < 101; i++) {
			column[i] = "";
		}
		for (i = 0; i < 11; i++) {
			join[i] = new WebDW_Table_Retrieve_PBSelect_Join();
		}
		for (i = 0; i < 11; i++) {
			where[i] = new WebDW_Table_Retrieve_PBSelect_Where();
		}
		for (i = 0; i < 11; i++) {
			order[i] = new WebDW_Table_Retrieve_PBSelect_Order();
		}

	}

	public WebDW_Table_Retrieve_PBSelect Clone() {
		WebDW_Table_Retrieve_PBSelect newOne = new WebDW_Table_Retrieve_PBSelect();
		int i;
		for (i = 0; i < 11; i++) {
			newOne.table[i] = table[i];
		}
		for (i = 0; i < 101; i++) {
			newOne.column[i] = column[i];
		}
		for (i = 0; i < 11; i++) {
			newOne.join[i] = join[i].Clone();
		}
		for (i = 0; i < 11; i++) {
			newOne.where[i] = where[i].Clone();
		}
		for (i = 0; i < 11; i++) {
			newOne.order[i] = order[i].Clone();
		}

		return newOne;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String[] getTable() {
		return table;
	}

	public void setTable(String[] table) {
		this.table = table;
	}

	public String[] getColumn() {
		return column;
	}

	public void setColumn(String[] column) {
		this.column = column;
	}

	public WebDW_Table_Retrieve_PBSelect_Join[] getJoin() {
		return join;
	}

	public void setJoin(WebDW_Table_Retrieve_PBSelect_Join[] join) {
		this.join = join;
	}

	public WebDW_Table_Retrieve_PBSelect_Where[] getWhere() {
		return where;
	}

	public void setWhere(WebDW_Table_Retrieve_PBSelect_Where[] where) {
		this.where = where;
	}

	public WebDW_Table_Retrieve_PBSelect_Order[] getOrder() {
		return order;
	}

	public void setOrder(WebDW_Table_Retrieve_PBSelect_Order[] order) {
		this.order = order;
	}

}


