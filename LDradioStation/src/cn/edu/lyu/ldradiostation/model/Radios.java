package cn.edu.lyu.ldradiostation.model;

import java.io.Serializable;

public class Radios implements Serializable {

	private String RadioId;
	private String RadioTitle;
	private String RadioContent;
	private String RadioAddress;
	private String Image;
	private String DeptName;
	private String Date;
	private String Date1;
	private String Author;
	private String ProgramId;
	private String category;
	private String Detail;
	private String Dept;
	private String ListenNum;
	private String Id;
	private String programImg;

	public String getProgramImg() {
		return programImg;
	}

	public void setProgramImg(String programImg) {
		this.programImg = programImg;
	}

	public String getDate1() {
		return Date1;
	}

	public void setDate1(String date1) {
		Date1 = date1;
	}

	public String getRadioId() {
		return RadioId;
	}

	public void setRadioId(String radioId) {
		RadioId = radioId;
	}

	public String getRadioTitle() {
		return RadioTitle;
	}

	public void setRadioTitle(String radioTitle) {
		RadioTitle = radioTitle;
	}

	public String getRadioContent() {
		return RadioContent;
	}

	public void setRadioContent(String radioContent) {
		RadioContent = radioContent;
	}

	public String getRadioAddress() {
		return RadioAddress;
	}

	public void setRadioAddress(String radioAddress) {
		RadioAddress = radioAddress;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getDeptName() {
		return DeptName;
	}

	public void setDeptName(String deptName) {
		DeptName = deptName;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getProgramId() {
		return ProgramId;
	}

	public void setProgramId(String programId) {
		ProgramId = programId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getDept() {
		return Dept;
	}

	public void setDept(String dept) {
		Dept = dept;
	}

	public String getListenNum() {
		return ListenNum;
	}

	public void setListenNum(String listenNum) {
		ListenNum = listenNum;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

}
