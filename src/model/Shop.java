package model;
import model.Food;
import java.util.LinkedList;
/**
 * 商家实体
 */
public class Shop {
    private String shopType;
    private String id;
    private String name;
    private String password;
    private Double avgScore;
    private Double aveprice;
    private String address;
    private String phone;
    private LinkedList<Food> food;
    private LinkedList<String> comment;
    private Integer privilege=1;
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shop(String shopType, String id, String name, String password, Double avgScore, Double aveprice,
			String address, String phone, LinkedList<Food> food, LinkedList<String> comment) {
		super();
		this.shopType = shopType;
		this.id = id;
		this.name = name;
		this.password = password;
		this.avgScore = avgScore;
		this.aveprice = aveprice;
		this.address = address;
		this.phone = phone;
		this.food = food;
		this.comment = comment;
	}
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}
	public Double getAveprice() {
		return aveprice;
	}
	public void setAveprice(Double aveprice) {
		this.aveprice = aveprice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LinkedList<Food> getFood() {
		return food;
	}
	
	public void setFood(LinkedList<Food> food) {
		this.food = food;
	}
	public LinkedList<String> getComment() {
		return comment;
	}
	public void setComment(LinkedList<String> comment) {
		this.comment = comment;
	}
	
    



   
}
