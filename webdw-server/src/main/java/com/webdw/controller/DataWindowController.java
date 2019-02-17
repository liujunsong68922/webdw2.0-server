package com.webdw.controller;

import java.util.*;

import com.webdw.common.Golbal;
import com.webdw.common.MyInt;
import com.webdw.common.exception.WebDWAuthorizedException;
import com.webdw.common.exception.WebDWException;
import com.webdw.common.util.SQLStringReplaceUtil;
import com.webdw.model.WebDWModel;
import com.webdw.model.datamodel.CWebDWData;
import com.webdw.model.dboper.DBModifyOper;
import com.webdw.model.dboper.DBSelectOper;
import com.webdw.model.dboper.DWConfig;
import com.webdw.model.syntaxmodel.CWebDW;
import com.webdw.model.syntaxmodel.dwsyntax.WebDWSyntax;
import com.webdw.view.DataWindowViewModel;
import com.webdw.view.ui.MyUIComponent;
import com.webdw.view.ui.container.MyJPanel;

import hello.webdw.controller.WebDWControllerRet;
import hello.webdw.dbutil.WebDWDBUtil;

public class DataWindowController extends Golbal {
	private String _ReadMe = "Datawindow Controller,simulate PB Datawindow Controller";

	public String dwname = "";
	public String uuid = "";
	public String errString = "";//

	// 使用新定义的数据模型对象
	public WebDWModel model = new WebDWModel();

	private MyInt iret = new MyInt(0);//

	// 返回对象
	public WebDWControllerRet retObject = new WebDWControllerRet();

	public DataWindowController() {
	}

	private String _DW_GetSQLPreview(MyInt iret) {
		WebDWSyntax local_webdw = this.model.webdw.webdw_creator.local_webdw;

		String stable = "";//

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

		return model.webdwData.GetUpdateSql(stable, iret);

	}

	public int DW_Retrieve(String username,String args) throws WebDWAuthorizedException  {
		//add Retrieve privileges check
		System.out.println("dwname:"+this.dwname);
		boolean isAuthorized = false;
		isAuthorized = new WebDWDBUtil().checkUserDwOper(username, this.dwname, "Retrieve");
		
		System.out.println("Authorized:"+isAuthorized);
		if(! isAuthorized) {
			throw new WebDWAuthorizedException("Unauthorized call."+this.dwname+".Retrieve");
		}
		
		String strsql = "";// As String
		String sdata = "";// As String
		String argArray[] = new String[1];// As Variant
		String arg = "";// As Variant
		String sarg = "";// As String
		int argid = 0;// As Long
		// int iret=0;// As Long

		// 获取Select SQL定义
		// 从数据窗口配置表中进行读取
		DWConfig config = new DWConfig();
		strsql = config.getDWSelectSQLByDWName(dwname);
		String strargs = config.getDWSelectArgsByDWName(dwname);
		String selectargs[] = strargs.split(",");

		// 查询参数替换及SQL检查
		SQLStringReplaceUtil strUtil = new SQLStringReplaceUtil();
		// 参数替换
		strsql = strUtil.Replace(strsql, selectargs, args);

		try {
			// 执行Select SQL,返回结果
			DBSelectOper dboper = new DBSelectOper();
			sdata = dboper.executeSelect(strsql);

			_SetData(sdata, "normal");
			model.GenerateViewModel();
			this.retObject.status = 200;
			this.retObject.message = "DW.Retrieve OK.";
		} catch (WebDWException e) {
			this.retObject.status = 500;
			this.retObject.message = "DW.Retrieve Error:" + e.getErrString();
		}

		// generate output object
		this.generateReturnObject();
		return 0;
	}

	private int _SetDataObject(String sdwName) throws WebDWException {
		this.dwname = sdwName;

		int iret = 0;
		String columnString = "";// As String
		iret = this.model.webdw.CreateByDwName(sdwName);

		// check whether datawindow create success.
		if (iret == -1) {
			errString = this.model.webdw.errString;
			throw new WebDWException("error when create datawindow");
		}

		columnString = this.model.webdw.GetColumnDefineString();
		iret = model.webdwData.InitData(columnString);

		if (iret == -1) {
			errString = model.webdwData.errString;
			throw new WebDWException("error when init WebDWData object");
		}

		int maxwidth = 0;// As Long
		maxwidth = (int) (this.model.webdw.getMaxWidth());

		// 'step 5
		System.out.println("begin drawDW");
		model.GenerateViewModel();//

		// step6 generate output object
		this.generateReturnObject();

		return 0;

	}

	public int DW_SetDataObjectByName(String username, String sdwName) throws  WebDWAuthorizedException {
		boolean isAuthorized = false;
		isAuthorized = new WebDWDBUtil().checkUserDwOper(username, sdwName, "SetDataObject");
		
		if(! isAuthorized) {
			throw new WebDWAuthorizedException("Unauthorized call. "+sdwName+"."+"SetDataObject");
		}
		
		this.dwname = sdwName;
		this.uuid = UUID.randomUUID().toString();
		try {
			this.retObject.status = 200;
			this.retObject.message = "DW.SetDataObject OK.";
			return _SetDataObject(sdwName);
		} catch (WebDWException e) {
			this.retObject.status = 500;
			this.retObject.message = "DW.SetDataObject Error:" + e.getErrString();
			return 0;
		}
	}

	private int _SetData(String indata, String datastate) {
		int iret = 0;// As Long

		if (datastate == null || datastate == "") {
			errString = "datastate argument error.";
			return -1;
		}

		iret = model.webdwData.InitData(indata, datastate);

		if (iret == -1) {
			errString = model.webdwData.errString;
			return -1;
		}

		return 0;
	}

	public int DW_InsertRow(String username,int rowid) throws Exception {
		//step1.check authorized
		boolean isAuthorized = false;
		isAuthorized = new WebDWDBUtil().checkUserDwOper(username, this.dwname, "InsertRow");
		
		if(! isAuthorized) {
			throw new WebDWException("Unauthorized call. "+this.dwname+"."+"InsertRow");
		}
		
		String emptystring = "";// As String
		int colid = 0;// As Long
		int colNum = 0;// As Long
		colNum = model.webdwData.GetColumnNumber();
		emptystring = "";
		for (colid = 1; colid <= colNum; colid++) {
			if (emptystring.equals("")) {
				emptystring = " ";
			} else {
				emptystring = emptystring + Chr(9) + "";
			}
		}

		int iret = 0;// As Long
		iret = model.webdwData.InsertRow(rowid, emptystring);

		if (iret == -1) {
			errString = model.webdwData.errString;
		} else {
		}
		model.GenerateViewModel();

		this.retObject.status = 200;
		this.retObject.message = "DW.Insert OK.";
		this.generateReturnObject();
		return iret;
	}

	public int DW_DeleteRow(String username,int rowid) throws WebDWAuthorizedException {
		boolean isAuthorized = false;
		isAuthorized = new WebDWDBUtil().checkUserDwOper(username, this.dwname, "DeleteRow");
		
		if(! isAuthorized) {
			throw new WebDWAuthorizedException("Unauthorized call. "+this.dwname+"."+"DeleteRow");
		}
		
		if (rowid <= 0) {
			return 0;
		}
		int iret = 0;// As Long
		iret = model.webdwData.DeleteRow(rowid);

		if (iret == -1) {
			// DW_DeleteRow = -1
			errString = model.webdwData.errString;
			return -1;
		}

		this.DW_Update(username);

		// 生成返回对象
		this.retObject.status = 200;
		this.retObject.message = "DW.Delete OK.";
		this.generateReturnObject();

		return 0;
	}

	public int DW_SetItem(int rowid, int colid, String sdata) throws Exception {

		int iret = 0;// As Long
		iret = toInt(model.webdwData.SetItemString(rowid, colid, sdata));
		if (iret == -1) {
			errString = model.webdwData.errString;
		}
		this.model.GenerateViewModel();
		return iret;
	}

	public int DW_Update(String username) throws WebDWAuthorizedException {

		//step1.check authorized
		boolean isAuthorized = false;
		isAuthorized = new WebDWDBUtil().checkUserDwOper(username, this.dwname, "Update");
		
		if(! isAuthorized) {
			throw new WebDWAuthorizedException("Unauthorized call. "+this.dwname+"."+"Update");
		}		
		
		String strsql = "";// As String
		strsql = this._DW_GetSQLPreview(iret);
		System.out.println("strsql:" + strsql);

		if (iret.intvalue == -1) {
			return -1;
		}

		String cmds[] = new String[1];// ) As String
		cmds = Split(strsql, "" + Chr(13) + Chr(10));

		System.out.println("length:" + cmds.length);

		try {
			DBModifyOper dboper = new DBModifyOper();
			for (String sql : cmds) {
				dboper.executeModify(sql);
			}

			this.model.webdwData.AfterUpdate();
			this.model.GenerateViewModel();
			this.retObject.status = 200;
			this.retObject.message = "DW.Update OK.";
			this.generateReturnObject();
			return 0;
		} catch (WebDWException e) {
			this.retObject.status = 500;
			this.retObject.message = "DW.Update Error:" + e.getErrString();
			this.generateReturnObject();
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * 生成返回的视图模型对象
	 * 
	 * @return
	 */
	private void generateReturnObject() {
		this.retObject.uuid = this.uuid;
		this.retObject.uiobjList = this.model.webdwviewmodel.targetControls;
	}
}
