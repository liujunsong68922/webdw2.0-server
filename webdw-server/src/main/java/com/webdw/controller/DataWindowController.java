package com.webdw.controller;

import java.util.*;

import com.webdw.common.Golbal;
import com.webdw.common.MyInt;
import com.webdw.model.CWebDW;
import com.webdw.model.CWebDWData;
import com.webdw.model.dboper.CWebDWTransaction;
import com.webdw.model.dboper.DBSelectOper;
import com.webdw.model.dboper.DWConfig;
import com.webdw.model.dwsyntax.WebDWSyntax;
import com.webdw.view.DataWindowViewModel;
import com.webdw.view.ui.MyUIComponent;
import com.webdw.view.ui.container.MyJPanel;

public class DataWindowController extends Golbal {
	private String _ReadMe = "Datawindow Controller,simulate PB Datawindow Controller";

	private String dwname = "";
	public String uuid = "";
	public String errString = "";//

	public MyUIComponent myControls[] = new MyUIComponent[10001];// 'myControls����������Զ������Ŀؼ��ļ���

	// ------------------------objcet define--------------
	// This is the model part of DataWindowController
	public CWebDW webdw = new CWebDW();//
	public CWebDWData webdwData = new CWebDWData();//
	public CWebDWTransaction sqlca = new CWebDWTransaction();//

	// This is the view part of DataWindowController
	public ArrayList targetControls = null;//
	public MyJPanel targetPict = null;
	public int currentRow = 0;//
	private MyInt iret = new MyInt(0);//

	public DataWindowController() {
	}

	public int DrawDW() throws Exception {
		DataWindowViewModel vm = new DataWindowViewModel(this);
		return vm.DrawDW();
	}

	public String DW_GetSQLPreview(MyInt iret) {
		WebDWSyntax local_webdw = this.webdw.webdw_creator.local_webdw;
		
		// step1 data check
		if (targetControls == null || targetPict == null) {
			iret.intvalue = -1;
			errString = "Please Call SetDataObject First.";
			return "";
		}

		String stable = "";// As String '���ݱ�����

		if (!local_webdw.table.retrieve.pbselect.table[2].equals("")) {// 'Ŀǰ��֧�ֵ�������Ƕ���˳�
			iret.intvalue = 0;
			return "";
		}

		if (local_webdw.table.retrieve.pbselect.table[1].equals("")) {// '�����һ������Ϊ�գ��˳�
			iret.intvalue = -1;
			// DW_GetSQLPreview = ""
			errString = "ERROR: no table define";
			return "";
		}
		stable = local_webdw.table.retrieve.pbselect.table[1];
		stable = Replace(stable, "~" + "\"", ""); //

		return webdwData.GetUpdateSql(stable, iret);

	}

	private String _DW_GetSQLSelect(String dwname) {
		String strsql = "";// As String

		DWConfig config = new DWConfig();
		strsql = config.getDWSelectSQLByDWName(dwname);

		return strsql;
	}

	public int DW_Retrieve(String args) throws Exception {
		if (targetControls == null || targetPict == null) {
			// DW_Retrieve = -1
			errString = "Please Call SetDataObject First.";
			return -1;
		}

		String strsql = "";// As String
		String sdata = "";// As String
		String argArray[] = new String[1];// As Variant
		String arg = "";// As Variant
		String sarg = "";// As String
		int argid = 0;// As Long
		// int iret=0;// As Long

		// 获取Select SQL定义(暂时未带参数)
		// 从数据窗口配置表中进行读取
		DWConfig config = new DWConfig();
		strsql = config.getDWSelectSQLByDWName(dwname);

		// TODO:查询参数替换及SQL检查

		// 执行Select SQL,返回结果
		DBSelectOper dboper = new DBSelectOper();
		sdata = dboper.executeSelect(strsql);

		if (iret.intvalue == -1) {
			// DW_Retrieve = -1
			errString = sqlca.errString;
			return -1;
		}
		// '���������ݣ���ʼ�����ݴ���
		_SetData(sdata, "normal");
		int i = DrawDW();
		return i;
	}

	public int DW_RowCount() {
		if (targetControls == null || targetPict == null) {
			errString = "Please Call SetDataObject First.";
			return -1;
		}
		return webdwData.GetRowCount();
	}

	private int _SetDataObject(List targetControlsArg, MyJPanel targetPictArg, String sdwName) {
		try {

			if (targetControlsArg == null) {
				errString = "Cannot set targetControls";
				throw new Exception("error");
			}

			this.targetControls = (ArrayList) targetControlsArg;
			this.targetPict = targetPictArg;
			this.dwname = sdwName;

			int iret = 0;
			String columnString = "";// As String
			iret = webdw.CreateByDwName(sdwName);

			// check whether datawindow create success.
			if (iret == -1) {
				errString = webdw.errString;
				throw new Exception("error when create datawindow");
			}

			columnString = webdw.GetColumnDefineString();
			iret = webdwData.InitData(columnString);

			if (iret == -1) {
				errString = webdwData.errString;
				throw new Exception("error when init WebDWData object");
			}

			int maxwidth = 0;// As Long
			maxwidth = (int) (webdw.getMaxWidth());

			// 'step 5
			System.out.println("begin drawDW");
			DrawDW();//

			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			this.targetControls = null;
			return -1;
		}

	}

	public int DW_SetDataObjectByName(ArrayList targetControlsArg, MyJPanel targetPictArg, String sdwName) {
		this.dwname = sdwName;
		this.uuid = UUID.randomUUID().toString();
		
		return _SetDataObject(targetControlsArg, targetPictArg, sdwName);
	}

	private int _SetData(String indata, String datastate) {
		int iret = 0;// As Long

		// �жϴ����������Ч��
		if (datastate == null || datastate == "") {
			errString = "datastate argument error.";
			return -1;
		}

		iret = webdwData.InitData(indata, datastate);

		if (iret == -1) {
			errString = webdwData.errString;
			return -1;
		}

		return 0;
	}

	// '�����ݴ����в���һ����¼������������¼�ĵ�ǰ�кţ������������-1
	// 'rowid����Ҫ������кţ����Ϊ0������������
	public int DW_InsertRow(int rowid) {
		// 'step1 �ж��Ƿ��ʼ��
		if (targetControls == null || targetPict == null) {
			// DW_InsertRow = -1
			errString = "Please Call SetDataObject First.";
			return -1;
		}

		String emptystring = "";// As String
		int colid = 0;// As Long
		int colNum = 0;// As Long
		colNum = webdwData.GetColumnNumber();
		emptystring = "";
		for (colid = 1; colid <= colNum; colid++) {
			if (emptystring.equals("")) {
				emptystring = " ";
			} else {
				emptystring = emptystring + Chr(9) + "";
			}
		}

		int iret = 0;// As Long
		iret = webdwData.InsertRow(rowid, emptystring);

		if (iret == -1) {
			errString = webdwData.errString;
		} else {
		}

		return iret;
	}

	public int DW_DeleteRow(int rowid) throws Exception {
		// '�߼��жϣ�����ǰ�ȳ�ʼ�����ݴ��ڿؼ�
		if (targetControls == null || targetPict == null) {
			// DW_DeleteRow = -1
			errString = "Please Call SetDataObject First.";
			return -1;
		}

		if (rowid <= 0) {
			return 0;
		}
		int iret = 0;// As Long
		iret = webdwData.DeleteRow(rowid);

		if (iret == -1) {
			// DW_DeleteRow = -1
			errString = webdwData.errString;
			return -1;
		}

		DrawDW();

		return 0;
	}

	public int DW_SetRow(int rowid) {
		if (rowid > 0 && rowid <= DW_RowCount()) {
			currentRow = rowid;
			return 1;// '����1����ɹ�
		} else {
			return -1;// '����-1����ʧ��
		}
	}

	public int DW_SetItem(int rowid, int colid, String sdata) {

		int iret = 0;// As Long
		iret = toInt(webdwData.SetItemString(rowid, colid, sdata));
		if (iret == -1) {
			errString = webdwData.errString;
		}

		return iret;
	}

	public int DW_Update() {
		// 'step1
		if (targetControls == null || targetPict == null) {
			// DW_Update = -1
			errString = "Please Call SetDataObject First.";
			return -1;
		}

		String strsql = "";// As String
		String sdata = "";// As String

		strsql = DW_GetSQLPreview(iret);
		System.out.println(strsql);

		if (iret.intvalue == -1) {
			return -1;
		}

		String cmds[] = new String[1];// ) As String
		int cmdid = 0;// As Long
		String transid = "";// As String

		transid = sqlca.Eval("GetTransid", iret);
		cmds = Split(strsql, "" + Chr(13) + Chr(10));

		boolean autoCommit = true;
		if (autoCommit) {
			if (transid.length() > 0) {// '����Ѿ����������ύ֮
				sqlca.Commit(iret);
			}

			sqlca.BeginTransaction(iret);// '��������
			if (iret.intvalue == -1) {
				// DW_Update = -1
				errString = sqlca.errString;
				return -1;
			}

			for (cmdid = 0; cmdid <= UBound(cmds); cmdid++) {
				sqlca.Eval("Setcommand(" + cmds[cmdid] + ")", iret);// '�������
				sqlca.AddCommand(iret);
			}

			sqlca.Commit(iret);// '�ύ����
			if (iret.intvalue == -1) {
				// DW_Update = -1
				errString = sqlca.errString;
				return -1;
			}
			webdwData.AfterUpdate();

		} else {// '�ֹ�����״̬������transId>""������ܾ�����
			if (transid.equals("")) {// Then
				// DW_Update = -1
				errString = "Please Call BeginTransaction First";
				return -1;
			}

			for (cmdid = 0; cmdid <= UBound(cmds); cmdid++) {
				sqlca.Eval("Setcommand(" + cmds[cmdid] + ")", iret);// '�������
				sqlca.AddCommand(iret);
			}
		}
		return 0;
	}
}
