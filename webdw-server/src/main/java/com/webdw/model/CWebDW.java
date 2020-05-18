package com.webdw.model;

import com.webdw.common.Golbal;
import com.webdw.common.exception.WebDWException;
import com.webdw.model.syntaxmodel.dwsyntax.WebDWSyntax;

/**
 * CWebDW 是WebDW的操作包装类,主要的操作封装在CWebDW_Create这个类里头
 * @author Administrator
 */
public class CWebDW extends Golbal {
//	private String _ReadMe = ("CWebDW.");
//
//	public String dwString = "";
//	public String errString = "";
//	public CWebDW_Create webdw_creator = new CWebDW_Create();
//
//	/**
//	 * The Only public function privided by CWebDW
//	 * @param dwname
//	 * @return
//	 * @throws WebDWException
//	 */
//	public int CreateByDwName(String dwname) throws WebDWException {
//		int iret = webdw_creator.CreateByDwname(dwname);
// 		this.dwString = webdw_creator.dwString;
//		this.errString = webdw_creator.errString;
//
//		return iret;
//	}
//
//	public String GetColumnDefineString() {
//		WebDWSyntax local_webdw = this.webdw_creator.local_webdw;
//		
//		String strcol = "";// As String
//		int colid = 0;// As Long
//		strcol = "";
//		for (colid = 1; colid <= 100; colid++) { 
//			if (local_webdw.table.Columns[colid].Name.equals("")) {
//				break;
//			}
//			if (strcol.equals("")) {
//				strcol = strcol + local_webdw.table.Columns[colid].Name;
//			} else {
//				strcol = strcol + Chr(9) + local_webdw.table.Columns[colid].Name;
//			}
//		}
//		return strcol;
//	}
//
//	public int GetColumnIdByColumnName(String colname) {
//		WebDWSyntax local_webdw = this.webdw_creator.local_webdw;
//		
//		int colid = 0;
//		for (colid = 1; colid <= 100; colid++) {
//			if (UCase(colname).equals(UCase(local_webdw.table.Columns[colid].Name))) {
//				return colid;
//			}
//		}
//		return -1;
//	}
//
//	public long getMaxWidth() {
//		WebDWSyntax local_webdw = this.webdw_creator.local_webdw;
//		
//		int i = 0;// As Long
//		long imax = 0;// As Long
//		imax = 0;
//		// check text elements
//		for (i = 1; i <= 100; i++) {
//			if (local_webdw.text[i].Name.equals("")) {
//				break;
//			}
//			if (local_webdw.text[i].x + local_webdw.text[i].width > imax) {
//				imax = local_webdw.text[i].x + local_webdw.text[i].width;
//			}
//		}
//		// check column elements
//		for (i = 1; i <= 100; i++) {
//			if (local_webdw.column[i].Name.equals("")) {
//				break;
//			}
//			if (local_webdw.column[i].x + local_webdw.column[i].width > imax) {
//				imax = local_webdw.column[i].x + local_webdw.column[i].width;
//			}
//		}
//		// check line elements
//		for (i = 1; i <= 100; i++) {
//			if (local_webdw.lineinfo[i].Name.equals("")) {
//				break;
//			}
//			if (local_webdw.lineinfo[i].x1 > imax) {
//				imax = local_webdw.lineinfo[i].x1;
//			}
//			if (local_webdw.lineinfo[i].x2 > imax) {
//				imax = local_webdw.lineinfo[i].x2;
//			}
//		}
//		return imax;
//	}
//	
//	public int CreateBySQL(String strsql,String stype) throws WebDWException {
//		int iret = webdw_creator.CreateBySQL(strsql,stype);
// 		this.dwString = webdw_creator.dwString;
//		this.errString = webdw_creator.errString;
//
//		return iret;
//	}

}
