package cn.edu.lyu.ldradiostation.model;

import java.io.Serializable;

public class TJRadios implements Serializable {
	private String RadioId;
	private String RadioTitle;
	private String RadioContent;
	private String RadioAddress;
	private String Image;
	private String DeptName;
	private String Date;
	private String Author;
	private String ProgramId;
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

}
