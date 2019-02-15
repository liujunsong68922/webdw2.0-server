package com.webdw.model;

import com.webdw.model.datamodel.CWebDWData;
import com.webdw.model.syntaxmodel.CWebDW;
import com.webdw.model.viewmodel.CWebDWViewModel;

/**
 * 重新设计的WebDWModel，这个Model将语法模型，数据存储模型，视图模型三个模型组合在一起 是整个WebDW整体类设计的整体模型类
 * 原则上这个模型类是用来存储数据的，原则上不提供数据操作及修改功能。
 * 
 * @author Administrator
 *
 */
public class WebDWModel {
	// 语法模型
	public CWebDW webdw = new CWebDW();
	// 数据模型
	public CWebDWData webdwData = new CWebDWData();
	// 视图模型
	public CWebDWViewModel webdwviewmodel = null;

	// 生成视图模型对象
	// 这是WebDWModel对象对外提供的唯一可调用方法
	public void GenerateViewModel() throws Exception {
		
		//初始化视图模型对象
		this.webdwviewmodel = new CWebDWViewModel(
				webdw.webdw_creator.local_webdw, webdwData);
		
		//根据输入参数，生成视图对象模型
		webdwviewmodel.DrawDW();
	}

}
