package com.webdw.view.ui;

import java.awt.Rectangle;

/**
 * ����UIԪ�صĳ��࣬�������������UI����
 * 
 * @author Administrator
 *
 */
public abstract class MyUIComponent {
	// ���캯��
	public MyUIComponent() {

	}

	// ���캯��
	public MyUIComponent(String _svalue) {
		Text = _svalue;
	}

	// ----------���н���Ԫ�صĹ������Զ���--------------//
	// �������Ԫ�����Զ��壺classname,id,name,text,tag
	//ÿ����ʵ�������඼���Լ��������ƣ��ڳ������ж���
	public String classname = "";
	//id������������DOMԪ�ص�idֵ������ͨ��document.getElementById()����ȡDOM����
	public String Id = "";
	//name������������DOMԪ�ص�nameֵ
	public String Name = "";
	//text������������DOMԪ�ص�text���ԣ������������������ԣ��������ݿ��л�ȡ��ԭʼֵ
	public String Text = "";
	//tag�����ǩ���ԣ��������ݴ��ڶ���
	public String Tag = "";

	public int rowid = 0;
	public int colid = 0;
	
	
	
	public int getColid() {
		return colid;
	}

	public void setColid(int colid) {
		this.colid = colid;
	}

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	// �������Ԫ��λ�ö��壺left,top,width,height,�������أ�px������ʾ
	public int Left;
	public int Top;
	public int Width;
	public int Height;

	// setBounds�ķ���
	public void setBounds(int xvalue, int topvalue, int width2, int height2) {
		Left = xvalue;
		Top = topvalue;
		Width = width2;
		Height = height2;

	}

	public void setBounds(Rectangle rect) {
		this.setBounds(rect.x, rect.y, rect.width, rect.height);

	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public void Text(String text) {
		Text = text;
	}

	public int getLeft() {
		return Left;
	}

	public void setLeft(int left) {
		Left = left;
	}

	public void Left(int left) {
		Left = left;
	}

	public int getTop() {
		return Top;
	}

	public void setTop(int top) {
		Top = top;
	}

	public void Top(int top) {
		Top = top;
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public void Width(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public void Height(int height) {
		Height = height;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}
	
	public void Tag(String tag) {
		Tag = tag;
	}
}
