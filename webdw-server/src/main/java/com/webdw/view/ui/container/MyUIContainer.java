package com.webdw.view.ui.container;

import java.util.ArrayList;

import com.webdw.view.ui.MyUIComponent;

//UI�����������࣬���������°���������UIԪ��
//MyUIContains����ӳ��Ϊ������ϵ�һ��DIV����
public abstract class MyUIContainer extends MyUIComponent {
	
	//�����¼��ڵ���б�洢����
	private ArrayList<MyUIComponent> childElements = new ArrayList<MyUIComponent>();

	// ���캯��
	public MyUIContainer() {
	}

	// ���캯��������һ������Name���ԵĹ������
	public MyUIContainer(String s1) {
		super(s1);
	}

	public ArrayList getChildElements() {
		return childElements;
	}

	public void setChildElements(ArrayList childElements) {
		this.childElements = childElements;
	}

	//�����¼��ڵ�
	public void add(MyUIComponent child) {
		this.childElements.add(child);
	}
	
	//ɾ���¼��ڵ�
	public void remove(MyUIComponent child) {
		if (child == null) {
			return;
		}

		this.childElements.remove(child);

	}
}
