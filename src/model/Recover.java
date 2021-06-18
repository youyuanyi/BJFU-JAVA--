package model;

/**
 * 恢复类，用于恢复商家food_num
 * @author HP
 *
 */
public class Recover {
	private String shopName;
	private String orderInfo;
	public Recover() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Recover(String shopName, String orderInfo) {
		super();
		this.shopName = shopName;
		this.orderInfo = orderInfo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	

}
