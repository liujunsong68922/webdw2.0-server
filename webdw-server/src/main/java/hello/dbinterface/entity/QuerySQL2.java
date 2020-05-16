package hello.dbinterface.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="common_query_sql2")
public class QuerySQL2 {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="queryname")
	private String queryname;
	
	@Column(name="querysql")
	private String querysql;
	
	@Column(name="queryargs")
	private String queryargs;
	
	@Column(name="memo")
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQueryname() {
		return queryname;
	}

	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}

	public String getQuerysql() {
		return querysql;
	}

	public void setQuerysql(String querysql) {
		this.querysql = querysql;
	}

	public String getQueryargs() {
		return queryargs;
	}

	public void setQueryargs(String queryargs) {
		this.queryargs = queryargs;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 转换为CommonQuerySQL
	 * @return
	 */
	public CommonQuerySQL toCommonQuerySQL() {
		CommonQuerySQL commonquerysql = new CommonQuerySQL();
		commonquerysql.setId(this.id);
		commonquerysql.setQueryname(this.queryname);
		commonquerysql.setQuerysql(this.querysql);
		commonquerysql.setQueryargs(this.queryargs);
		commonquerysql.setMemo(this.memo);
		return commonquerysql;
	}		
}
