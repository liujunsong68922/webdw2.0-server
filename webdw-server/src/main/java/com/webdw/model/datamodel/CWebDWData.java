package com.webdw.model.datamodel;

import com.webdw.common.Golbal;
import com.webdw.common.MyInt;
import com.webdw.model.datamodel.dwstore.CWebDWData_DataBuffer;

public class CWebDWData extends Golbal {
	public String _ReadMe = "WebDW DataBuffer Operator";
	public CWebDWData_DataBuffer PrimaryBuffer = new CWebDWData_DataBuffer();
	public CWebDWData_DataBuffer FilterBuffer = new CWebDWData_DataBuffer();
	public CWebDWData_DataBuffer DeleteBuffer = new CWebDWData_DataBuffer();

	public CWebDWData() {
		PrimaryBuffer.BufferType = 1; // PrimaryBuffer
		FilterBuffer.BufferType = 2; // FilterBuffer
		DeleteBuffer.BufferType = 3; // DeleteBuffer
	}

	public String errString = "";
	private String data_filter = "";
	private String data_sorter = "";


	public int AfterUpdate() {

		DeleteBuffer.Init();

		int rowid = 0;
		MyInt iret = new MyInt(0);
		for (rowid = 1; rowid <= PrimaryBuffer.RowNumber; rowid++) {
			PrimaryBuffer.SetRowState(rowid, "normal", iret);
		}
		return 0;
	}

	public int DeleteRow(int rowid) {
		String sData = "";
		int newrowid;
		int colid;
		MyInt iret = new MyInt(0);

		sData = "";

		if (rowid > 0 && rowid <= PrimaryBuffer.RowNumber) {

			sData = PrimaryBuffer.GetRowString(rowid);
			newrowid = DeleteBuffer.InsertRow(0, sData);

			for (colid = 1; colid <= PrimaryBuffer.ColNumber; colid++) {
				sData = PrimaryBuffer.GetOriginalItemString(rowid, colid, iret);
				if (iret.intvalue == -1) {
					return -1;
				}

				DeleteBuffer.SetOriginalItemString(newrowid, colid, sData, iret);

				if (iret.intvalue == -1) {
					return -1;
				}

				sData = PrimaryBuffer.GetRowState(rowid, iret);
				DeleteBuffer.SetRowState(newrowid, sData, iret);
			}

			PrimaryBuffer.DeleteRow(rowid);
		}
		return 0;
	}

	public String Eval(String command, MyInt iret) {

		String arg1 = "";// As String

		if (command.equals("")) {// Then
			iret.intvalue = 0;
			return "0";// = 0
		}

		// '��һ���֣���Sort��ع��ܵ�֧��,����GetSort,SetSort,Sort
		if (UCase(Left(command, Len("GetSort"))).equals(UCase("GetSort"))) {// Then
			iret.intvalue = 0;
			return data_sorter;
		}

		if (UCase(Left(command, Len("SetSort"))).equals(UCase("SetSort"))) {// Then
			arg1 = getOneInputArg(command, iret);
			if (iret.intvalue == -1) {
				return "";
			}
			data_sorter = arg1;
			return "";
		}
		if (UCase(Left(command, Len("Sort"))).equals(UCase("Sort"))) {// Then
			long i = 0;
			i = PrimaryBuffer.Sort(data_sorter);
			if (i == -1) {// Then
				errString = PrimaryBuffer.errString;
			}
			return "";
		}

		// UnknownCommand:
		iret.intvalue = -1;
		errString = "Unknown Command: " + command;
		return "";
	}

	public String GetAllData() {
		return PrimaryBuffer.GetAllData();
	}

	public int GetColIdByName(String colname) {
		return PrimaryBuffer.GetColIdByName(colname);
	}

	public int GetColumnNumber() {
		return GetColumnNumber(1);
	}

	// '�õ��е�����
	public int GetColumnNumber(int BufferType) {
		switch (BufferType) {
		case 1:
			return PrimaryBuffer.ColNumber;
		case 2:
			return FilterBuffer.ColNumber;
		case 3:
			return DeleteBuffer.ColNumber;
		default:
			return 0;
		}
	}

	public String GetDataFilter() {// As String
		return data_filter;
	}

	public String GetItemString(int row, int col) {
		return GetItemString(row, col, 1);
	}

	public String GetItemString(int row, int col, int BufferType) {
		MyInt iret = new MyInt(0);

		switch (BufferType) {
		case 1:
			return PrimaryBuffer.GetItemString(row, col, iret);
		case 2:
			return FilterBuffer.GetItemString(row, col, iret);
		case 3:
			return DeleteBuffer.GetItemString(row, col, iret);
		}
		return "";
	}

	private String getOneInputArg(String cmd, MyInt iret) {
		int pos1 = 0;// As Long '������
		int pos2 = 0;// As Long '������
		String stemp = "";// As String '��ʱ����

		pos1 = InStr(cmd, "(");

		// 'pos2ָ���������һ��������
		for (pos2 = Len(cmd); pos2 >= 1; pos2--) {// Step -1
			if (Mid(cmd, pos2, 1).equals(")")) {// Then
				break;
			}
		}

		if (pos1 <= 0 || pos2 <= 0 || pos1 > pos2) {// Then
			iret.intvalue = -1;
			errString = "�����������ʧ��:" + cmd;
			return "";
		} // End If

		stemp = Mid(cmd, pos1 + 1, pos2 - (pos1 + 1));
		stemp = Trim(stemp);

		if (Left(stemp, 1).equals("\"") && Right(stemp, 1).equals("\"") && Len(stemp) > 1) {// Then
			stemp = Mid(stemp, 2, Len(stemp) - 2);
		}

		// getOneInputArg = stemp
		iret.intvalue = 0;
		return stemp;

	}

	public int GetRowCount() {
		return GetRowCount(1);
	}

	public int GetRowCount(int BufferType) {
		switch (BufferType) {
		case 1:
			return PrimaryBuffer.RowNumber;
		case 2:
			return FilterBuffer.RowNumber;
		case 3:
			return DeleteBuffer.RowNumber;
		default:
			return 0;
		}
	}

	public String GetRowState(int row, MyInt iret) {
		return PrimaryBuffer.GetRowState(row, iret);
	}

	public String GetUpdateSql(String stablename, MyInt iret) {

		String sql1 = "";
		String sql2 = "";

		sql1 = PrimaryBuffer.GetAllUpdateSQL(stablename, iret);
		if (iret.intvalue == -1) {
			return "";
		}

		sql2 = DeleteBuffer.GetAllUpdateSQL(stablename, iret);
		if (iret.intvalue == -1) {
			return "";
		}

		if (!sql1.equals("")) {
			return sql1 + Chr(13) + Chr(10) + sql2;
		} else {
			return sql2;
		}
	}

	public int InitData(String sindata) {
		return InitData(sindata, "normal");
	}

	public int InitData(String sindata, String state) {
		PrimaryBuffer.InitData(sindata, state);
		String sColumn = PrimaryBuffer.GetColumnString();
		FilterBuffer.InitData(sColumn);
		DeleteBuffer.InitData(sColumn);
		return 0;
	}

	public int InsertRow(int rowid, String sData) {
		int i = 0;
		i = PrimaryBuffer.InsertRow(rowid, sData);
		if (i == -1) {
			errString = PrimaryBuffer.errString;
		}
		return i;
	}

	public int ResetData() {
		PrimaryBuffer.ResetData();
		FilterBuffer.ResetData();
		DeleteBuffer.ResetData();
		return 0;
	}

	public int SetDataFilter(String Filter) {// As Long
		data_filter = Filter;
		return 0;
	}

	public String SetItemString(int row, int col, String sData) {
		return SetItemString(row, col, sData, 1);
	}

	public String SetItemString(int row, int col, String sData, int BufferType) {
		MyInt iret = new MyInt(0);

		if (BufferType == 1) {
			return PrimaryBuffer.SetItemString(row, col, sData, iret);
		}
		if (BufferType == 2) {
			return FilterBuffer.SetItemString(row, col, sData, iret);
		}

		if (BufferType == 3) {
			return DeleteBuffer.SetItemString(row, col, sData, iret);
		}
		return "";
	}

	public String GetColumnString() {// As String
		return PrimaryBuffer.GetColumnString();
	}

}
