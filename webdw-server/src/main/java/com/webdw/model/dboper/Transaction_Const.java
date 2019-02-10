package com.webdw.model.dboper;

/**
 * ���������ʵ���س�������
 * @author Administrator
 *
 */
public class Transaction_Const {
	public String Trans_Oper_Query = "1"; // '��ѯ���� //1
	public String Trans_Oper_Exec = "2"; // 'ִ�в��� //2
	public String Trans_Oper_TableList = "3"; // '���ݱ��б���� //3
	public String Trans_Oper_ColumnList = "4"; // '�������б���� //4
	// '������������ط���
	public String Trans_BeginTrans = "begintrans"; // '����һ������
	public String Trans_AddCommand = "addcommand"; // '��������
	public String Trans_Commit = "commit"; // '�ύ����
	public String Trans_Rollback = "rollback"; // '�ع���ȡ��������
	// '�����ǻ�����ݴ��ڶ���ķ���
	public String Trans_GetDWDefine = "getdwdefine"; // '�Ӻ�̨�������ݴ��ڶ����ļ�

}
