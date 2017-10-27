package storehouse;

import java.util.List;
import java.util.Map;

/**
 * 货物发送方案
 * @author zhenghsuwen
 * @date 2017年10月27日16:16:07
 * */
public class DeliveryPlanBean {
	/**
	 * 方案名称
	 * */
	private String name;
	/**
	 * 方案单号
	 * */
	private String id;
	/**
	 * 用户需求的货物量string 货物名称，Integer 货物数量
	 * */
	private Map<String, Integer> clientStoreNum;
	/**
	 * 仓库的出货情况
	 * */
	
	
	
	
	
	private List<Map<String,String>> deliveryMessage;
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
	/**
	 * 用户需求的货物量string 货物名称，Integer 货物数量
	 * */
	public Map<String, Integer> getClientStoreNum() {
		return clientStoreNum;
	}
	/**
	 * 用户需求的货物量string 货物名称，Integer 货物数量
	 * */
	public void setClientStoreNum(Map<String, Integer> clientStoreNum) {
		this.clientStoreNum = clientStoreNum;
	}
	/**
	 * 仓库的出货情况
	 * */
	public List<Map<String, String>> getDeliveryMessage() {
		return deliveryMessage;
	}
	/**
	 * 仓库的出货情况
	 * */
	public void setDeliveryMessage(List<Map<String, String>> deliveryMessage) {
		this.deliveryMessage = deliveryMessage;
	}
	
}
