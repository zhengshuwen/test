package storehouse;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class StorehouseServerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		//设置三个仓库，并设置他的库存
		Map<String, Integer> stock=new HashMap<String, Integer>();//仓库库存
		stock.put("苹果", 1000);
		stock.put("橘子", 3000);
		stock.put("香蕉", 4000);
		StorehouseBean bean1=new StorehouseBean("仓库1", "001", "10012", stock);
		stock.put("香蕉", 1000);
		StorehouseBean bean2=new StorehouseBean("仓库2", "002", "11190", stock);
		stock.put("橘子", 2000);
		StorehouseBean bean3=new StorehouseBean("仓库3", "003", "10400", stock);
		List<StorehouseBean> storehouseList=new ArrayList<StorehouseBean>();
		storehouseList.add(bean1);
		storehouseList.add(bean2);
		storehouseList.add(bean3);
		
		//客户的采购计划
		Map<String, Integer> procurement=new HashMap<String, Integer>();//仓库库存
		procurement.put("苹果", 500);
		procurement.put("橘子", 5000);
		procurement.put("香蕉", 6000);
		ProcurementPlanBean procurementPlan=new ProcurementPlanBean("zhengshuwen", "10000", procurement);
		
		
//		StorehouseServer storehouseServer=new StorehouseServer();
//		List<StorehouseBean> newList=storehouseServer.orderStorehouseByDestination(procurementPlan.getDestination(),storehouseList);
//		assertEquals(newList.size(),storehouseList.size());
//		
//		newList.forEach((bean)->{
//			System.out.println(bean.getName()+","+bean.getDistance());
//		});
	}

}
