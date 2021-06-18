package model;

import java.util.LinkedList;

public class UserOrder {
	private String userName;
	private String shopName;
	private String orderTime;
	private String orderFood; //默认没有菜品订单,order变为String，格式为"菜品信息:菜品: ,价格: ,菜品: ,价格: .... "
	public UserOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserOrder(String userName, String shopName, String orderTime, String orderFood) {
		super();
		this.userName = userName;
		this.shopName = shopName;
		this.orderTime = orderTime;
		this.orderFood = orderFood;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderFood() {
		return orderFood;
	}
	public void setOrderFood(String orderFood) {
		this.orderFood = orderFood;
	}

	
	
	
}
