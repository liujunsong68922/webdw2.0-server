package hello.dbinterface.exception;

public class QueryException extends Exception{
	/**
	 * 数据查询接口合并到数据窗口项目组件里面去。
	 * 数据查询后台服务的异常类
	 */
	private static final long serialVersionUID = 1L;
	public String errString="";
	
	public QueryException(String _errinfo) {
		this.errString = _errinfo;
	}
}
