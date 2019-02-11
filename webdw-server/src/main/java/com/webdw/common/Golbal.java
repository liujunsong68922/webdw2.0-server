package com.webdw.common;

import com.webdw.model.CWebDW;
import com.webdw.model.dboper.Transaction_Const;
import com.webdw.model.dwsyntax.WebDWSyntax;
import com.webdw.view.ui.MyUIComponent;

import java.util.List;
import java.awt.Color;

/**
 * ȫ�ֱ�������
 * 
 * @author admin
 * 
 */
public class Golbal extends VBFunction {
	public static final String JWebDWInfo = "    Author: Liujunsong  \r\n" + "    E_Mail: liujunsong@aliyun.com  \r\n"
			+ "    http://webdw.vicp.net  \r\n" + "    Info:If You Can See SourceCode and find bug in it  \r\n"
			+ "    Please contract me.  \r\n";

	public static String G_ServerURL = "http://localhost/myproj/Table";

	public static Transaction_Const G_Transaction_Const = new Transaction_Const();

	public static boolean True = true;
	public static boolean False = false;

	public Golbal() {
		// do nothing

	}

	// '��һ��targetControls�����У����ݸ����ؼ������������ؼ�
	// '����ؼ������ڣ��򷵻�Nothing
	public static MyUIComponent GF_GetObjectByName(List targetControls, String objName) {
		MyUIComponent vobj = null;
		for (int i = 0; i < targetControls.size(); i++) {
			vobj = (MyUIComponent) targetControls.get(i);
			if (vobj == null) {
				continue;
			}
			if (vobj.getName() == null) {
				continue;
			}
			if (UCase(vobj.getName()).equals(UCase(objName))) {
				return vobj;
			}
		}
		return null;
	}

	public long GF_GetVBColor(long pbColor, long defColor) {
		long iret = 0;
		long SYSCOLOR = 16777215;// '�����ɫֵ,256 * 256 * 256 - 1

		if (pbColor <= SYSCOLOR) {
			return pbColor;
		}

		iret = defColor;// '����Ĭ����ɫ
		if (pbColor == 1090519039)
			iret = RGB(255, 255, 255); // '�����windowsĬ�ϱ���ɫ
		if (pbColor == 276856960)
			iret = RGB(125, 125, 125);// ' �����Ӧ�ù�����Ĭ�ϱ���ɫ
		if (pbColor == 81324524)
			iret = RGB(125, 125, 125); // ' ����ǰ�ť��ɫ
		if (pbColor == 33554592)
			iret = RGB(0, 0, 0); // 'window�ı�Ĭ����ɫ

		// '�����Ǽ��̶ֹ��������ɫת��
		if (pbColor == 536870912)
			iret = RGB(255, 255, 255); // '��ɫ

		return iret;

	}

	/**
	 * ����ȫ�ֵ�ת��������Ĭ��Ϊ0.25
	 * ���ǵ�������ʾ����ֵ��ԭʼ����֮���ת����ϵ��Ӧ����ǰ��չʾ����Ƹ�����Ȼ
	 * ����ں�˽����к�ת��������صĹ���ȥ��
	 * @param targetControls
	 * @return
	 */
	private double _GF_GetConvertRate(List targetControls) {
		double convertRate = 0.25;
		return convertRate;
	}


	public long GF_GetDBlength(String sdata) {
		int i = 0;
		long ilen = 0;
		String stemp = "";
		ilen = 0;
		for (i = 1; i <= Len(sdata); i++) {
			stemp = Mid(sdata, i, 1);
			if (Asc(stemp) < 128 && Asc(stemp) > 0) {
				ilen = ilen + 1;
			} else {
				ilen = ilen + 2;
			}

		}
		return ilen;
	}

	public String GF_IF(boolean ifClause, String YesValue, String NoValue) {
		if (ifClause) {
			return YesValue;
		} else {
			return NoValue;
		}
	}

	public int GF_IF_Long(boolean ifClause, int YesValue, int NoValue) {
		if (ifClause) {
			return YesValue;
		} else {
			return NoValue;
		}
	}

	/**
	 * ��VB����ɫת����Java����ɫ
	 * 
	 * @param vbcolor
	 * @return
	 */
	public Color GF_GetJavaColor(int vbcolor) {
		if (vbcolor == 255) {
			return Color.RED;
		}
		return new Color(0, 0, 0);
	}

	public void log(String s) {
		System.out.println(s);
	}
}


