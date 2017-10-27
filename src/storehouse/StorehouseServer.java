package storehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StorehouseServer {
	/**
	 * 根据用户的货物来选择货物的发送方案
	 * @param:ProcurementPlanBean(plan)客户的采购计划
	 * 			List<StorehouseBean>(storehouses)所有的仓库列表
	 * */
	public List<DeliveryPlanBean> getDeliveryPlanByClient(ProcurementPlanBean plan,List<StorehouseBean> storehouses){
		/**
		 * 1、根据客户的采购计划的目的地->获取仓库的发货优先级。
		 * */
		List<StorehouseBean> StorehouseList = orderStorehouseByDestination(plan.getDestination(),storehouses);
		/**
		 * 2、根据客户的采购计划的采购量->货物的发送方案.
		 * */
		List<DeliveryPlanBean> planBeans=getDeliveryPlanBeanByProcurementPlan(plan,StorehouseList);
		
		return planBeans;
	} 
	
	/**
	 * 根据客户的采购计划的采购量->获取货物的发送方案.
	 * */
	private List<DeliveryPlanBean> getDeliveryPlanBeanByProcurementPlan(ProcurementPlanBean plan,List<StorehouseBean> storehouses){
		List<DeliveryPlanBean> planList=new ArrayList<DeliveryPlanBean>();
		DeliveryPlanBean planBean=new DeliveryPlanBean();
		//1、判断第一优先级的仓库是否能满足客户的采购需求
		boolean isSatisfied=isSatisfiy(plan.getProcurementPlan(),storehouses.get(0).getStock());
		
		return planList;
	}
	
	/**
	 * 仓库是否满足客户的采购需求
	 * */
	private boolean isSatisfiy(Map<String ,Integer> plan,Map<String ,Integer> stock){
		boolean satisfiy=true;
		Set<String> planset = plan.keySet();
		
		if(!planset.contains(stock))
			return false;
		
		for(String key:planset){
			int i=stock.get(key);
			
		}
		return satisfiy;
	}
	
	/**
	 * 根据用户发货的目的地->选择仓库的优先级
	 * @param destination发货的目的地 ，storehouseList仓库列表
	 * @return storehouseList
	 * @date 2017年10月27日16:09:42
	 * */
	public List<StorehouseBean> orderStorehouseByDestination(String destination,List<StorehouseBean> storehouseList){
		storehouseList.forEach((bean)->{
			bean.setDistance(getDistance(destination,bean.getAddress()));
		});
		Collections.sort(storehouseList,  new Comparator<StorehouseBean>() {
			@Override
			public int compare(StorehouseBean o1, StorehouseBean o2) {
				return o1.getDistance()-o2.getDistance();
			} 
		});		
		return storehouseList;
	}
	/**
	 * 根据两地的地址获取距离
	 * */
	private int getDistance(String destination,String storehouseAddress){
		return Math.abs(Integer.parseInt(destination)-Integer.parseInt(storehouseAddress));
	}
}
