package com.webdw.model.syntaxmodel;

import com.webdw.common.Golbal;
import com.webdw.common.MyInt;
import com.webdw.model.syntaxmodel.dwsyntax.WebDWSyntax;

/**
 * 这个类用来封装利用SQL查询语句来生成一个标准字符串的方法
 * 
 * @author liujunsong
 *
 */
public class CWebDW_SyntaxFromSQL extends Golbal {
	/**
	 * 这个字符串描述数据列的定义，这个字符串由调用者传入 这个字符串格式为第一行列定义，其他列：列定义数据
	 */
	public String colDefString = "";

	// 下面是本地变量定义
	public String errString = "";
	private String columnlist;
	private String tablelist;
	private String joinlist;
	private String wherelist;

	int color_white = 16777215;

	private WebDWSyntax local_webdw = new WebDWSyntax();

	
	public WebDWSyntax getLocal_webdw() {
		return local_webdw;
	}

	public void setLocal_webdw(WebDWSyntax local_webdw) {
		this.local_webdw = local_webdw;
	}

	/**
	 * 根据SQL语句进行解析，生成一个语法对象
	 * 
	 * @param strsql  select SQL语句
	 * @param sdwtype 数据窗口类型
	 * @param myint   存放返回值标志位
	 * @return
	 */
	public String SyntaxFromSQL(String strsql, String stype, MyInt myint) {
		String strDW = "";
		// 'step1 判断stype是否合法，是否是支持的DW类型
		if (!(stype.equals("freedom") || stype.equals("grid"))) {
			myint.intvalue = -1;
			errString = "不支持的DW类型:" + stype;
			return "";
		}

		// 'step2先调用通用功能
		generateDW_0Common(strsql, stype, myint);

		// 判断调用的返回值是否正确
		if (myint.intvalue == -1) {
			System.out.println("调用发生错误:" + this.errString);
			return "";
		}

		// '再根据stype来调用对应的的特殊功能
		MyInt iret = new MyInt(0);
		if (stype.equals("freedom")) {
			
			generateDW_1Freedom(strsql, stype, iret);
		}

		if (stype.equals("grid")) {
			 generateDW_2Grid( strsql, stype, iret);
		}
		return "";
	}

	private String generateDW_0Common(String strsql, String stype, MyInt myint) {
		local_webdw = new WebDWSyntax();
		// initDW (stype) ;
		// '先将Where语句切分成四部分，计算其中的四个子句
		// Dim selectpos As Long
		int selectpos;
		// Dim frompos As Long
		int frompos;
		// Dim wherepos As Long
		int wherepos;
		// Dim orderpos As Long
		int orderpos;

		// Dim temparray() As String
		String[] temparray;
		// Dim tempv As Variant
		String tempv;

		// Dim temps As String
		// String temps;
		// Dim id As Long
		int id;

		// '暂时先不考虑groupby,having关键字，以后增加
		// select的位置
		selectpos = InStr(1, strsql, "select ");

		// '检查select关键字
//	    If selectpos < 0 Then
//	        iret = -1
//	        errString = "SQL语句中找不到select关键字"
//	        Exit Function
//	    End If
		// 找不到select关键词的情况
		if (selectpos < 0) {
			myint.intvalue = -1;
			errString = "SQL语句中找不到select关键字";
			System.out.println(errString);
			return "";
		}

		frompos = InStr(selectpos + 6, strsql, " from ");

		// '检查from关键字
//	    If frompos < 0 Then
//	        iret = -1
//	        errString = "SQL语句中找不到from关键字"
//	        Exit Function
//	    End If
		if (frompos < 0) {
			myint.intvalue = -1;
			errString = "SQL语句中找不到from关键字";
			System.out.println(errString);
			return "";
		}

		columnlist = Mid(strsql, selectpos + 7, frompos - (selectpos + 7) + 1);// '得到数据列的列表

		// '检查where关键字
		wherepos = InStr(frompos, strsql, " where ");

//	    If wherepos > 0 Then
//	        tablelist = Mid(strsql, frompos + 6, wherepos - (frompos + 6) + 1)
//	    Else
//	        tablelist = Mid(strsql, frompos + 6, Len(strsql) - (frompos + 6) + 1)
//	    End If
		if (wherepos > 0) {
			tablelist = Mid(strsql, frompos + 6, wherepos - (frompos + 6) + 1);
		} else {
			tablelist = Mid(strsql, frompos + 6, Len(strsql) - (frompos + 6) + 1);
		}

		// '接下来用得到的columnlist和tablelist来填充local_webdw的数据
		// 'join子句，where子句，order子句的支持以后再添加，先完成一个基本功能。
		// '利用得到的table名称，来填充对应的Table参数
		temparray = Split(tablelist, ",");

		id = 0;
//	    For Each tempv In temparray
//	        temps = tempv                           '转换成字符串，代表一个数据表名称
//	        temps = Trim(temps)
//	        
//	        id = id + 1
//	        local_webdw.table.retrieve.pbselect.table[id] = temps
//	    Next
		// 存储table的数值
		for (String temp1 : temparray) {
			id = id + 1;
			local_webdw.table.retrieve.pbselect.table[id] = temp1;
		}

//	    Dim col_datatype As String
//	    Dim col_length As Long
//	    Dim stablename As String
//	    Dim scolname As String
//	    Dim ipos As Long
		String col_datatype = "", stablename = "", scolname = "";
		int col_length = 0, ipos;

		temparray = Split(columnlist, ",");// '将columnlist切分成数组
		id = 0;
//	    For Each tempv In temparray
		for (String temps : temparray) {
			// temps = tempv '转换成字符串,代表一个列名称
			// temps = Trim(temps)
			id = id + 1;
			// '利用这个column的名称，自动生成text,column,table里面的column

			// 'step1 初始化table.column
			local_webdw.table.Columns[id].dbname = temps;
			ipos = InStr(1, temps, ".");
//	        If ipos < 0 Then
//	            stablename = local_webdw.table.retrieve.pbselect.table(1)
//	            scolname = temps
//	        Else
//	            stablename = Left(temps, ipos - 1)
//	            scolname = Right(temps, Len(temps) - ipos)
//	        End If
			if (ipos <= 0) {
				stablename = local_webdw.table.retrieve.pbselect.table[1];
				scolname = temps;
			} else {
				stablename = Left(temps, ipos - 1);
				scolname = Right(temps, Len(temps) - ipos);

			}

			// '检索得到数据表对应列的字段类型和字段长度
//	        If getDataType(stablename, scolname, col_datatype, col_length) = -1 Then
//	            generateDW_0Common = ""
//	            iret = -1
//	            errString = "读取数据列的数据类型时发生错误!"
//	            Exit Function
//	        End If
			CWebDW_SyntaxFromSQL_DataTypeVO datatypevo = this.getDataTypeVO(stablename, scolname);
			if (datatypevo == null) {
				errString = "读取数据列的数据类型时发生错误![tablename:"+stablename+",scolname:"+scolname+"]";
				myint.intvalue = -1;
				return "";
			} else {
				// 记录列的数据类型和长度定义
				col_datatype = datatypevo.columntype;
				col_length = Integer.parseInt(datatypevo.columnlength);
			}

			local_webdw.table.Columns[id].Name = Replace(temps, ".", "_");// '名称的.变成下划线
			local_webdw.table.Columns[id].type = col_datatype + "(" + col_length + ")";
			local_webdw.table.Columns[id].update = "yes";
			local_webdw.table.Columns[id].updatewhereclause = "yes";

			// 'TODO:下一步需要根据此字段是否为主键，以及是否有表连接来判断key的属性
			local_webdw.table.Columns[id].key = "no";

			// 'step2 初始化table.retrieve.column 的值
			local_webdw.table.retrieve.pbselect.column[id] = "~" + "\"" + temps + "~" + "\"";

			// 'stemp3 初始化text的值
			local_webdw.text[id].Name = Replace(temps, ".", "_") + "_t";
			local_webdw.text[id].alignment = 2;
			local_webdw.text[id].text = Replace(temps, ".", "_");// '未来描述应当从数据库中得到
			local_webdw.text[id].border = 0;
			local_webdw.text[id].color = 0;
			local_webdw.text[id].font.face = "MS Sans Serif";
			local_webdw.text[id].font.height = -8;
			local_webdw.text[id].font.weight = 700;
			local_webdw.text[id].font.family = 2;
			local_webdw.text[id].font.pitch = 2;
			local_webdw.text[id].font.charset = 0;

			local_webdw.text[id].background_mode = 1;
			local_webdw.text[id].background_color = color_white;

			// 'step4 初始化column的属性
			local_webdw.column[id].width = col_length;// '暂时把列的数据库定义宽度存在width变量里面
			local_webdw.column[id].Name = Replace(temps, ".", "_");
			local_webdw.column[id].id = id;
			local_webdw.column[id].alignment = 0;
			local_webdw.column[id].tabsequence = id * 10;
			local_webdw.column[id].border = 1;
			local_webdw.column[id].color = 0;
			local_webdw.column[id].format = "[general]";
			local_webdw.column[id].tag = Replace(temps, ".", "_");
			local_webdw.column[id].edit_limit = 0;
			local_webdw.column[id].edit_case = "any";
			local_webdw.column[id].edit_focusrectangle = "no";
			local_webdw.column[id].edit_autoselect = "no";
			local_webdw.column[id].edit_autohscroll = "no";
			local_webdw.column[id].font.face = "MS Sans Serif";
			local_webdw.column[id].font.height = -8;
			local_webdw.column[id].font.weight = 400;
			local_webdw.column[id].font.family = 2;
			local_webdw.column[id].font.pitch = 2;
			local_webdw.column[id].font.charset = 0;
			local_webdw.column[id].background_mode = 1;
			local_webdw.column[id].background_color = color_white;
			local_webdw.column[id].band = "detail";
			// Next
		}

		return "";
	}

	/**
	 * 根据传入这个类的一个字符串进行判断，得到一个列定义的VO对象
	 * 
	 * @param stable
	 * @param scol
	 * @return
	 */
	private CWebDW_SyntaxFromSQL_DataTypeVO getDataTypeVO(String stable, String scol) {
		//去除空格
		stable = stable.trim();
		scol = scol.trim();
		
		String[] line;
		String[] sdata;

		String aline;// As Variant
		// String sline ;//As String
		int id;

		int tab_colIndex;// As Long 'TNAME列的序号
		int col_colIndex;// As Long 'CNAME列的序号
		int type_index;// As Long
		int len_index;// As Long

		// '判断是否是空字符串
		if (this.colDefString.equals("")) {
			errString = "尚未设置colDefString的值";
			System.out.println(errString);
			return null;
		}else {
			System.out.println("coldefine:"+colDefString);
		}

		// '计算Tname列 和 Cname列的序号
		tab_colIndex = getColIndex("TNAME");
		col_colIndex = getColIndex("CNAME");
		type_index = getColIndex("COLTYPE");
		len_index = getColIndex("WIDTH");

		if (tab_colIndex == -1 || col_colIndex == -1 || type_index == -1 || len_index == -1) {
			// getDataType = -1
			errString = "无法获得TNAME列和CNAME列的下标";
			System.out.println(errString);
			return null;
		}else {
			System.out.println("tab_colIndex:"+tab_colIndex);
			System.out.println("col_colIndex:"+col_colIndex);
			System.out.println("type_index:"+type_index);
			System.out.println("len_index:"+len_index);
		}

		// '数据切分成多行
		line = Split(colDefString, "" + Chr(13) + Chr(10));

		for (String sline : line) {
			System.out.println("sline:"+sline);
			sdata = Split(sline, "" + Chr(9));
			System.out.println("sdata.length:"+sdata.length);
			if (sdata[tab_colIndex].equals(stable) && sdata[col_colIndex].equals(scol)) {// Then
				CWebDW_SyntaxFromSQL_DataTypeVO datatypevo = new CWebDW_SyntaxFromSQL_DataTypeVO();
				datatypevo.columntype = sdata[type_index];
				datatypevo.columnlength = sdata[len_index];
				return datatypevo;
			}
		}

		// 找不到，返回null对象,调用者需要自己判断这个返回值是否合法
		return null;
	}

//	'从colDefString中检索，检索其中第一行
//	'给出一个列名，检索得到对应的下标号(从0开始)
//	'返回值:>=0 代表下标号
//	'返回-1代表找不到,发生错误
	private int getColIndex(String colname) {
		String[] line;// () As String
		String[] colnames;// () As String
		int id;

		// On Error GoTo errorHandle
		// '数据切分成多行
		line = Split(colDefString, "" + Chr(13) + Chr(10));
		// 第一行代表列定义
		colnames = Split(line[0], "" + Chr(9));
		for (id = 0; id <= UBound(colnames); id++) {
			if (UCase(colnames[id]).equals(UCase(colname))) {
				return id;
			}
		}
		return -1;
	}

	// '根据给定的SQL语句，以及对应的数据窗口类型
	// '生成一个Freedom风格的DW出来
	private String generateDW_1Freedom(String strsql, String stype, MyInt iret) {
		String temparray[];
		int id;
		// Dim tempv As Variant
		// Dim temps As String
		int col_length;

		temparray = Split(columnlist, ","); // '将columnlist切分成数组
		id = 0;
		// For Each tempv In temparray
		for (String temps : temparray) {
			// temps = tempv '转换成字符串,代表一个列名称
			temps = Trim(temps);
			id = id + 1;

			// '设置text的属性
			local_webdw.text[id].x = 10;
			if (id == 1) {// Then
				local_webdw.text[id].y = 10;
			} else {
				local_webdw.text[id].y = local_webdw.column[id - 1].y + local_webdw.column[id - 1].height + 50;
			}

			local_webdw.text[id].width = 500;
			local_webdw.text[id].height = 50;
			local_webdw.text[id].band = "detail";
			local_webdw.detail.height = local_webdw.text[id].y + local_webdw.text[id].height + 200;

			// '设置column的属性
			local_webdw.column[id].x = 600;
			if (id == 1) {
				local_webdw.column[id].y = 10;
			} else {
				local_webdw.column[id].y = local_webdw.column[id - 1].y + local_webdw.column[id - 1].height + 50;
			}

			// '从width里面取出col_length出来
			col_length = local_webdw.column[id].width;//
			if (col_length <= 30) {
				if (col_length < 10) {
					local_webdw.column[id].width = 200;
					local_webdw.column[id].height = 50;
				} else {
					local_webdw.column[id].width = 25 * col_length;
					local_webdw.column[id].height = 50;
				}
			} else {
				local_webdw.column[id].width = 800;
				local_webdw.column[id].height = 200;
			}

			// '对于freedom类型的DW，设置detail的height
			if (local_webdw.detail.height < local_webdw.column[id].y + local_webdw.column[id].height + 100) {
				local_webdw.detail.height = local_webdw.column[id].y + local_webdw.column[id].height + 100;
			}

		}

		return "";
	}

	private String generateDW_2Grid(String strsql, String stype, MyInt iret) {
		String temparray[];
		// Dim tempv As Variant
		// Dim temps As String
		int id;// As Long
		int ipos;// As Long
		String stablename;// As String
		String scolname;// As String
		int col_length;// As Long
		int sum_width;// As Long

		sum_width = 0;
		temparray = Split(columnlist, ",");// '将columnlist切分成数组
		id = 0;
		// For Each tempv In temparray
		// String temps;
		for (String temps : temparray) {
			// temps = tempv '转换成字符串,代表一个列名称
			// temps = Trim(temps)
			id = id + 1;
			// '利用这个column的名称，自动生成text,column,table里面的column

			// 'step1 初始化table.column
			// String temp1 = temps;
			// local_webdw.table.Columns[id].dbname = temps;
			// ipos = InStr(1, temps, ".");
			// If ipos < 0 Then
			// stablename = local_webdw.table.retrieve.pbselect.table(1)
			// scolname = temps
			// Else
			// stablename = Left(temps, ipos - 1)
			// scolname = Right(temps, Len(temps) - ipos)
			// End If

			// '设置text的属性
			local_webdw.text[id].y = 10;
			local_webdw.text[id].x = 10 + sum_width;
			local_webdw.text[id].height = 200;
			local_webdw.text[id].band = "header";
			local_webdw.header.height = 200;
			local_webdw.detail.height = 100;

			// '设置column的属性
			local_webdw.column[id].border = 0;
			local_webdw.column[id].y = 10;
			local_webdw.column[id].x = 10 + sum_width;
			col_length = local_webdw.column[id].width;
			local_webdw.column[id].width = 500;

			if (col_length <= 20) {
				if (col_length < 10) {
					local_webdw.column[id].width = 200;
					local_webdw.text[id].width = 200;
					local_webdw.column[id].height = 50;
				} else {
					local_webdw.column[id].width = col_length * 25;
					local_webdw.text[id].width = col_length * 25;
					local_webdw.column[id].height = 50;
				}
			} else {
				local_webdw.column[id].width = 500;
				local_webdw.text[id].width = 500;
				local_webdw.column[id].height = 200;
				local_webdw.detail.height = 220;
			}

			sum_width = sum_width + 10 + local_webdw.column[id].width;
		}

		return "";
	}

}
