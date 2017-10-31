package storehouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

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
		 *  运输方案：1、选择可接受范围内的所有仓库
		 *  		  2、一个仓库完成全部的采购需求。
		 *  		  3、根据仓库的优先级来逐步分配数量
		 *  				即第一优先级的仓库如果无法满足采购需求，就有第二优先级的仓库来补充。以此类推。
		 * */
		List<DeliveryPlanBean> planBeans=getDeliveryPlanBeanByProcurementPlan(plan,StorehouseList);
		
		
		return planBeans;
	} 
	
	/**
	 * 根据客户的采购计划的采购量->获取货物的发送方案.
	 * */
	private List<DeliveryPlanBean> getDeliveryPlanBeanByProcurementPlan(ProcurementPlanBean plan,List<StorehouseBean> storehouses){
		List<DeliveryPlanBean> planList=new ArrayList<DeliveryPlanBean>();
		/**
		 * 第一个方案：一个仓库完成全部的采购需求。
		 * */
		//获取能满足采购需求的仓库,并生产货物的发送方案.
		for(StorehouseBean bean:storehouses){
			if(isSatisfiy(plan.getProcurementPlan(),bean.getStock())){
				DeliveryPlanBean deliveryPlan=getDeliveryPlan(plan,Arrays.asList(bean));
				planList.add(deliveryPlan);
			}
		}
		
		/**
		 * 第二个方案：根据仓库的优先级来逐步分配数量。
		 * */
		//1、判断第一优先级的仓库是否能满足客户的采购需求
		boolean isSatisfied=isSatisfiy(plan.getProcurementPlan(),storehouses.get(0).getStock());
		//排除第一优先级就满足的采购需求的情况
		if(!isSatisfied){
			List<StorehouseBean> storehouseList=new ArrayList<StorehouseBean>();
			Map<String ,Integer> procurement=new HashMap<String,Integer>();
			procurement.putAll(plan.getProcurementPlan());
			for(StorehouseBean bean:storehouses){
				procurement=subtract(procurement,bean.getStock());
				storehouseList.add(bean);
				if(procurement.isEmpty()){
					break;
				}
			}
			//如果不为空，即仓库库存不能满足客户采购量
			if(!procurement.isEmpty()){
				return null;
			}
			DeliveryPlanBean deliveryPlan = getDeliveryPlan(plan,storehouseList);
			planList.add(deliveryPlan);
		}
		return planList;
	}
	
	/**
	 * 计算仓库与客户采购量的差值
	 * @param plan:采购量，stock:库存
	 * */
	private Map<String ,Integer> subtract(Map<String ,Integer> plan,Map<String ,Integer> stock){
		Map<String ,Integer> map=new HashMap<String,Integer>();
		map.putAll(plan);
		Iterator<Entry<String,Integer>> it=map.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,Integer> entry=it.next();
			int j=entry.getValue();
			int i=stock.get(entry.getKey());
			if(i>=j){
				it.remove();
			}else{
				map.put(entry.getKey(),j-i);
			}
		}
		return map;
	}
	
	
	/**
	 * 生产货物的发送方案.
	 * @param plan:客户的采购计划，storehouses：生成方案的仓库列表。
	 * */
	private DeliveryPlanBean getDeliveryPlan(ProcurementPlanBean plan,List<StorehouseBean> storehouses){
		DeliveryPlanBean deliveryPlanBean=new DeliveryPlanBean(plan.getName()+"客户采购发货方案", UUID.randomUUID().toString().replaceAll("-", ""), 
				plan.getProcurementPlan(), null);
		
		if(1==storehouses.size()){
			DeliveryMessage deliveryMessage=new DeliveryMessage(deliveryPlanBean.getName()+"详情", 
					UUID.randomUUID().toString().replaceAll("-", ""), storehouses.get(0).getName(), plan.getProcurementPlan());
			deliveryPlanBean.setDeliveryMessage(Arrays.asList(deliveryMessage));
		}else{
			List<DeliveryMessage> deliveryMessageList=new ArrayList<DeliveryMessage>(); 
			for(StorehouseBean bean:storehouses){
				DeliveryMessage deliveryMessage=new DeliveryMessage(deliveryPlanBean.getName()+"详情", 
						UUID.randomUUID().toString().replaceAll("-", ""), bean.getName(), null);
				//获取方案详细信息
				Map<String, Integer> message=getDeliveryMessage(plan.getProcurementPlan(),bean.getStock());
				deliveryMessage.setMessage(message);
				deliveryMessageList.add(deliveryMessage);
			}
			deliveryPlanBean.setDeliveryMessage(deliveryMessageList);
		}
		return deliveryPlanBean;
	}
	
	/**
	 * 获取发货详情
	 * */
	private Map<String, Integer> getDeliveryMessage(Map<String, Integer> planMessage,Map<String, Integer> stock){
		Map<String, Integer> map=new HashMap<String,Integer>();
		map.putAll(planMessage);
		Iterator<Entry<String, Integer>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Integer> entry=iterator.next();
			int stockNum=stock.get(entry.getKey());
			int planNum=entry.getValue();
			//采购数量大于仓库数量
			if(planNum>stockNum){
				//map取仓库的最大值，即全部的商品都要发出
				map.put(entry.getKey(), stockNum);
				//设置客户采购的数量
				planMessage.put(entry.getKey(), planNum-stockNum);
			}else{
				planMessage.put(entry.getKey(), 0);
			}
		}
		return map;
	}
	
	/**
	 * 仓库是否满足客户的采购需求
	 * @param plan：客户采购量，stock：仓库的储存量
	 * */
	private boolean isSatisfiy(Map<String ,Integer> plan,Map<String ,Integer> stock){
		Set<String> planset = plan.keySet();
		for(String key:planset){
			//如果仓库的商品种类没有采购需求的商品
			if(!stock.keySet().contains(key))
				return false;
			int i=stock.get(key);
			//如果仓库的商品数量不足
			if(i<plan.get(key)){
				return false;
			}
		}
		return true;
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
