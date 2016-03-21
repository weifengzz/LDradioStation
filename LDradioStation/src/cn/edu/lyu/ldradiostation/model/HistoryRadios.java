package cn.edu.lyu.ldradiostation.model;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NotNull;
import com.lidroid.xutils.db.annotation.Table;

@Table(name = "HistoryRadios")
public class HistoryRadios implements Serializable {
	@Id(column = "_id")
	int id;
	@Column(column = "radioId")
	@NotNull
	String radioId;
	@Column(column = "radioTitle")
	@NotNull
	String radioTitle;
	@Column(column = "radioContent")
	@NotNull
	String radioContent;
	@Column(column = "author")
	@NotNull
	String author;
	@Column(column = "RadioAddress")
	@NotNull
	String RadioAddress;
	public String getRadioAddress() {
		return RadioAddress;
	}

	public void setRadioAddress(String radioAddress) {
		RadioAddress = radioAddress;
	}

	public HistoryRadios() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRadioId() {
		return radioId;
	}

	public void setRadioId(String radioId) {
		this.radioId = radioId;
	}

	public String getRadioTitle() {
		return radioTitle;
	}

	public void setRadioTitle(String radioTitle) {
		this.radioTitle = radioTitle;
	}

	public String getRadioContent() {
		return radioContent;
	}

	public void setRadioContent(String radioContent) {
		this.radioContent = radioContent;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
