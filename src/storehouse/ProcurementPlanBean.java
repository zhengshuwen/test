package storehouse;

import java.util.Map;

/**
 * 客户的采购计划
 * @author zhengshuwen
 * @date 2017年10月27日16:34:07
 * */
public class ProcurementPlanBean {
	/**
	 * 客户名称
	 * */
	private String name;
	/**
	 * 目的地
	 * */
	private String destination;
	/**
	 * 采购详情
	 * */
	private Map<String ,Integer> procurementPlan;
	
	
	
	
	/**
	 * procurementPlan采购详情，即货物的详情
	 * */
	public ProcurementPlanBean(String name, String destination, Map<String, Integer> procurementPlan) {
		super();
		this.name = name;
		this.destination = destination;
		this.procurementPlan = procurementPlan;
	}
	/**
	 * 客户名称
	 * */
	public String getName() {
		return name;
	}
	/**
	 * 客户名称
	 * */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 目的地
	 * */
	public String getDestination() {
		return destination;
	}
	/**
	 * 目的地
	 * */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * 采购详情
	 * */
	public Map<String, Integer> getProcurementPlan() {
		return procurementPlan;
	}
	/**
	 * 采购详情
	 * */
	public void setProcurementPlan(Map<String, Integer> procurementPlan) {
		this.procurementPlan = procurementPlan;
	}
	
}
