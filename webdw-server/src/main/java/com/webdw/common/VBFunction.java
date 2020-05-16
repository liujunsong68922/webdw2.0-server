package com.webdw.common;

import java.util.*;

/**
 * ����ඨ�������VB������Javaʵ�֣������ظ���д����
 * 
 * @author admin
 * 
 */
public class VBFunction {
	public void ReadMe() {
		System.out.println("����ඨ�������VB������Javaʵ�֣������ظ���д����");
		System.out.println(Golbal.JWebDWInfo);
	}

	/**
	 * �ṩ��VB����InStr��ͬ�Ĺ���,VB���ַ�������,�Ǵ�1��ʼ��,���Խ�����ֵ��1����
	 * ����0�����Ҳ���
	 * @param beginPos,��Чλ�ô�1��ʼ
	 * @param string1
	 * @param findString
	 * @return
	 */
	public static int InStr(int beginPos, String string1, String findString) {
		int ipos;
		if (string1 == null || string1.equals("")) {
			return -1;
		}
		if (beginPos<1){
			beginPos=1;
		}
		ipos = string1.indexOf(findString, beginPos - 1);
		return ipos + 1;
	}

	public static int InStr(String string1, String findString) {
		return InStr(0, string1, findString);
	}

	/**
	 * �ṩ��VB����Len��ͬ�Ĺ���
	 * 
	 * @param inStr
	 * @return
	 */
	public static int Len(String inStr) {
		if (inStr == null) {
			return 0;
		}
		return inStr.length();
	}

	/**
	 * �ṩ��VB����Mid��ͬ�Ĺ���,VB���ַ�������,�±��1��ʼ.
	 * 
	 * @param str1
	 * @param pos
	 * @param length
	 * @return
	 */
	public static String Mid(String str1, int pos, int length) {
		if (length <= 0) {
			return "";
		}
		return str1.substring(pos - 1, pos + length - 1);
	}

	public static String Mid(String str1, int pos) {
		return str1.substring(pos - 1);
	}

	/**
	 * VB's Left String Function
	 * 
	 * @param instr
	 * @param i
	 * @return
	 */
	public String Left(String instr, int i) {
		if (instr.length() >= i) {
			return instr.substring(0, i);
		} else {
			return instr;
		}
	}

	/**
	 * VB's Right String Function
	 * 
	 * @param instr
	 * @param i
	 * @return
	 */
	public String Right(String instr, int i) {
		return instr.substring(instr.length() - i);
	}

	// VB's Char Function
	public String Chr(int i) {
		if (i == 9)
			return "\t";
		if (i == 13)
			return "\r";
		if (i == 10)
			return "\n";
		return "";
	}

	/**
	 * ���ַ���ת��������
	 * 
	 * @param s
	 * @return
	 */
	public int toInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println("ERROR Data ToInt:*"+s+"*");
			return 0;
		}
	}

	/**
	 * ���ַ���ת������ֵ
	 * 
	 * @param s
	 * @return
	 */
	public double toDouble(String s) {
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println("ERROR Data ToInt:*"+s+"*");
			return 0;
		}
	}

	public static String UCase(String inStr) {
		return inStr.toUpperCase();
	}

	public String[] Split(String inStr1, String sepStr) {
		// return inStr.split(sepStr);
		int arraynum = 0;
		String data[] = new String[100000];
		int i = 0;
		int tempi = 0;
		tempi = InStr(i, inStr1, sepStr);
		while (tempi > 0) {
			if (i == 0) {
				// ��һ��Ԫ��,��1��ʼ����
				data[arraynum] = Mid(inStr1, 1, tempi - i - 1);
			} else {
				data[arraynum] = Mid(inStr1, i + sepStr.length(), tempi - i
						- sepStr.length());
			}
			arraynum++;
			i = tempi;
			tempi = InStr(i+1, inStr1, sepStr);
		}
		if (i == 0) {
			data[arraynum] = inStr1;
		} else {
			data[arraynum] = Mid(inStr1, i + sepStr.length());
		}
		arraynum++;
		String newdata[] = new String[arraynum];
		for (i = 0; i < arraynum; i++) {
			newdata[i] = data[i];
		}
		return newdata;
	}

	/**
	 * �õ����������±�
	 * 
	 * @param instr
	 * @return
	 */
	public int UBound(String[] instr) {
		return instr.length - 1;
	}

	/**
	 * ȥ���ַ�������Ŀո�
	 * 
	 * @param inStr
	 * @return
	 */
	public String Trim(String inStr) {
		return inStr.trim();
	}

	/**
	 * �滻�ַ����е�����
	 * 
	 * @param instr
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String Replace(String instr, String str1, String str2) {
		return instr.replaceAll(str1, str2);
	}

	/**
	 * �õ���ǰʱ��ı�ʾ
	 * 
	 * @return
	 */
	public String Now() {
		return new java.util.Date().toString();
	}

	public long RGB(int r, int g, int b) {
		return r * 256 * 256 + g * 256 + b;
	}

	public int Asc(String s) {
		if (s == null) {
			s = " ";
		}
		if (s.length() == 0) {
			s = " ";
		}
		return s.charAt(0);
	}

	/**
	 * �ж�S1 > S2
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean IsGreat(String s1, String s2) {
		if (s1.length() > 0 && s2.length() > 0) {
			if (s1.charAt(0) > s2.charAt(0)) {
				return true;
			}
			if (s1.charAt(0) < s2.charAt(0)) {
				return false;
			}
			s1 = s1.substring(1);
			s2 = s2.substring(1);
			return IsGreat(s1, s2);
		}

		if (s1.length() > 0 && s2.length() == 0) {
			return true;
		}

		if (s1.length() == 0 && s2.length() > 0) {
			return false;
		}
		return true;
	}

	public boolean IsNumeric(String s1) {
		try {
			double d = Double.parseDouble(s1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public double Rnd(double d) {
		Random r = new Random();
		return r.nextDouble();
	}
	
	//����һ������VB��Windows��׼�Ի���

	public static void MessageBox(String title,String info){
		//f1 = new JFrame();
		//d1 = new MessageJDialog(title,info,f1);
	}
	
	public static void MessageBox(String sinfo){
		MessageBox("JWebDW0.3",sinfo);
	}

	public static void MsgBox(String stext,int iButton,String title){
		MessageBox(title,stext);
	}
}
