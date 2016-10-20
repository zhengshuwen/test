package com;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GetTest {
	@Test
	public void getTest(){
		int a=1;
		int b=4;
		int c=0;
		String SID="123";
		List<PrintBean> getTest=get(SID,a,b,c);
		for(int j=0;j<getTest.size();j++){
			PrintBean printBean=getTest.get(j);
			System.out.println("发货单 号:"+printBean.getSID()+
					",商品名称:"+printBean.getGoods()+
					",数量:"+printBean.getNum()+
					",出货仓库："+printBean.getChck());
		}
	}	
	@Test
	public void test(){
		System.out.println("1");
	}
	public List<PrintBean> get(String SID,int a,int b,int c){
		int[] w1=new int[]{1,2,2};
		int[] w2=new int[]{2,1,2};
		int[] w3=new int[]{1,3,1};
		int aLength=w1[0]+w2[0]+w3[0];
		int bLength=w1[1]+w2[1]+w3[1];
		int cLength=w1[2]+w2[2]+w3[2];
		List<PrintBean> orderList=new ArrayList<PrintBean>();
		if(a<=aLength&&b<=bLength&&c<=cLength){
			/*
			顾客下单地址距离依次顺序为w3=w2>w1
			仓库优先级排序是w1<w2<w3
				1、当出货数量三个仓库都能满足时，优先现在w1，然后是w3，最后是w2；
				2、当三个仓库对出货数量都不能满足时，但是可以用两个仓库来满足时。
					w1[0]+w3[0]>=a,w1[1]+w3[1]>=b,w1[2]+w3[2]>=c;
					w1[0]+w2[0]>=a,w1[1]+w2[1]>=b,w1[2]+w2[2]>=c;
					w2[0]+w3[0]>=a;w2[1]+w3[1]>=b,w2[2]+w3[2]>=c;
				3、当需要三个仓库一起来满足时。
					
			*/
			if(a<=w1[0]&&b<=w1[1]&&c<=w1[2]){
				//w1仓库出货可以满足
				System.out.println("w1");
				PrintBean printBeanA=new PrintBean();
				PrintBean printBeanB=new PrintBean();
				PrintBean printBeanC=new PrintBean();
				if(a>0){
					printBeanA.setSID(SID);
					printBeanA.setGoods("a");
					printBeanA.setNum(Integer.toString(a));
					printBeanA.setChck("w1");
					orderList.add(printBeanA);
				}
				if(b>0){
					printBeanB.setSID(SID);
					printBeanB.setGoods("b");
					printBeanB.setNum(Integer.toString(b));
					printBeanB.setChck("w1");
					orderList.add(printBeanB);
				}
				if(c>0){
					printBeanC.setSID(SID);
					printBeanC.setGoods("c");
					printBeanC.setNum(Integer.toString(c));
					printBeanC.setChck("w1");
					orderList.add(printBeanC);
				}

			}else if(a<=w3[0]&&b<=w3[1]&&c<=w3[2]){
				System.out.println("w3");
				//w3仓库出货可以满足
				PrintBean printBeanA=new PrintBean();
				PrintBean printBeanB=new PrintBean();
				PrintBean printBeanC=new PrintBean();
				if(a>0){
					printBeanA.setSID(SID);
					printBeanA.setGoods("a");
					printBeanA.setNum(Integer.toString(a));
					printBeanA.setChck("w3");
					orderList.add(printBeanA);
				}
				
				if(b>0){
					printBeanB.setSID(SID);
					printBeanB.setGoods("b");
					printBeanB.setNum(Integer.toString(b));
					printBeanB.setChck("w3");
					orderList.add(printBeanB);
				}
				
				if(c>0){
					printBeanC.setSID(SID);
					printBeanC.setGoods("c");
					printBeanC.setNum(Integer.toString(c));
					printBeanC.setChck("w3");
					orderList.add(printBeanC);
				}
				
			}else if(a<=w2[0]&&b<=w2[1]&&c<=w2[2]){
				System.out.println("w2");
				//w2仓库出货可以满足
				PrintBean printBeanA=new PrintBean();
				PrintBean printBeanB=new PrintBean();
				PrintBean printBeanC=new PrintBean();
				if(a>0){
					printBeanA.setSID(SID);
					printBeanA.setGoods("a");
					printBeanA.setNum(Integer.toString(a));
					printBeanA.setChck("w2");
					orderList.add(printBeanA);
				}
				
				if(b>0){
					printBeanB.setSID(SID);
					printBeanB.setGoods("b");
					printBeanB.setNum(Integer.toString(b));
					printBeanB.setChck("w2");	
					orderList.add(printBeanB);
				}
				
				if(c>0){
					printBeanC.setSID(SID);
					printBeanC.setGoods("c");
					printBeanC.setNum(Integer.toString(c));
					printBeanC.setChck("w2");
					orderList.add(printBeanC);
				}
				
				
			}else if(w1[0]+w3[0]>=a&&w1[1]+w3[1]>=b&&w1[2]+w3[2]>=c){
				//w1+w3仓库出货可以满足
				System.out.println("w1+w3");
				if(a>0){
					PrintBean printBeanA1=new PrintBean();
					printBeanA1.setSID(SID);
					printBeanA1.setGoods("a");
					printBeanA1.setNum(Integer.toString(w1[0]));
					printBeanA1.setChck("w1");
					orderList.add(printBeanA1);
					if(a-w1[0]>0){
						PrintBean printBeanA2=new PrintBean();
						printBeanA2.setSID(SID);
						printBeanA2.setGoods("a");
						printBeanA2.setNum(Integer.toString(a-w1[0]));
						printBeanA2.setChck("w3");
						orderList.add(printBeanA2);
					}
				}
				if(b>0){
					PrintBean printBeanB1=new PrintBean();
					printBeanB1.setSID(SID);
					printBeanB1.setGoods("b");
					printBeanB1.setNum(Integer.toString(w1[1]));
					printBeanB1.setChck("w1");
					orderList.add(printBeanB1);
					if(b-w1[1]>0){
						PrintBean printBeanB2=new PrintBean();
						printBeanB2.setSID(SID);
						printBeanB2.setGoods("b");
						printBeanB2.setNum(Integer.toString(b-w1[1]));
						printBeanB2.setChck("w3");
						orderList.add(printBeanB2);
					}
				}
				if(c>0){
					PrintBean printBeanC1=new PrintBean();
					printBeanC1.setSID(SID);
					printBeanC1.setGoods("c");
					printBeanC1.setNum(Integer.toString(w1[2]));
					printBeanC1.setChck("w1");
					orderList.add(printBeanC1);
					if(c-w1[2]>0){
						PrintBean printBeanC2=new PrintBean();
						printBeanC2.setSID(SID);
						printBeanC2.setGoods("c");
						printBeanC2.setNum(Integer.toString(c-w1[2]));
						printBeanC2.setChck("w3");
						orderList.add(printBeanC2);
					}
				}

			}else if(w1[0]+w2[0]>=a&&w1[1]+w2[1]>=b&&w1[2]+w2[2]>=c){
				//w1+w2仓库出货可以满足
				System.out.println("w1+w2");
				if(a>0){
					PrintBean printBeanA1=new PrintBean();
					printBeanA1.setSID(SID);
					printBeanA1.setGoods("a");
					printBeanA1.setNum(Integer.toString(w1[0]));
					printBeanA1.setChck("w1");
					orderList.add(printBeanA1);
					if(a-w1[0]>0){
						PrintBean printBeanA2=new PrintBean();
						printBeanA2.setSID(SID);
						printBeanA2.setGoods("a");
						printBeanA2.setNum(Integer.toString(a-w1[0]));
						printBeanA2.setChck("w2");
						orderList.add(printBeanA2);
					}
				}
				
				if(b>0){
					PrintBean printBeanB1=new PrintBean();
					printBeanB1.setSID(SID);
					printBeanB1.setGoods("b");
					printBeanB1.setNum(Integer.toString(w1[1]));
					printBeanB1.setChck("w1");
					orderList.add(printBeanB1);
					if(b-w1[1]>0){
						PrintBean printBeanB2=new PrintBean();
						printBeanB2.setSID(SID);
						printBeanB2.setGoods("b");
						printBeanB2.setNum(Integer.toString(b-w1[1]));
						printBeanB2.setChck("w2");
						orderList.add(printBeanB2);
					}
				}
				
				if(c>0){
					PrintBean printBeanC1=new PrintBean();
					printBeanC1.setSID(SID);
					printBeanC1.setGoods("c");
					printBeanC1.setNum(Integer.toString(w1[2]));
					printBeanC1.setChck("w1");
					orderList.add(printBeanC1);
					if(c-w1[2]>0){
						PrintBean printBeanC2=new PrintBean();
						printBeanC2.setSID(SID);
						printBeanC2.setGoods("c");
						printBeanC2.setNum(Integer.toString(c-w1[2]));
						printBeanC2.setChck("w2");
						orderList.add(printBeanC2);
					}
				}
			}else if(w2[0]+w3[0]>=a&&w2[1]+w3[1]>=b&&w2[2]+w3[2]>=c){
				//w2+w3仓库出货可以满足
				System.out.println("w2+w3");
				if(a>0){
					PrintBean printBeanA1=new PrintBean();
					printBeanA1.setSID(SID);
					printBeanA1.setGoods("a");
					printBeanA1.setNum(Integer.toString(w3[0]));
					printBeanA1.setChck("w3");
					orderList.add(printBeanA1);
					if(a-w3[0]>0){
						PrintBean printBeanA2=new PrintBean();
						printBeanA2.setSID(SID);
						printBeanA2.setGoods("a");
						printBeanA2.setNum(Integer.toString(a-w3[0]));
						printBeanA2.setChck("w2");
						orderList.add(printBeanA2);
					}
				}
				
				if(b>0){
					PrintBean printBeanB1=new PrintBean();
					printBeanB1.setSID(SID);
					printBeanB1.setGoods("b");
					printBeanB1.setNum(Integer.toString(w3[1]));
					printBeanB1.setChck("w3");
					orderList.add(printBeanB1);
					if(b-w3[1]>0){
						PrintBean printBeanB2=new PrintBean();
						printBeanB2.setSID(SID);
						printBeanB2.setGoods("b");
						printBeanB2.setNum(Integer.toString(b-w3[1]));
						printBeanB2.setChck("w2");
						orderList.add(printBeanB2);
					}
				}
				
				if(c>0){
					PrintBean printBeanC1=new PrintBean();
					printBeanC1.setSID(SID);
					printBeanC1.setGoods("c");
					printBeanC1.setNum(Integer.toString(w3[2]));
					printBeanC1.setChck("w3");
					orderList.add(printBeanC1);
					if(c-w3[2]>0){
						PrintBean printBeanC2=new PrintBean();
						printBeanC2.setSID(SID);
						printBeanC2.setGoods("c");
						printBeanC2.setNum(Integer.toString(c-w3[2]));
						printBeanC2.setChck("w2");
						orderList.add(printBeanC2);
					}
				}
			}else{
				//需要三个仓库一起出货
				System.out.println("w1+w2+w3");
				System.out.println("abc:"+a+b+c);
				if(a>0){
					if(a<=w1[0]){
						PrintBean printBeanA=new PrintBean();
						printBeanA.setSID(SID);
						printBeanA.setGoods("a");
						printBeanA.setNum(Integer.toString(a));
						printBeanA.setChck("w1");
						orderList.add(printBeanA);
					}else if(a>w1[0]&&a<=w1[0]+w3[0]){
						PrintBean printBeanA1=new PrintBean();
						PrintBean printBeanA2=new PrintBean();
						printBeanA1.setSID(SID);
						printBeanA1.setGoods("a");
						printBeanA2.setSID(SID);
						printBeanA2.setGoods("a");
						System.out.println("13");
						printBeanA1.setNum(Integer.toString(w1[0]));
						printBeanA1.setChck("w1");
						orderList.add(printBeanA1);
						printBeanA2.setNum(Integer.toString(a-w1[0]));
						printBeanA2.setChck("w3");
						orderList.add(printBeanA2);
					}else if(a>w1[0]+w3[0]){
						PrintBean printBeanA1=new PrintBean();
						PrintBean printBeanA2=new PrintBean();
						PrintBean printBeanA3=new PrintBean();
						printBeanA1.setSID(SID);
						printBeanA1.setGoods("a");
						printBeanA2.setSID(SID);
						printBeanA2.setGoods("a");
						printBeanA3.setSID(SID);
						printBeanA3.setGoods("a");
						printBeanA1.setNum(Integer.toString(w1[0]));
						printBeanA1.setChck("w1");
						orderList.add(printBeanA1);
						printBeanA2.setNum(Integer.toString(w3[0]));
						printBeanA2.setChck("w3");
						orderList.add(printBeanA2);
						printBeanA3.setNum(Integer.toString(a-w1[0]-w3[0]));
						printBeanA3.setChck("w2");
						orderList.add(printBeanA3);
					}
				}
				if(b>0){
					if(b<=w1[1]){
						PrintBean printBeanB=new PrintBean();
						printBeanB.setSID(SID);
						printBeanB.setGoods("b");
						printBeanB.setNum(Integer.toString(b));
						printBeanB.setChck("w1");
						orderList.add(printBeanB);
					}else if(b>w1[1]&&b<=w1[1]+w3[1]){
						PrintBean printBeanB1=new PrintBean();
						printBeanB1.setSID(SID);
						printBeanB1.setGoods("b");
						PrintBean printBeanB2=new PrintBean();
						printBeanB2.setSID(SID);
						printBeanB2.setGoods("b");
						printBeanB1.setNum(Integer.toString(w1[1]));
						printBeanB1.setChck("w1");
						orderList.add(printBeanB1);
						printBeanB2.setNum(Integer.toString(b-w1[1]));
						printBeanB2.setChck("w3");
						orderList.add(printBeanB2);
					}else if(b>w1[1]+w3[1]){
						PrintBean printBeanB1=new PrintBean();
						printBeanB1.setSID(SID);
						printBeanB1.setGoods("b");
						PrintBean printBeanB2=new PrintBean();
						printBeanB2.setSID(SID);
						printBeanB2.setGoods("b");
						PrintBean printBeanB3=new PrintBean();
						printBeanB3.setSID(SID);
						printBeanB3.setGoods("b");
						printBeanB1.setNum(Integer.toString(w1[1]));
						printBeanB1.setChck("w1");
						orderList.add(printBeanB1);
						printBeanB2.setNum(Integer.toString(w3[1]));
						printBeanB2.setChck("w3");
						orderList.add(printBeanB2);
						printBeanB3.setNum(Integer.toString(b-w1[1]-w3[1]));
						printBeanB3.setChck("w2");
						orderList.add(printBeanB3);
					}
				}
				if(c>0){
					if(c<=w1[2]){
						PrintBean printBeanC=new PrintBean();
						printBeanC.setSID(SID);
						printBeanC.setGoods("c");
						printBeanC.setNum(Integer.toString(c));
						printBeanC.setChck("w1");
						orderList.add(printBeanC);
					}else if(c>w1[2]&&c<=w1[2]+w3[2]){
						PrintBean printBeanC1=new PrintBean();
						printBeanC1.setSID(SID);
						printBeanC1.setGoods("c");
						PrintBean printBeanC2=new PrintBean();
						printBeanC2.setSID(SID);
						printBeanC2.setGoods("c");
						printBeanC1.setNum(Integer.toString(w1[2]));
						printBeanC1.setChck("w1");
						orderList.add(printBeanC1);
						printBeanC2.setNum(Integer.toString(c-w1[2]));
						printBeanC2.setChck("w3");
						orderList.add(printBeanC2);
					}else if(c>w1[2]+w3[2]){
						PrintBean printBeanC1=new PrintBean();
						printBeanC1.setSID(SID);
						printBeanC1.setGoods("c");
						PrintBean printBeanC2=new PrintBean();
						printBeanC2.setSID(SID);
						printBeanC2.setGoods("c");
						PrintBean printBeanC3=new PrintBean();
						printBeanC3.setSID(SID);
						printBeanC3.setGoods("c");
						printBeanC1.setNum(Integer.toString(w1[2]));
						printBeanC1.setChck("w1");
						orderList.add(printBeanC1);
						printBeanC2.setNum(Integer.toString(w3[2]));
						printBeanC2.setChck("w3");
						orderList.add(printBeanC2);
						printBeanC3.setNum(Integer.toString(c-w1[2]-w3[2]));
						printBeanC3.setChck("w2");
						orderList.add(printBeanC3);
					}
				}
			}
			return orderList;
		}else{
			System.out.println("订单要求数量超过仓库现有数量！");
			return null;
		}
	}
}
