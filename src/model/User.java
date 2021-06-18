package model;

/**
 * 消费者实体
 * @author HP
 *
 */
public class User {
    private String name;  //账号
    private String password; //密码
    private String phone;//联系方式
    private Integer privilege=0;//权限，消费者为0

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String name, String password, String phone,Integer privilege) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.privilege=privilege;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", phone=" + phone + ", privilege=" + privilege + "]";
	}
    
    

}
