package cn.edu.lyu.ldradiostation.utils;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cn.edu.lyu.ldradiostation.model.TJRadios;

/**
 * 本类的作用是
 * 
 * @author宋熙明
 * */
public class RadioUtils {
	/*
	 * public static List<Map<String, List<TJRadios>>> getRadioUtils(String
	 * string) { List<Map<String, List<TJRadios>>> list = new
	 * ArrayList<Map<String, List<TJRadios>>>(); try { JSONObject jsonObject =
	 * new JSONObject(string); JSONArray jsonArray =
	 * jsonObject.getJSONArray("data"); List<TJRadios> list1 = new
	 * ArrayList<TJRadios>(); List<TJRadios> list2 = new ArrayList<TJRadios>();
	 * List<TJRadios> list3 = new ArrayList<TJRadios>(); List<TJRadios> list4 =
	 * new ArrayList<TJRadios>(); List<TJRadios> list5 = new
	 * ArrayList<TJRadios>(); List<TJRadios> list6 = new ArrayList<TJRadios>();
	 * List<TJRadios> list7 = new ArrayList<TJRadios>(); List<TJRadios> list8 =
	 * new ArrayList<TJRadios>(); List<TJRadios> list9 = new
	 * ArrayList<TJRadios>(); List<TJRadios> list10 = new ArrayList<TJRadios>();
	 * TJRadios tjRadios = null; for (int i = 0; i < jsonArray.length(); i++) {
	 * tjRadios = new TJRadios();
	 * tjRadios.setCategory(jsonArray.getJSONObject(i).getString( "Category"));
	 * tjRadios.setRadioId(jsonArray.getJSONObject(i).getString( "RadioId"));
	 * tjRadios.setRadioTitle(jsonArray.getJSONObject(i).getString(
	 * "RadioTitle"));
	 * tjRadios.setRadioContent(jsonArray.getJSONObject(i).getString(
	 * "RadioContent"));
	 * tjRadios.setImage(jsonArray.getJSONObject(i).getString("Image"));
	 * tjRadios.setRadioAddress(jsonArray.getJSONObject(i).getString( "Radio"));
	 * tjRadios.setDeptName(jsonArray.getJSONObject(i).getString( "DeptName"));
	 * tjRadios.setDate(jsonArray.getJSONObject(i).getString("Date"));
	 * tjRadios.setAuthor(jsonArray.getJSONObject(i).getString( "Author"));
	 * tjRadios.setProgramId(jsonArray.getJSONObject(i).getString(
	 * "ProgramId"));
	 * 
	 * if ("书影随行".equals(jsonArray.getJSONObject(i).getString( "Category"))) {
	 * list1.add(tjRadios);
	 * 
	 * } else if ("文苑撷英".equals(jsonArray.getJSONObject(i).getString(
	 * "Category"))) { list2.add(tjRadios);
	 * 
	 * } else if ("漫游记".equals(jsonArray.getJSONObject(i).getString(
	 * "Category"))) { list3.add(tjRadios);
	 * 
	 * } else if ("耳朵去旅行".equals(jsonArray.getJSONObject(i).getString(
	 * "Category"))) { list4.add(tjRadios);
	 * 
	 * } else if ("巅峰荣耀".equals(jsonArray.getJSONObject(i).getString(
	 * "Category"))) { list5.add(tjRadios);
	 * 
	 * } else if ("天堂电影院".equals(jsonArray.getJSONObject(i).getString(
	 * "Category"))) { list6.add(tjRadios);
	 * 
	 * } else if ("杂货铺子".equals(jsonArray.getJSONObject(i).getString(
	 * "Category"))) { list7.add(tjRadios);
	 * 
	 * } else if ("灵犀".equals(jsonArray.getJSONObject(i).getString(
	 * "Category"))) { list8.add(tjRadios);
	 * 
	 * } else if ("城市稻草人".equals(jsonArray.getJSONObject(i).getString(
	 * "Category"))) { list9.add(tjRadios);
	 * 
	 * } else if ("流年爱无边".equals(jsonArray.getJSONObject(i).getString(
	 * "Category"))) { list10.add(tjRadios); }
	 * 
	 * } Map<String, List<TJRadios>> map1 = new HashMap<String,
	 * List<TJRadios>>(); Map<String, List<TJRadios>> map2 = new HashMap<String,
	 * List<TJRadios>>(); Map<String, List<TJRadios>> map3 = new HashMap<String,
	 * List<TJRadios>>(); Map<String, List<TJRadios>> map4 = new HashMap<String,
	 * List<TJRadios>>(); Map<String, List<TJRadios>> map5 = new HashMap<String,
	 * List<TJRadios>>(); Map<String, List<TJRadios>> map6 = new HashMap<String,
	 * List<TJRadios>>(); Map<String, List<TJRadios>> map7 = new HashMap<String,
	 * List<TJRadios>>(); Map<String, List<TJRadios>> map8 = new HashMap<String,
	 * List<TJRadios>>(); Map<String, List<TJRadios>> map9 = new HashMap<String,
	 * List<TJRadios>>(); Map<String, List<TJRadios>> map10 = new
	 * HashMap<String, List<TJRadios>>(); map1.put("sysx", list1);
	 * map2.put("wyjy", list2); map3.put("myj", list3); map4.put("edqlx",
	 * list4); map5.put("dfry", list5); map6.put("ttdyy", list6);
	 * map7.put("zhpz", list7); map8.put("lx", list8); map9.put("csdcr", list9);
	 * map10.put("lnawb", list10); list.add(map1); list.add(map2);
	 * list.add(map3); list.add(map4); list.add(map5); list.add(map6);
	 * list.add(map7); list.add(map8); list.add(map9); list.add(map10); } catch
	 * (JSONException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return list;
	 * 
	 * }
	 */
	public static List<List<TJRadios>> getRadioUtils(String string) {
		List<List<TJRadios>> list = new ArrayList<List<TJRadios>>();
		try {
			JSONObject jsonObject = new JSONObject(string);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			List<TJRadios> list1 = new ArrayList<TJRadios>();
			List<TJRadios> list2 = new ArrayList<TJRadios>();
			List<TJRadios> list3 = new ArrayList<TJRadios>();
			List<TJRadios> list4 = new ArrayList<TJRadios>();
			List<TJRadios> list5 = new ArrayList<TJRadios>();
			List<TJRadios> list6 = new ArrayList<TJRadios>();
			List<TJRadios> list7 = new ArrayList<TJRadios>();
			List<TJRadios> list8 = new ArrayList<TJRadios>();
			List<TJRadios> list9 = new ArrayList<TJRadios>();
			List<TJRadios> list10 = new ArrayList<TJRadios>();
			TJRadios tjRadios = null;
			for (int i = 0; i < jsonArray.length(); i++) {
				tjRadios = new TJRadios();
				tjRadios.setCategory(jsonArray.getJSONObject(i).getString(
						"Category"));
				tjRadios.setRadioId(jsonArray.getJSONObject(i).getString(
						"RadioId"));
				tjRadios.setRadioTitle(jsonArray.getJSONObject(i).getString(
						"RadioTitle"));
				tjRadios.setRadioContent(jsonArray.getJSONObject(i).getString(
						"RadioContent"));
				tjRadios.setImage(jsonArray.getJSONObject(i).getString("Image"));
				tjRadios.setRadioAddress(jsonArray.getJSONObject(i).getString(
						"Radio"));
				tjRadios.setDeptName(jsonArray.getJSONObject(i).getString(
						"DeptName"));
				tjRadios.setDate(jsonArray.getJSONObject(i).getString("Date"));
				tjRadios.setAuthor(jsonArray.getJSONObject(i).getString(
						"Author"));
				tjRadios.setProgramId(jsonArray.getJSONObject(i).getString(
						"ProgramId"));

				if ("书影随行".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list1.add(tjRadios);

				} else if ("文苑撷英".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list2.add(tjRadios);

				} else if ("漫游记".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list3.add(tjRadios);

				} else if ("耳朵去旅行".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list4.add(tjRadios);

				} else if ("巅峰荣耀".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list5.add(tjRadios);

				} else if ("天堂电影院".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list6.add(tjRadios);

				} else if ("杂货铺子".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list7.add(tjRadios);

				} else if ("灵犀".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list8.add(tjRadios);

				} else if ("城市稻草人".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list9.add(tjRadios);

				} else if ("流年爱无边".equals(jsonArray.getJSONObject(i).getString(
						"Category"))) {
					list10.add(tjRadios);
				}

			}

			list.add(list1);
			list.add(list2);
			list.add(list3);
			list.add(list4);
			list.add(list5);
			list.add(list6);
			list.add(list7);
			list.add(list8);
			list.add(list9);
			list.add(list10);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}
}
