package com.webdw.model.syntaxmodel.dwsyntax;

//'table��Ƕ��column���Զ���
public class WebDW_Table_Column {
	public String type = ""; // '��������

	public String update = ""; // '�Ƿ���Ը���yes/no

	public String updatewhereclause = ""; // '�Ƿ��Ǹ���ʱ��where����yes/no

	public String key = ""; // '�Ƿ�������

	public String Name = ""; // '�ֶ���

	public String dbname = ""; // '���ݿ��ֶ���

	public String values = ""; // '����ֵ�������洢�༭���ʱ����Ϣ

	public String validations = ""; // '�༭��У���ַ���

	public WebDW_Table_Column Clone() {

		WebDW_Table_Column newOne = new WebDW_Table_Column();

		newOne.type = type; // '��������

		newOne.update = update; // '�Ƿ���Ը���yes/no

		newOne.updatewhereclause = updatewhereclause; // '�Ƿ��Ǹ���ʱ��where����yes/no

		newOne.key = key; // '�Ƿ�������

		newOne.Name = Name; // '�ֶ���

		newOne.dbname = dbname; // '���ݿ��ֶ���

		newOne.values = values; // '����ֵ�������洢�༭���ʱ����Ϣ

		newOne.validations = validations; // '�༭��У���ַ���

		return newOne;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getUpdatewhereclause() {
		return updatewhereclause;
	}

	public void setUpdatewhereclause(String updatewhereclause) {
		this.updatewhereclause = updatewhereclause;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getValidations() {
		return validations;
	}

	public void setValidations(String validations) {
		this.validations = validations;
	}

}
