package kr.co.goodee39.vo;

public class FileVO {
	private int num;
	private String serverName;
	private String localName;
	private int bnum;
	
	public FileVO() {
		// TODO Auto-generated constructor stub
	}
	
	public FileVO(String serverName, String localName) {
		this.serverName = serverName;
		this.localName = localName;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	
		
}