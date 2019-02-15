package com.webdw.model.syntaxmodel.dwsyntax;

//'�����ж�ѡ����֧�ֶ��壬����Column��һ������
public class WebDW_Column_CheckBox {
	public String text = ""; // '��ʾ�ڽ����ϵ��ַ���

	public String on = ""; // 'ѡ��ʱ������ֵ

	public String off = ""; // 'δѡ��ʱ������ֵ

	public String scale1 = ""; // '���岻��,scale��vb�����֣�����

	public String threed = ""; // '3D��ʾ���

	public WebDW_Column_CheckBox Clone() {

		WebDW_Column_CheckBox newOne = new WebDW_Column_CheckBox();

		newOne.text = text; // '��ʾ�ڽ����ϵ��ַ���

		newOne.on = on; // 'ѡ��ʱ������ֵ

		newOne.off = off; // 'δѡ��ʱ������ֵ

		newOne.scale1 = scale1; // '���岻��,scale��vb�����֣�����

		newOne.threed = threed; // '3D��ʾ���
		return newOne;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOn() {
		return on;
	}

	public void setOn(String on) {
		this.on = on;
	}

	public String getOff() {
		return off;
	}

	public void setOff(String off) {
		this.off = off;
	}

	public String getScale1() {
		return scale1;
	}

	public void setScale1(String scale1) {
		this.scale1 = scale1;
	}

	public String getThreed() {
		return threed;
	}

	public void setThreed(String threed) {
		this.threed = threed;
	}

}

