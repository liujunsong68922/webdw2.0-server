package com.webdw.model;

import java.util.ArrayList;

import com.webdw.common.Golbal;
import com.webdw.common.MyInt;
import com.webdw.common.VBFunction;
import com.webdw.common.exception.WebDWException;
import com.webdw.model.dboper.DWConfig;
import com.webdw.model.dwsyntax.WebDWSyntax;
import com.webdw.model.dwsyntax.WebDW_Column;

/**
 * Generate WebDWSyntax Object(from db config)
 * @author admin
 * 
 */
public class CWebDW_Create extends Golbal {
	public String ReadMe = ("WebDW Create function");

	public String dwString = "";
	public String errString = "";
	public WebDWSyntax local_webdw = new WebDWSyntax();//

	// Create using dwname
	public int CreateByDwname(String dwname) throws WebDWException {
		DWConfig dwconfig = new DWConfig();
		String strSyntax = dwconfig.getDWSyntaxStringByDWName(dwname);
		if (strSyntax == null || strSyntax.length() == 0) {
			throw new WebDWException("Cannot find Datawindow Syntax:" + dwname);
		}
		return this.CreateBySyntaxString(strSyntax);
	}

	// Create using datawindow syntax String
	public int CreateBySyntaxString(String inString) throws WebDWException {
		// 'step1 get g_webdw
		dwString = inString;
		errString = "";

		// 'step2 read datawindow
		if (readWebDW01_Datawindow() == -1) {
			throw new WebDWException("CreateDW.datawindow Error: " + errString);
		}

		// 'step3 read header
		if (readWebDW02_Header() == -1) {
			throw new WebDWException("CreateDW.header Error: " + errString);
		}

		// 'step4 read summary
		if (readWebDW03_Summary() == -1) {
			throw new WebDWException("CreateDW.summary Error: " + errString);
		}

		// 'step5 read footer
		if (readWebDW04_Footer() == -1) {
			throw new WebDWException("CreateDW.footer Error: " + errString);
		}

		// 'step6 read detail
		if (readWebDW05_Detail() == -1) {
			throw new WebDWException("CreateDW.detal Error: " + errString);
		}

		// 'step7 read table
		if (readWebDW06_Table() == -1) {
			throw new WebDWException("CreateDW.table Error: " + errString);
		}

		// 'step8 read text
		if (readWebDW07_Text() == -1) {
			throw new WebDWException("CreateDW.text Error: " + errString);
		}

		// 'step9 read column
		if (readWebDW08_Column() == -1) {
			throw new WebDWException("CreateDW.column Error: " + errString);
		}

		// 'step10 read line
		if (readWebDW09_Line() == -1) {
			throw new WebDWException("CreateDW.line Error: " + errString);
		}
		// 'return 0,run ok.
		return 0;
	}

	private String getElementDesc(String inString, String eleName, int beginPos, MyInt findPos) {
		int iBeg;
		int leftPos;
		int iflag;

		int i;
		String s;

		findPos.intvalue = -1; // '��ʼ��findPos��ֵ��������ı䣬���صľ���-1

		iBeg = InStr(beginPos, inString, eleName);
		if (iBeg <= 0) {
			return ""; // '���ؿ��ַ�������û���ҵ����Ԫ��
		}

		leftPos = InStr(iBeg, inString, "("); // '�õ��������ŵ�λ��
		if (leftPos <= 0) {
			return ""; // '���ؿ��ַ�������û���ҵ����Ԫ��
		}

		iflag = 0; // 'ÿ�ҵ�һ��(��iflag++,�ҵ�һ��) iflag --
		for (i = leftPos + 1; i <= VBFunction.Len(inString); i++) {
			s = Mid(inString, i, 1); // 'ȡ��ǰ�ַ���

			if ((s.equals("(") || s.equals(")")) && Mid(inString, i - 1, 1).equals("~")) { // '�����()����Ҫ�ж��ϸ��ַ��Ƿ���~,����ǲ�����
				continue;
			}

			if (s.equals("(")) {
				iflag = iflag + 1;
				continue; // '��������ѭ��
			}

			if (s.equals(")")) { // '��ǰֵΪ)ʱ��Ҫ�ж�iflag��ֵ
				if (iflag == 0) { // 'iflag=0�����Խ���ѭ��
					String s1 = Mid(inString, leftPos, i - leftPos + 1);
					findPos.intvalue = leftPos; // '�ҵ���λ����leftPos
					return s1;
				} else {
					iflag = iflag - 1; // '����iflag��ȥ1
				}
			}
		}

		return "";
	}

	private String removeQuote(String strIn) {
		int ilen;
		ilen = Len(strIn);

		if (strIn.equals("")) {
			return "";
		}
		if (Left(strIn, 1).equals("\"") && Right(strIn, 1).equals("\"")) {
			return Mid(strIn, 2, ilen - 2);
		}
		return strIn;
	}

	private String getElementProp2(String eleString, String paraName, int begPos, String defValue, MyInt retFlag,
			String sep) {
		int iBeg;
		int iEnd;
		int ipos;
		int i;
		int iflag;
		String s;
		String svalue;

		retFlag.intvalue = -1;
		ipos = InStr(begPos, eleString, paraName + "="); // '�ҵ��������ƵĿ�ʼ��
		if (ipos <= 0) { // '�Ҳ������˳�
			return defValue; // '����Ĭ��ֵ
		}

		iBeg = ipos + Len(paraName + "="); // 'iBeg����ֵ�Ŀ�ʼ��
		iflag = 0;
		for (i = iBeg; i <= Len(eleString); i++) {
			s = Mid(eleString, i, 1);

			if (s.equals("\"")) { // '�����ǰ�ַ��������ţ���ô���ñ�־
				if (iflag == 0) {
					iflag = 1;
				} else {
					iflag = 0;
				}
				continue;
			}

			if (s.equals(sep)) { // '���s�ǽ������ţ���Ҫ����iFlag���ж�
				if (iflag == 0) { // '��������ַ����ڣ���ô���˳�
					svalue = Mid(eleString, iBeg, i - iBeg);
					svalue = removeQuote(svalue); // 'ȥ����ͷ�ͽ�β������
					// getElementProp2 = svalue
					retFlag.intvalue = 0; // '��־�ɹ�����
					return svalue; // '�˳��˹���
				}
			}

		}

		return defValue; // '����Ĭ��ֵ
	}

	private String getElementProp(String eleString, String paraName, int begPos, String defValue, MyInt retFlag) {
		String svalue;
		svalue = getElementProp2(eleString, paraName, begPos, defValue, retFlag, " ");
		return svalue;
	}

	private ArrayList getAllElement(String inString, String eletype) {
		ArrayList myarray = new ArrayList();
		String stext = "";
		MyInt ipos = new MyInt(0);

		stext = getElementDesc(inString, eletype + "(", 1, ipos);
		while (ipos.intvalue > 0) {
			myarray.add(stext); // '�����ڼ���sText
			stext = getElementDesc(inString, eletype + "(", ipos.intvalue + 1, ipos);
		}

		return myarray;
	}

	private int readWebDW01_Datawindow() {
		String sDataWindow;
		MyInt ipos = new MyInt(0);
		MyInt iflag = new MyInt(0);

		sDataWindow = getElementDesc(dwString, "datawindow", 1, ipos);

		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW01_Datawindow:���ݸ�ʽ���Ϸ�:�Ҳ���datawindow����(����)";
			return -1;
		}

		local_webdw.datawindow.unit = getElementProp(sDataWindow, "unit", 1, "0", iflag);
		local_webdw.datawindow.timer_interval = getElementProp(sDataWindow, "time_interval", 1, "0", iflag);
		local_webdw.datawindow.color = toInt(getElementProp(sDataWindow, "color", 1, "0", iflag));
		local_webdw.datawindow.processiong = getElementProp(sDataWindow, "processiong", 1, "", iflag);
		local_webdw.datawindow.HTMLDW = getElementProp(sDataWindow, "HTMLDW", 1, "no", iflag);
		local_webdw.datawindow.print_documentname = getElementProp(sDataWindow, "print.documentname", 1, "", iflag);
		local_webdw.datawindow.print_orientation = toInt(
				getElementProp(sDataWindow, "print.orientation", 1, "0", iflag));
		local_webdw.datawindow.print_margin_left = toInt(
				getElementProp(sDataWindow, "print.margin.left", 1, "110", iflag));
		local_webdw.datawindow.print_margin_right = toInt(
				getElementProp(sDataWindow, "print.margin.right", 1, "110", iflag));
		local_webdw.datawindow.print_margin_top = toInt(
				getElementProp(sDataWindow, "print.margin.top", 1, "96", iflag));
		local_webdw.datawindow.print_margin_bottom = toInt(
				getElementProp(sDataWindow, "print.margin.bottom", 1, "96", iflag));
		local_webdw.datawindow.print_paper_source = toInt(
				getElementProp(sDataWindow, "print.paper.source", 1, "0", iflag));
		local_webdw.datawindow.print_paper_size = toInt(getElementProp(sDataWindow, "print.paper.size", 1, "0", iflag));
		local_webdw.datawindow.print_prompt = getElementProp(sDataWindow, "print.prompt", 1, "no", iflag);
		local_webdw.datawindow.print_buttons = getElementProp(sDataWindow, "print.buttons", 1, "no", iflag);
		local_webdw.datawindow.print_preview_buttons = getElementProp(sDataWindow, "print.preview.buttons", 1, "no",
				iflag);
		local_webdw.datawindow.grid_lines = getElementProp(sDataWindow, "grid.lines", 1, "-1", iflag);
		return 0;
	}

	private int readWebDW02_Header() {
		String sHeader = "";
		MyInt ipos = new MyInt(0);
		MyInt iflag = new MyInt(0);

		sHeader = getElementDesc(dwString, "header", 1, ipos);

		if (ipos.intvalue < 0) {
			return 0;
		}

		local_webdw.header.height = toInt(getElementProp(sHeader, "height", 1, "0", iflag));
		local_webdw.header.color = toInt(getElementProp(sHeader, "color", 1, "0", iflag));
		return 0;

	}

	private int readWebDW03_Summary() {
		String sSummary = "";
		MyInt ipos = new MyInt(0);
		MyInt iflag = new MyInt(0);

		sSummary = getElementDesc(dwString, "summary", 1, ipos);

		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW03_Summary:���ݸ�ʽ���Ϸ�:�Ҳ���summary����(����)";
			return -1;
		}

		local_webdw.summary.height = toInt(getElementProp(sSummary, "height", 1, "0", iflag));
		local_webdw.summary.color = toInt(getElementProp(sSummary, "color", 1, "0", iflag));
		return 0;
	}

	private int readWebDW04_Footer() {
		String sFooter = "";
		MyInt ipos = new MyInt(0);
		MyInt iflag = new MyInt(0);

		sFooter = getElementDesc(dwString, "footer", 1, ipos);

		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW04_Footer:���ݸ�ʽ���Ϸ�:�Ҳ���footer����(����)";
			return -1;
		}

		local_webdw.footer.height = toInt(getElementProp(sFooter, "height", 1, "0", iflag));
		local_webdw.footer.color = toInt(getElementProp(sFooter, "color", 1, "0", iflag));
		return 0;
	}

	private int readWebDW05_Detail() {
		String sDetail = "";
		MyInt ipos = new MyInt(0);
		MyInt iflag = new MyInt(0);

		sDetail = getElementDesc(dwString, "detail", 1, ipos);

		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW05_Detail:���ݸ�ʽ���Ϸ�:�Ҳ���detail����(����)";
			return -1;
		}
		local_webdw.detail.height = toInt(getElementProp(sDetail, "height", 1, "0", iflag));
		local_webdw.detail.color = toInt(getElementProp(sDetail, "color", 1, "0", iflag));
		return 0;
	}

	private int readWebDW06_Table() {
		String stable = "";
		MyInt ipos = new MyInt(0);
		MyInt iflag = new MyInt(0);
		ArrayList columnArray = new ArrayList();
		Object obj;
		String stemp;
		int id = 0;

		stable = getElementDesc(dwString, "table", 1, ipos);

		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW06_Table:���ݸ�ʽ���Ϸ�:�Ҳ���table����(����)";
			return -1;

		}

		columnArray = getAllElement(stable, "column=");
		id = 0;
		for (int i = 0; i < columnArray.size(); i++) {
			stemp = (String) columnArray.get(i);

			id = id + 1;
			if (id > 100) {
				errString = "ERROR:readWebDW06_Table:SELECT���г���100��!";
				return -1;
			}

			local_webdw.table.Columns[id].type = getElementProp(stemp, "type", 1, "", iflag);
			local_webdw.table.Columns[id].update = getElementProp(stemp, "update", 1, "yes", iflag);
			local_webdw.table.Columns[id].updatewhereclause = getElementProp(stemp, "updatewhereclause", 1, "yes",
					iflag);
			local_webdw.table.Columns[id].key = getElementProp(stemp, "key", 1, "", iflag);
			local_webdw.table.Columns[id].Name = getElementProp(stemp, "name", 1, "", iflag);
			local_webdw.table.Columns[id].dbname = getElementProp(stemp, "dbname", 1, "", iflag);
			local_webdw.table.Columns[id].values = getElementProp(stemp, "values", 1, "", iflag);

		}

		// 'update,updatewhere,updatekeyinplace
		local_webdw.table.update = getElementProp(stable, "update", 1, "", iflag);
		local_webdw.table.updatewhere = getElementProp(stable, "updatewhere", 1, "", iflag);
		local_webdw.table.updatekeyinplace = getElementProp(stable, "updatekeyinplace", 1, "", iflag);

		String strPBSelect = "";
		String Columns[] = new String[101]; // '����Ҫ��ȡ��column�����ƣ����column�����ݿ������
		String tables[] = new String[11]; // '����Ҫ��ȡ��table���ƣ����10��
		ArrayList temparray = new ArrayList();
		String tempobj = "";
		MyInt iret = new MyInt(0);

		String select_tablelist = ""; // 'tabel �Ӿ�
		String select_columnlist = ""; // 'column �Ӿ�
		String select_join = ""; // 'join �����Ӿ�
		String select_where = ""; // 'where�Ӿ�

		String stablename = "";
		int table_id = 0;
		String scolumnname = "";
		int column_id = 0;

		int join_id = 0;
		String join_left = "";
		String join_op = "";
		String join_right = "";

		int where_id = 0;
		String exp1 = "";
		String where_op = "";
		String exp2 = "";
		String logic = "";

		int order_id = 0;// As Long
		String order_name = "";// As String
		String order_asc = "";// As String

		strPBSelect = getElementProp(stable, "retrieve", 1, "", iflag); // '�ȶ�ȡretrieve���Գ���

		// '��ȡtable����
		temparray = getAllElement(strPBSelect, "TABLE"); // '�õ�TABLEԪ�صĶ���
		table_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			stablename = getElementProp2(stemp, "NAME", 1, "", iret, ")"); // '����)��Ϊ�����ָ����

			if (iret.intvalue == -1) {
				continue;
			}

			table_id = table_id + 1;
			if (table_id > 10) {
				break;
			}
			local_webdw.table.retrieve.pbselect.table[table_id] = stablename; // '�洢tableName��ֵ

		}

		// '��ȡcolumn����
		temparray = getAllElement(strPBSelect, "COLUMN"); // '�õ�columnԪ�ض���
		column_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			scolumnname = getElementProp2(stemp, "NAME", 1, "", iret, ")");

			if (iret.intvalue == -1) {
				continue;
			}

			column_id = column_id + 1;
			if (column_id > 100) {
				break;
			}
			local_webdw.table.retrieve.pbselect.column[column_id] = scolumnname; // '�洢column��NAME
		}

		// '��ȡjoin����
		temparray = getAllElement(strPBSelect, "JOIN "); // '�õ�JOINԪ�ض��壬������һ���ո�
		join_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			join_left = getElementProp2(stemp, "LEFT", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			// ��̬���ɵ����ݴ��ڵ�OP������һ���ո񣬲��Ӵ˿ո��ȡ����
			// �����ַ��������Կո���Ϊ�綨���ţ���Ҫ��R��Ϊ�綨��
			join_op = getElementProp2(stemp, "OP ", 1, "", iret, "R");
			if (iret.intvalue == -1) {
				continue;
			}

			join_right = getElementProp2(stemp, "RIGHT", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			join_id = join_id + 1;
			if (join_id > 10) {
				break;
			}
			local_webdw.table.retrieve.pbselect.join[join_id].join_left = join_left;
			local_webdw.table.retrieve.pbselect.join[join_id].join_op = join_op;
			local_webdw.table.retrieve.pbselect.join[join_id].join_right = join_right;
		}

		temparray = getAllElement(strPBSelect, "WHERE"); // '�õ�WhereԪ�ض���
		where_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			exp1 = getElementProp2(stemp, "EXP1", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			where_op = getElementProp2(stemp, "OP", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			exp2 = getElementProp2(stemp, "EXP2", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			logic = getElementProp2(stemp, "LOGIC", 1, "", iret, " ");

			where_id = where_id + 1;
			if (where_id > 10) {
				break;
			}
			local_webdw.table.retrieve.pbselect.where[where_id].exp1 = exp1;
			local_webdw.table.retrieve.pbselect.where[where_id].op = where_op;
			local_webdw.table.retrieve.pbselect.where[where_id].exp2 = exp2;
			local_webdw.table.retrieve.pbselect.where[where_id].logic = logic;

		}

		temparray = getAllElement(strPBSelect, "ORDER");// '�õ�orderԪ�ض���
		order_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			order_name = getElementProp2(stemp, "NAME", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			order_asc = getElementProp2(stemp, "ASC", 1, "yes", iret, " ");// 'Ĭ��Ϊ����
			if (iret.intvalue == -1) {
				continue;
			}

			order_id = order_id + 1;

			if (order_id > 10) {
				break;
			}

			local_webdw.table.retrieve.pbselect.order[order_id].Name = order_name;
			local_webdw.table.retrieve.pbselect.order[order_id].Asc = order_asc;
		}

		return 0;
	}

	private int readWebDW07_Text() {
		ArrayList textArray = new ArrayList();
		Object obj;
		String stemp = "";
		int id = 0;
		MyInt iret = new MyInt(0);

		textArray = getAllElement(dwString, "text");
		id = 0;
		for (int i = 0; i < textArray.size(); i++) {
			stemp = (String) textArray.get(i);
			if (Len(stemp) < 10) {
				continue;
			}

			id = id + 1;

			if (id > 100) {
				errString = "ERROR:readWebDW07_Text:���ɶ�ȡ100����ǩ!";
				return -1;
			}

			local_webdw.text[id].band = getElementProp(stemp, "band", 1, "detail", iret);
			local_webdw.text[id].alignment = toInt(getElementProp(stemp, "alignment", 1, "1", iret));
			local_webdw.text[id].text = getElementProp(stemp, "text", 1, "", iret);
			local_webdw.text[id].border = toInt(getElementProp(stemp, "border", 1, "0", iret));
			local_webdw.text[id].color = toInt(getElementProp(stemp, "color", 1, "0", iret));
			local_webdw.text[id].x = toInt(getElementProp(stemp, "x", 1, "0", iret));
			local_webdw.text[id].y = toInt(getElementProp(stemp, "y", 1, "0", iret));
			local_webdw.text[id].height = toInt(getElementProp(stemp, "height", 1, "0", iret));
			local_webdw.text[id].width = toInt(getElementProp(stemp, "width", 1, "0", iret));
			local_webdw.text[id].Name = getElementProp(stemp, "name", 1, "", iret);

			local_webdw.text[id].font.face = getElementProp(stemp, "font.face", 1, "", iret);
			local_webdw.text[id].font.height = toInt(getElementProp(stemp, "font.height", 1, "", iret));
			local_webdw.text[id].font.weight = toInt(getElementProp(stemp, "font.weight", 1, "", iret));
			local_webdw.text[id].font.family = toInt(getElementProp(stemp, "font.family", 1, "", iret));
			local_webdw.text[id].font.pitch = toInt(getElementProp(stemp, "font.pitch", 1, "", iret));
			local_webdw.text[id].font.charset = toInt(getElementProp(stemp, "font.charset", 1, "", iret));
			local_webdw.text[id].font.italic = toInt(getElementProp(stemp, "font.italic", 1, "0", iret));
			local_webdw.text[id].font.underline = toInt(getElementProp(stemp, "font.underline", 1, "0", iret));
			local_webdw.text[id].font.strikethrough = toInt(getElementProp(stemp, "font.strikethrough", 1, "0", iret));

			local_webdw.text[id].background_mode = toInt(getElementProp(stemp, "background.mode", 1, "", iret));
			local_webdw.text[id].background_color = toInt(getElementProp(stemp, "background.color", 1, "", iret));
		}
		return 0;
	}

	private int readWebDW08_Column() {
		ArrayList columnArray = new ArrayList();
		Object obj;
		String sColumn;
		int id;
		MyInt iret = new MyInt(0);
		WebDW_Column temp_webdw_column = new WebDW_Column();

		columnArray = getAllElement(dwString, "column");
		id = 0;
		// 'ѭ������
		for (int i = 0; i < columnArray.size(); i++) {
			sColumn = (String) columnArray.get(i);
			id = id + 1;

			if (id > 100) {
				errString = "ERROR:readWebDW08_Column:���ɶ�ȡ100����!";
				return -1;
			}

			temp_webdw_column = new WebDW_Column();
			temp_webdw_column.band = getElementProp(sColumn, "band", 1, "1", iret);
			temp_webdw_column.id = toInt(getElementProp(sColumn, "id", 1, "0", iret));
			temp_webdw_column.alignment = toInt(getElementProp(sColumn, "alignment", 1, "1", iret));
			temp_webdw_column.tabsequence = toInt(getElementProp(sColumn, "tabsequence", 1, "0", iret));
			temp_webdw_column.border = toInt(getElementProp(sColumn, "border", 1, "1", iret));
			temp_webdw_column.color = toInt(getElementProp(sColumn, "color", 1, "0", iret));

			temp_webdw_column.x = toInt(getElementProp(sColumn, "x", 1, "0", iret));
			temp_webdw_column.y = toInt(getElementProp(sColumn, "y", 1, "0", iret));
			temp_webdw_column.height = toInt(getElementProp(sColumn, "height", 1, "0", iret));
			temp_webdw_column.width = toInt(getElementProp(sColumn, "width", 1, "0", iret));
			temp_webdw_column.format = getElementProp(sColumn, "format", 1, "", iret);
			temp_webdw_column.Name = getElementProp(sColumn, "name", 1, "", iret);
			temp_webdw_column.tag = getElementProp(sColumn, "tag", 1, "", iret);

			temp_webdw_column.edit_limit = toInt(getElementProp(sColumn, "edit.limit", 1, "0", iret));
			temp_webdw_column.edit_case = getElementProp(sColumn, "edit.case", 1, "any", iret);
			temp_webdw_column.edit_focusrectangle = getElementProp(sColumn, "edit.focusrectangle", 1, "no", iret);
			temp_webdw_column.edit_autoselect = getElementProp(sColumn, "edit.autoselect", 1, "no", iret);
			temp_webdw_column.edit_autohscroll = getElementProp(sColumn, "edit.autohscroll", 1, "yes", iret);

			temp_webdw_column.font.face = getElementProp(sColumn, "font.face", 1, "", iret);
			temp_webdw_column.font.height = toInt(getElementProp(sColumn, "font.height", 1, "", iret));
			temp_webdw_column.font.weight = toInt(getElementProp(sColumn, "font.weight", 1, "", iret));
			temp_webdw_column.font.family = toInt(getElementProp(sColumn, "font.family", 1, "", iret));
			temp_webdw_column.font.pitch = toInt(getElementProp(sColumn, "font.pitch", 1, "", iret));
			temp_webdw_column.font.charset = toInt(getElementProp(sColumn, "font.charset", 1, "", iret));
			temp_webdw_column.font.italic = toInt(getElementProp(sColumn, "font.italic", 1, "0", iret));
			temp_webdw_column.font.underline = toInt(getElementProp(sColumn, "font.underline", 1, "0", iret));
			temp_webdw_column.font.strikethrough = toInt(getElementProp(sColumn, "font.strikethrough", 1, "0", iret));

			temp_webdw_column.background_mode = toInt(getElementProp(sColumn, "background.mode", 1, "", iret));
			temp_webdw_column.background_color = toInt(getElementProp(sColumn, "background.color", 1, "", iret));

			temp_webdw_column.radiobuttons.Columns = toInt(
					getElementProp(sColumn, "radiobuttons.columns", 1, "0", iret));

			temp_webdw_column.checkbox.text = getElementProp(sColumn, "checkbox.text", 1, "", iret);
			temp_webdw_column.checkbox.on = getElementProp(sColumn, "checkbox.on", 1, "", iret);
			temp_webdw_column.checkbox.off = getElementProp(sColumn, "checkbox.off", 1, "", iret);
			temp_webdw_column.checkbox.scale1 = getElementProp(sColumn, "checkbox.scale", 1, "", iret);
			temp_webdw_column.checkbox.threed = getElementProp(sColumn, "checkbox.threed", 1, "", iret);

			temp_webdw_column.combobox.allowedit = getElementProp(sColumn, "ddlb.allowedit", 1, "", iret);
			temp_webdw_column.combobox.limit = toInt(getElementProp(sColumn, "ddlb.limit", 1, "0", iret));
			temp_webdw_column.combobox.case1 = getElementProp(sColumn, "ddlb.case", 1, "", iret);
			temp_webdw_column.combobox.useasborder = getElementProp(sColumn, "ddlb.useasborder", 1, "", iret);

			temp_webdw_column.dddw.allowedit = getElementProp(sColumn, "dddw.allowedit", 1, "", iret);
			temp_webdw_column.dddw.case1 = getElementProp(sColumn, "dddw.case", 1, "", iret);
			temp_webdw_column.dddw.DataColumn = getElementProp(sColumn, "dddw.datacolumn", 1, "", iret);
			temp_webdw_column.dddw.DisplayColumn = getElementProp(sColumn, "dddw.displaycolumn", 1, "", iret);
			temp_webdw_column.dddw.limit = toInt(getElementProp(sColumn, "dddw.limit", 1, "0", iret));
			temp_webdw_column.dddw.Lines = toInt(getElementProp(sColumn, "dddw.lines", 1, "0", iret));
			temp_webdw_column.dddw.Name = getElementProp(sColumn, "dddw.name", 1, "", iret);
			temp_webdw_column.dddw.PercentWidth = toInt(getElementProp(sColumn, "dddw.percentwidth", 1, "100", iret));
			temp_webdw_column.dddw.useasborder = getElementProp(sColumn, "dddw.useasborder", 1, "", iret);
			temp_webdw_column.dddw.vscrollbar = getElementProp(sColumn, "dddw.vscrollbar", 1, "", iret);

			local_webdw.column[temp_webdw_column.id] = temp_webdw_column;
		}
		return 0;
	}

	private int readWebDW09_Line() {
		ArrayList lineArray = new ArrayList();
		Object obj;
		String sline = "";
		int id = 0;
		MyInt iret = new MyInt(0);

		lineArray = getAllElement(dwString, "line");
		id = 0;
		for (int i = 0; i < lineArray.size(); i++) {
			sline = (String) lineArray.get(i);

			id = id + 1;

			if (id > 100) {

				errString = "ERROR:readWebDW09_Line:���ɶ�ȡ100������!";
				return -1;
			}

			local_webdw.lineinfo[id].band = getElementProp(sline, "band", 1, "detail", iret);
			local_webdw.lineinfo[id].x1 = toInt(getElementProp(sline, "x1", 1, "0", iret));
			local_webdw.lineinfo[id].y1 = toInt(getElementProp(sline, "y1", 1, "0", iret));
			local_webdw.lineinfo[id].x2 = toInt(getElementProp(sline, "x2", 1, "0", iret));
			local_webdw.lineinfo[id].y2 = toInt(getElementProp(sline, "y2", 1, "0", iret));
			local_webdw.lineinfo[id].Name = getElementProp(sline, "name", 1, "", iret);
			local_webdw.lineinfo[id].pen_style = getElementProp(sline, "pen.style", 1, "", iret);
			local_webdw.lineinfo[id].pen_width = getElementProp(sline, "pen.width", 1, "", iret);
			local_webdw.lineinfo[id].pen_color = getElementProp(sline, "pen.color", 1, "", iret);
			local_webdw.lineinfo[id].background_mode = getElementProp(sline, "background.mode", 1, "", iret);
			local_webdw.lineinfo[id].background_color = getElementProp(sline, "background.color", 1, "", iret);
		}
		return 0;
	}
}
