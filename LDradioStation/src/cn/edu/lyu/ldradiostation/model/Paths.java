package cn.edu.lyu.ldradiostation.model;

import android.text.StaticLayout;

public class Paths {
	// public static final String IP="192.168.0.123:1111";//IP
	public static final String IP = "10.0.153.236:1111";// IP
	public static final String TOP3_PATH = "http://" + IP
			+ "/WS/WSRadio.asmx/GetTop3Radio?num=0";// 中文推荐
	public static final String USER_LOGIN = "http://" + IP
			+ "/WS/UserMessage.asmx/UserLogin";// 用户登录
	public static final String USER_DETAIL = "http://" + IP
			+ "/WS/UserMessage.asmx/UserDetail";// 用户的详细信息细
	public static final String USER_THREE_REGISTER = "http://" + IP
			+ "/WS/UserMessage.asmx/User3Register";// 用户的第三方登录
	public static final String USER_HEAD_IMG = "http://" + IP
			+ "/Source/Users/userimage";// 用户的头像
	public static final String TJ_HEAD_PATH = "http://" + IP
			+ "/WS/WSRadio.asmx/GetTJ";// 推荐
	public static final String TJ_HEAD_IMGS = "http://" + IP + "/Source/tj/";// 推荐头部的图片
	public static final String RADIO_PATH = "http://" + IP + "/Source/Radios/";// 歌曲播放地址
	public static final String CATEGROY_ITEMS_PATH_STRING = "http://" + IP
			+ "/WS/WSRadio.asmx/GetRadioByCategory";// 根据分类得到radio
	public static final String RADIO_IMG_PATH = "http://" + IP
			+ "/Source/RadioImages/";
	public static final String PROGRAM_IMG_PATH = "http://" + IP
			+ "/Source/ProgramImgs/";
	public static final String ADD_LISTEN_NUMBER = "http://" + IP
			+ "/WS/WSRadio.asmx/UpdateListenNum";
	public static final String PROGRAM_CATEGORY_BY_ID="http://" + IP
			+ "/WS/WSRadio.asmx/GetRadioByProgramId";
}
