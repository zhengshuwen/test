package storehouse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 仓库详情
 * @author zhengshuwen
 * @date:2017年10月27日15:56:58
 * */
public class StorehouseBean {
	/**
	 * 仓库名称 
	 * */	
	private String name;
	/**
	 * 仓库id
	 * */
	private String id;
	/**
	 * 仓库地址
	 * */
	private String address;
	/**
	 * 仓库库存,string 货物名称，Integer 货物数量
	 * */
	private Map<String,Integer> stock;
	/**
	 * 与发货目的地的距离
	 * */
	private int distance;
	
	
	/**
	 * stock 仓库库存,string 货物名称，Integer 货物数量
	 * */
	public StorehouseBean(String name, String id, String address, Map<String, Integer> stock) {
		super();
		this.name = name;
		this.id = id;
		this.address = address;
		this.stock = stock;
	}
	public StorehouseBean(){
		
	}

	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 仓库库存string 货物名称，Integer 货物数量
	 * */
	public Map<String, Integer> getStock() {
		return stock;
	}
	/**
	 * 仓库库存string 货物名称，Integer 货物数量
	 * */
	public void setStock(Map<String, Integer> stock) {
		this.stock = stock;
	}
	/**
	 * 与发货目的地的距离
	 * */
	public int getDistance() {
		return distance;
	}
	/**
	 * 与发货目的地的距离
	 * */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
}
