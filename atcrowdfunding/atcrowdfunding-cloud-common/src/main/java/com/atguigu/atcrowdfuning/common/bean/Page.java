package com.atguigu.atcrowdfuning.common.bean;

import java.util.List;

public class Page<T> {

	private Integer pageno;
	private Integer pagesize;
	private Integer totalno;
	private Integer totalsize;
	private List<T> datas;
	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getTotalno() {
		return totalno;
	}
	public void setTotalno(Integer totalno) {
		this.totalno = totalno;
	}
	public Integer getTotalsize() {
		return totalsize;
	}
	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	
}
