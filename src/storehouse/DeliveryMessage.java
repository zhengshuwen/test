package storehouse;

import java.util.Map;

/**
 * 货物发送方案详情（仓库的出货情况）
 * */
public class DeliveryMessage {
	private String name;
	private String id;
	/**
	 * 仓库名称
	 * */
	private String storehouseName;
	/**
	 * 仓库出货信息
	 * */
	private Map<String ,Integer> message;
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
	public String getStorehouseName() {
		return storehouseName;
	}
	public void setStorehouseName(String storehouseName) {
		this.storehouseName = storehouseName;
	}
	/**
	 * 仓库出货信息
	 * */
	public Map<String, Integer> getMessage() {
		return message;
	}
	/**
	 * 仓库出货信息
	 * */
	public void setMessage(Map<String, Integer> message) {
		this.message = message;
	}
	
	
	
}
