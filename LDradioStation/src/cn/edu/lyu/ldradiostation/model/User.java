package cn.edu.lyu.ldradiostation.model;

public class User {
	/**
	 * "UserId":11,
            "UserName":"songximing",
            "Email":"8990@qq.com",
            "Image":"201503280103138268照片 (4).jpg",
            "Sex":"男",
            "Say":"还记得你说稻香，打算理发的时间快来和客户"
	 * */
	private String UserId;
	private String UserName;
	private String Email;
	private String Image;
	private String Sex;
	private String Say;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getSay() {
		return Say;
	}
	public void setSay(String say) {
		Say = say;
	}
	
}
