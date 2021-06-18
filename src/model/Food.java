package model;

/**
 * 椋熺墿绫诲疄浣�
 */
public class Food {
    private String food_id;//food_id
    private String food_name;//food_name
    private String food_price;//food_price
    private Integer food_nums;//默认10份

    public Food(String food_id, String food_name, String food_price,Integer food_nums) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_nums=food_nums; //默认食物数量为10份
    }

    public Food() {
		// TODO Auto-generated constructor stub
	}

	public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public Integer getFood_nums() {
        return food_nums;
    }

    public void setFood_nums(Integer food_nums) {
        this.food_nums = food_nums;
    }
}
