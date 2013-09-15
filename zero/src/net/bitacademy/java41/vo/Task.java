package net.bitacademy.java41.vo;

import java.io.Serializable;
import java.sql.Date;

public class Task implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int tno;
	private int pno;
	private String title;
	private String uiProtoUrl;
	private String content;
	private Date startDate;
	private Date endDate;
	private int status;
	
	
	public int getTno() {
		return tno;
	}
	public Task setTno(int tno) {
		this.tno = tno;
		return this;
	}
	public int getPno() {
		return pno;
	}
	public Task setPno(int pno) {
		this.pno = pno;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public Task setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getUiProtoUrl() {
		return uiProtoUrl;
	}
	public Task setUiProtoUrl(String uiProtoUrl) {
		this.uiProtoUrl = uiProtoUrl;
		return this;
	}
	public String getContent() {
		return content;
	}
	public Task setContent(String content) {
		this.content = content;
		return this;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Task setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}
	public Date getEndDate() {
		return endDate;
	}
	public Task setEndDate(Date endDate) {
		this.endDate = endDate;
		return this;
	}
	public int getStatus() {
		return status;
	}
	public Task setStatus(int status) {
		this.status = status;
		return this;
	}
	
	


}