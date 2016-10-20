package com;

public class PrintBean {
	private String SID;    //发货单号
	private String goods;  //商品名称
	private String num;    //数量
	private String chck;   //出货仓库
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getChck() {
		return chck;
	}
	public void setChck(String chck) {
		this.chck = chck;
	}
}
