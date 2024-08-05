package com.xyt.project.youbeisoft.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.xyt.project.youbeisoft.base.SqlUtil;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ActionUtils {

	public static final String RECORD_SEPARATOR = "#%@!&";

	public static final String KEY_VALUE_SEPARATOR = "##";

	public static final String DQMS_SEPARATOR = "\"";

	public static final String sign = "abcdefghijklmnopqrstuvwxyz123456";

	public static final String nonceStr = "123456abcdefghijklmnopqrstuvwxyz";

	private static ActionUtils actionUtils;

	@Autowired
	SqlUtil sqlUtil;

	@PostConstruct
	public void init(){
		actionUtils = this;
	}

	/**
	 * 参数列表中参数个数是否符合
	 *
	 * //@param params
	 * //@param count
	 * //@throws ApplicationException
	 */
//	public static void assertParams(Object[] params, int count)
//			throws ApplicationException {
//
//		if (params != null && params.length >= count) {
//		} else {
//			throw new ApplicationException("参数列表中参数数量错误！");
//		}
//	}

	/**
	 * 将 map list 转化为二维数组
	 *
	 * //@param list
	 * //@return
	 */
	public String[][] mapList2Array(List list) {
		String[][] result = null;
		int size = list == null ? 0 : list.size();
		result = new String[size][];
		Map map = null;
		for (int i = 0; i < size; i++) {
			map = (Map) list.get(i);
			result[i] = new String[map.size()];
			map.values().toArray(result[i]);

			result[i] = actionUtils.toStringArray(result[i]);
		}
		return result;
	}

	/**
	 * 将 map list 转化为 一维字符串数组
	 *
	 * //@param list
	 * //@param keyValueSeparator --
	 *            分隔符
	 * //@param flag --
	 *            是否做分隔处理
	 * //@return
	 */
	public String[] mapListArray(List list, String keyValueSeparator,
                                 boolean flag) {
		String[] result = null;
		int length = list == null ? 0 : list.size();
		result = new String[length];

		if (length != 0) {
			Map map = null;
			for (int i = 0; i < length; i++) {
				map = (Map) list.get(i);
				String keyValueSeparator1 = "";
				if (flag) {// 设置 map 的 value 分隔
					keyValueSeparator1 = keyValueSeparator;
				}

				Iterator it = map.keySet().iterator();
				String key = null;
				String str = "";
				while (it.hasNext()) {
					key = (String) it.next();
					map.put(key, key + keyValueSeparator1 + map.get(key));
					if (str.length() > 0){//增加字段间分隔符
						str += actionUtils.RECORD_SEPARATOR;
					}
					str += map.get(key);
				}
				result[i] = str;
			}
		}
		return result;
	}

//原实现方法，不是把一条记录中的字段拼成一个串
//	public String[] mapListArray(List list, String keyValueSeparator,
//			boolean flag) {
//		String[] result = null;
//		int length = list == null ? 0 : list.size();
//		List resultList = new ArrayList();
//		if (length != 0) {
//			Map map = null;
//			for (int i = 0; i < length; i++) {
//				map = (Map) list.get(i);
//
//				if (flag) {
//					// 设置 map 的 value 分隔
//					Iterator it = map.keySet().iterator();
//					String key = null;
//					while (it.hasNext()) {
//						key = (String) it.next();
//						map.put(key, key + keyValueSeparator + map.get(key));
//					}
//					// end
//				}
//
//				resultList.addAll(map.values());
//				if (i != length - 1) {
//					resultList.add(actionUtils.RECORD_SEPARATOR);
//				}
//			}
//		}
//		result = new String[resultList.size()];
//		resultList.toArray(result);
//		return result;
//	}

	/**
	 * 将 map list 转化为 一维字符串数组
	 *
	 * //@param list
	 * //@param keyValueSeparator --
	 *            分隔符
	 * //@return
	 */
	public String[] mapListArray(List list, String keyValueSeparator) {
		return actionUtils.mapListArray(list, actionUtils.KEY_VALUE_SEPARATOR,
				true);
	}

	/**
	 * 将 map list 转化为 一维字符串数组
	 *
	 * //@param list
	 * //@param flag --
	 *            是否分隔
	 * //@return
	 */
	public String[] mapListArray(List list, boolean flag) {
		if (flag) {
			return actionUtils.mapListArray(list,
					actionUtils.KEY_VALUE_SEPARATOR);
		} else {
			return actionUtils.mapListArray(list,
					actionUtils.KEY_VALUE_SEPARATOR, false);
		}
	}

	/**
	 * 将 map list 转化为 一维字符串数组
	 *
	 * //@param list
	 * //@return
	 */
	public String[] mapListArray(List list) {
		return actionUtils.mapListArray(list, actionUtils.KEY_VALUE_SEPARATOR);
	}

	/**
	 * 将 list 的数组 转化为 二维数组
	 *
	 * //@param list
	 * //@return
	 */
	public String[][] list2Array(List list) {
		String[][] result = null;
		int size = list == null ? 0 : list.size();
		result = new String[size][];
		for (int i = 0; i < size; i++) {
			result[i] = actionUtils.toStringArray((Object[]) list.get(i));
		}
		return result;
	}

	/**
	 * 将 list 的二维数组 转化为 一维字符串数组
	 *
	 * //@param list
	 * //@return
	 */
	public String[] list2Array1(List list, String[] field, String datePattern, String keyValueSeparator, String recordValueSeparator) {
		String[] result = null;
		Object[] object = null;
		int size = list == null ? 0 : list.size();
		result = new String[size];

		for (int i = 0; i < size; i++) {
			if (list.get(i) instanceof Object[]){
				object = (Object[]) list.get(i);
			}
			else {
				object = new Object[1];
				object[0] = list.get(i);
			}
			String str = "";
			for (int j = 0; j < object.length; j++) {
				if (str.length() > 0){//增加字段间分隔符
					str += recordValueSeparator;
				}
				if (object[j] instanceof Date){//Date 类型转 String
					SimpleDateFormat sdf = new SimpleDateFormat();
					sdf.applyPattern(datePattern);
					str += field[j] + keyValueSeparator +sdf.format(object[j]);
				}
				else{
					if (object[j] == null){
						str += field[j] + keyValueSeparator + "";
					}
					else{
						str += field[j] + keyValueSeparator + object[j].toString();
					}
				}
			}
			result[i] = str;
		}
		return result;
	}

	public String[] list2Array1(List list, String[] field) {
		return actionUtils.list2Array1(list, field, "yyyyMMddHHmmss");
	}

	public String[] list2Array1(List list, String[] field, String datePattern) {
		return actionUtils.list2Array1(list, field, datePattern,actionUtils.KEY_VALUE_SEPARATOR,actionUtils.RECORD_SEPARATOR);
	}


	/**
	 * 将 数组 list 转化为 一维字符串数组
	 *
	 * //@param list
	 * //@return
	 */
	public String[] listArray(List list) {
		String[] result = null;
		int size = list == null ? 0 : list.size();
		List resultList = new ArrayList();
		List tmpList = null;
		for (int i = 0; i < size; i++) {
			tmpList = Arrays.asList((Object[]) list.get(i));
			resultList.addAll(tmpList);
			if (i != size - 1) {
				resultList.add(actionUtils.RECORD_SEPARATOR);
			}
		}
		result = new String[resultList.size()];
		resultList.toArray(result);
		return result;
	}

	/**
	 * 转化为 String 数组
	 *
	 * //@param orig
	 * //@return
	 */
	public String[] toStringArray(Object[] orig) {
		return actionUtils.toStringArray(orig, "yyyyMMddHHmmss");
	}

	/**
	 * 转化为 String 数组
	 *
	 * //@param orig
	 * //@param datePattern
	 * //@return
	 */
	public String[] toStringArray(Object[] orig, String datePattern) {
		String[] result = null;
		int length = orig == null ? 0 : orig.length;
		if (length != 0) {
			result = new String[length];
			for (int i = 0; i < length && orig[i] != null; i++) {
				if (BigDecimal.class == orig[i].getClass()) {
					if (orig[i] != null) {
						result[i] = orig[i].toString();
					}
				} else if (Date.class == orig[i].getClass()) {
					SimpleDateFormat sdf = new SimpleDateFormat();
					sdf.applyPattern(datePattern);
					result[i] = sdf.format(orig[i]);
				} else {
					result[i] = orig[i] == null ? null : orig[i].toString();
				}
			}
		}
		return result;
	}

	/**
	 * 将查询的字段名称 转换到 String 数组
	 *
	 * //@param orig
	 * //@param datePattern
	 * //@return
	 */
	public String[] FieldtoStringArray(String hql) {
		String field = "";
		field = hql.substring(0,hql.indexOf("from"));
		field = field.substring(field.indexOf(" "));
		field = field.replaceAll(" ", "");
		//field = field.replaceAll("t.", "");
		field = field.toUpperCase();
		String sql[] = field.split(",");
		return sql;
	}

	/**
	 * 将查询的字段名称 转换到 String 数组（不自动转换大写）
	 *
	 * //@param orig
	 * //@param datePattern
	 * //@return
	 */
	public String[] FieldtoStringArrayNOUpper(String hql) {
		String field = "";
		field = hql.substring(0,hql.indexOf("from"));
		field = field.substring(field.indexOf(" "));
		field = field.replaceAll(" ", "");
		//field = field.replaceAll("t.", "");
		//field = field.toUpperCase();
		String sql[] = field.split(",");
		return sql;
	}

	/**
	 * 记录字符串 转换到 String 数组
	 *
	 * //@param orig
	 * //@param datePattern
	 * //@return
	 */
	public String[] StringtoStringArray(String str) {
		String[] StringArray = str.split(actionUtils.RECORD_SEPARATOR);
		return StringArray;
	}

	/**
	 * 记录字符串 转换到 Map
	 *
	 * //@param orig
	 * //@param datePattern
	 * //@return
	 */
	public static Map StringtoMap(String str) {
		Map map = new HashMap();
		String[] StringArray = str.split(actionUtils.RECORD_SEPARATOR);
		for (int i = 0;i < StringArray.length;i++){
			String[] StringArray2 = StringArray[i].split(actionUtils.KEY_VALUE_SEPARATOR);
			if (StringArray2.length < 2){
				map.put(StringArray2[0], "");
			}
			else{
				map.put(StringArray2[0], StringArray2[1]);
			}
		}
		return map;
	}

	/**
	 * 从记录字符串得到指定字段的值
	 *
	 * //@param orig
	 * //@param datePattern
	 * //@return
	 */
	public String GetValuefromStr(String str, String column_name, String keyValueSeparator, String recordValueSeparator) {
		String value = "";
		String[] StringArray = str.split(recordValueSeparator);
		for (int i = 0;i < StringArray.length;i++){
			String[] StringArray2 = StringArray[i].split(keyValueSeparator);
			if (StringArray2.length < 2){
				if (column_name.equals(StringArray2[0])){
					value = "";
					break;
				}
			}
			else{
				if (column_name.equals(StringArray2[0])){
					value = StringArray2[1];
					break;
				}
			}
		}
		return value;
	}

	public String GetValuefromStr(String str, String column_name) {
		return actionUtils.GetValuefromStr(str,column_name,actionUtils.KEY_VALUE_SEPARATOR,actionUtils.RECORD_SEPARATOR);
	}

	//将记录Object[]转换成"##"分隔的String
	public String ObjecttoString(Object[] obj) {
		String str = "";
		int length = obj.length;
		if (length != 0) {
			for (int i = 0; i < length; i++) {
				if (str.length() > 0) {
					str += actionUtils.KEY_VALUE_SEPARATOR;
				}
				if (obj[i] == null) {
					str += "null";
				} else{
					if (BigDecimal.class == obj[i].getClass()) {
						str += obj[i].toString();

					} else if (Date.class == obj[i].getClass()) {
						SimpleDateFormat sdf = new SimpleDateFormat();
						sdf.applyPattern("yyyyMMddHHmmss");
						str += sdf.format(obj[i]);

					} else {
						if (obj[i] == "") {
							str += "null";
						} else {
							str += obj[i].toString();
						}
					}
				}
			}
		}
		return str;
	}

	//将记录list转换成XML格式的String
	public String ListtoXML(List list, String sql) {
		Object[] obj = null;
		String column[] = actionUtils.FieldtoStringArray(sql);

		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version = '1.0' encoding = 'GB2312'?>");
		xml.append("<ROWSET>");

		int size = list == null ? 0 : list.size();
		if (size > 0){
			for (int i = 0; i < size; i++) {
				obj = (Object[]) list.get(i);
				int length = obj.length;
				if (length != 0) {
					xml.append("<ROW num='"+(i+1)+"'>");
					for (int j = 0; j < length; j++) {
						if (obj[j] == null){
							xml.append("<"+column[j].toUpperCase()+">");
							xml.append("</"+column[j].toUpperCase()+">");
						}
						else{
							if (BigDecimal.class == obj[j].getClass()) {
								xml.append("<"+column[j].toUpperCase()+">");
								xml.append(obj[j].toString());
								xml.append("</"+column[j].toUpperCase()+">");

							} else if (Date.class == obj[j].getClass()) {
								SimpleDateFormat sdf = new SimpleDateFormat();
								sdf.applyPattern("yyyyMMddHHmmss");
								xml.append("<"+column[j].toUpperCase()+">");
								xml.append(sdf.format(obj[j]));
								xml.append("</"+column[j].toUpperCase()+">");

							} else {
								if (obj[j] == ""){
									xml.append("<"+column[j].toUpperCase()+">");
									xml.append("</"+column[j].toUpperCase()+">");
								}
								else{
									xml.append("<"+column[j].toUpperCase()+">");
									xml.append(obj[j].toString());
									xml.append("</"+column[j].toUpperCase()+">");
								}
							}
						}
					}
					xml.append("</ROW>");
				}
			}
		}
		xml.append("</ROWSET>");
		//System.out.println(xml.toString());
		return xml.toString();
	}

//	将记录list转换成JSON格式的String（面向用友基金管控平台）
	public String ListtoJSON2YY(List list, String sql, String UID, String SEND_DATE) {
		Object[] obj = null;
		String column[] = actionUtils.FieldtoStringArray(sql);

		StringBuffer jsonstr = new StringBuffer();
		jsonstr.append("{");

		int size = list == null ? 0 : list.size();
		if (size > 0){
			jsonstr.append("\"head\":{"+DQMS_SEPARATOR+"version"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"1.0"+DQMS_SEPARATOR+
					","+DQMS_SEPARATOR+"UID"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+UID+DQMS_SEPARATOR+
					","+DQMS_SEPARATOR+"SEND_DATE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+SEND_DATE+DQMS_SEPARATOR+"}");
			jsonstr.append(",");
			for (int i = 0; i < size; i++) {
				obj = (Object[]) list.get(i);
				int length = obj.length;
				if (length != 0) {
					//jsonstr.append("<ROW num='"+(i+1)+"'>");
					jsonstr.append("\"body\":{");
					for (int j = 0; j < length; j++) {
						if (obj[j] == null){
							jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
							if (j < length - 1) {jsonstr.append(",");}
						}
						else{
							if (BigDecimal.class == obj[j].getClass()) {
								jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else if (Date.class == obj[j].getClass()) {
								SimpleDateFormat sdf = new SimpleDateFormat();
								sdf.applyPattern("yyyyMMddHHmmss");
								jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+sdf.format(obj[j])+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else {
								if (obj[j] == ""){
									jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
								else{
									jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
							}
						}
					}
					//jsonstr.append("</ROW>");
					jsonstr.append("}");
					if (i < size - 1) {jsonstr.append(",");}
				}
			}
		}
		else{
			jsonstr.append("\"head\":{"+DQMS_SEPARATOR+"version"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"1.0"+DQMS_SEPARATOR+
					","+DQMS_SEPARATOR+"UID"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+UID+DQMS_SEPARATOR+
					","+DQMS_SEPARATOR+"SEND_DATE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+SEND_DATE+DQMS_SEPARATOR+"}");
			jsonstr.append(",");
			jsonstr.append("\"body\":{}");
		}
		jsonstr.append("}");
		//System.out.println(jsonstr.toString());
		return jsonstr.toString();
	}

	//将list.get(i)转换成JSON格式的String（面向用友基金管控平台）
	public String ListtoJSON2YYObj(Object objinput, String sql, String UID, String SEND_DATE) {
		Object[] obj = null;
		String column[] = actionUtils.FieldtoStringArray(sql);

		StringBuffer jsonstr = new StringBuffer();
		jsonstr.append("{");

//		int size = list == null ? 0 : list.size();
//		if (size > 0){
			jsonstr.append("\"head\":{"+DQMS_SEPARATOR+"version"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"1.0"+DQMS_SEPARATOR+
					","+DQMS_SEPARATOR+"UID"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+UID+DQMS_SEPARATOR+
					","+DQMS_SEPARATOR+"SEND_DATE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+SEND_DATE+DQMS_SEPARATOR+"}");
			jsonstr.append(",");
			//for (int i = 0; i < size; i++) {
				obj = (Object[]) objinput;
				int length = obj.length;
				if (length != 0) {
					//jsonstr.append("<ROW num='"+(i+1)+"'>");
					jsonstr.append("\"body\":{");
					for (int j = 0; j < length; j++) {
						if (obj[j] == null){
							jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
							if (j < length - 1) {jsonstr.append(",");}
						}
						else{
							if (BigDecimal.class == obj[j].getClass()) {
								jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else if (Date.class == obj[j].getClass()) {
								SimpleDateFormat sdf = new SimpleDateFormat();
								sdf.applyPattern("yyyyMMddHHmmss");
								jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+sdf.format(obj[j])+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else {
								if (obj[j] == ""){
									jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
								else{
									jsonstr.append(DQMS_SEPARATOR+column[j].toUpperCase()+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
							}
						}
					}
					//jsonstr.append("</ROW>");
					jsonstr.append("}");
					//if (i < size - 1) {jsonstr.append(",");}
				}
//			}
//		}
//		else{
//			jsonstr.append("\"head\":{"+DQMS_SEPARATOR+"version"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"1.0"+DQMS_SEPARATOR+
//					","+DQMS_SEPARATOR+"UID"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+UID+DQMS_SEPARATOR+
//					","+DQMS_SEPARATOR+"SEND_DATE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+SEND_DATE+DQMS_SEPARATOR+"}");
//			jsonstr.append(",");
//			jsonstr.append("\"body\":{}");
//		}
		jsonstr.append("}");
		//System.out.println(jsonstr.toString());
		return jsonstr.toString();
	}

	//将Object[2]存储过程返回信息转换成JSON（面向用友基金管控平台）
	public String[] Obj2JSON2YY(Object[] obj, String UID, String SEND_DATE) {
		String[] result = new String[1];
		StringBuffer jsonstr = new StringBuffer();
		int length = obj.length;
		if (length != 0&&length == 2) {
			jsonstr.append("{");
			jsonstr.append("\"head\":{"+DQMS_SEPARATOR+"version"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"1.0"+DQMS_SEPARATOR+
					","+DQMS_SEPARATOR+"UID"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+UID+DQMS_SEPARATOR+
					","+DQMS_SEPARATOR+"SEND_DATE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+SEND_DATE+DQMS_SEPARATOR+"}");
			jsonstr.append(",");
			jsonstr.append("\"body\":{");
			jsonstr.append(DQMS_SEPARATOR+"CODE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[0].toString()+DQMS_SEPARATOR);
			jsonstr.append(",");
			jsonstr.append(DQMS_SEPARATOR+"ERROR_MSG"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[1].toString()+DQMS_SEPARATOR);
			jsonstr.append("}");
			jsonstr.append("}");
		}

		result[0] = jsonstr.toString();
		jsonstr.setLength(0);//用完清空

		return result;
	}

	//将记录list转换成JSON格式的String
	public String ListtoJSON(List list, String sql) {
		Object[] obj = null;
		String column[] = actionUtils.FieldtoStringArrayNOUpper(sql);

		StringBuffer jsonstr = new StringBuffer();
		jsonstr.append("[");

		int size = list == null ? 0 : list.size();
		if (size > 0){
			jsonstr.append("{"+DQMS_SEPARATOR+"ROWNUM"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+(size)+DQMS_SEPARATOR+"}");
			jsonstr.append(",");
			for (int i = 0; i < size; i++) {
				obj = (Object[]) list.get(i);
				int length = obj.length;
				if (length != 0) {
					//jsonstr.append("<ROW num='"+(i+1)+"'>");
					jsonstr.append("{");
					for (int j = 0; j < length; j++) {
						if (obj[j] == null){
							jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
							if (j < length - 1) {jsonstr.append(",");}
						}
						else{
							if (BigDecimal.class == obj[j].getClass()) {
								jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else if (Date.class == obj[j].getClass()) {
								SimpleDateFormat sdf = new SimpleDateFormat();
								sdf.applyPattern("yyyyMMddHHmmss");
								jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+sdf.format(obj[j])+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else {
								if (obj[j] == ""){
									jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
								else{
									jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
							}
						}
					}
					//jsonstr.append("</ROW>");
					jsonstr.append("}");
					if (i < size - 1) {jsonstr.append(",");}
				}
			}
		}
		else{
			jsonstr.append("{"+DQMS_SEPARATOR+"ROWNUM"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+0+DQMS_SEPARATOR+"}");
		}
		jsonstr.append("]");
		//System.out.println(jsonstr.toString());
		return jsonstr.toString();
	}

	//将Object[2]存储过程返回信息转换成JSON
	public String[] Obj2JSON(Object[] obj) {
		String[] result = new String[1];
		StringBuffer jsonstr = new StringBuffer();
		int length = obj.length;
		if (length != 0&&length == 2) {
			jsonstr.append("[");
			jsonstr.append("{");
			jsonstr.append(DQMS_SEPARATOR+"APPCODE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[0].toString()+DQMS_SEPARATOR);
			jsonstr.append(",");
			jsonstr.append(DQMS_SEPARATOR+"APPMSG"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[1].toString()+DQMS_SEPARATOR);
			jsonstr.append("}");
			jsonstr.append("]");
		}

		result[0] = jsonstr.toString();
		jsonstr.setLength(0);//用完清空

		return result;
	}

	//将记录list转换成JSON格式的String(RESTful接口输出专用，带[])
	public String ListtoJSONRest(List list, String sql) {
		Object[] obj = null;
		String column[] = actionUtils.FieldtoStringArrayNOUpper(sql);

		StringBuffer jsonstr = new StringBuffer();
		jsonstr.append("[");

		int size = list == null ? 0 : list.size();
		if (size > 0){
			//jsonstr.append("{"+DQMS_SEPARATOR+"ROWNUM"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+(size)+DQMS_SEPARATOR+"}");
			//jsonstr.append(",");
			for (int i = 0; i < size; i++) {
				obj = (Object[]) list.get(i);
				int length = obj.length;

				if (length != 0) {
					//jsonstr.append("<ROW num='"+(i+1)+"'>");
					jsonstr.append("{");
					for (int j = 0; j < length; j++) {
						if (obj[j] == null){
							jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
							if (j < length - 1) {jsonstr.append(",");}
						}
						else{
							if (BigDecimal.class == obj[j].getClass()) {
								jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else if (Date.class == obj[j].getClass()) {
								SimpleDateFormat sdf = new SimpleDateFormat();
								sdf.applyPattern("yyyyMMddHHmmss");
								jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+sdf.format(obj[j])+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else {
								if (obj[j] == ""){
									jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
								else{
									jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
							}
						}
					}
					//jsonstr.append("</ROW>");
					jsonstr.append("}");
					if (i < size - 1) {jsonstr.append(",");}
				}
			}
		}
		else{
			//jsonstr.append("{"+DQMS_SEPARATOR+"ROWNUM"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+0+DQMS_SEPARATOR+"}");
		}
		jsonstr.append("]");
		//System.out.println(jsonstr.toString());
		return jsonstr.toString();
	}

	//将记录list转换成JSON格式的String(RESTful接口输出专用，不带[])
	public String ListtoJSONRestNoS(List list, String sql) {
		Object[] obj = null;
		String column[] = actionUtils.FieldtoStringArrayNOUpper(sql);

		StringBuffer jsonstr = new StringBuffer();
		//jsonstr.append("[");

		int size = list == null ? 0 : list.size();
		if (size > 0){
			//jsonstr.append("{"+DQMS_SEPARATOR+"ROWNUM"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+(size)+DQMS_SEPARATOR+"}");
			//jsonstr.append(",");
			for (int i = 0; i < size; i++) {
				obj = (Object[]) list.get(i);
				int length = obj.length;
				if (length != 0) {
					//jsonstr.append("<ROW num='"+(i+1)+"'>");
					jsonstr.append("{");
					for (int j = 0; j < length; j++) {
						if (obj[j] == null){
							jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
							if (j < length - 1) {jsonstr.append(",");}
						}
						else{
							if (BigDecimal.class == obj[j].getClass()) {
								jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else if (Date.class == obj[j].getClass()) {
								SimpleDateFormat sdf = new SimpleDateFormat();
								sdf.applyPattern("yyyyMMddHHmmss");
								jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+sdf.format(obj[j])+DQMS_SEPARATOR);
								if (j < length - 1) {jsonstr.append(",");}

							} else {
								if (obj[j] == ""){
									jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
								else{
									jsonstr.append(DQMS_SEPARATOR+column[j]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[j].toString()+DQMS_SEPARATOR);
									if (j < length - 1) {jsonstr.append(",");}
								}
							}
						}
					}
					//jsonstr.append("</ROW>");
					jsonstr.append("}");
					if (i < size - 1) {jsonstr.append(",");}
				}
			}
		}
		else{
			//jsonstr.append("{"+DQMS_SEPARATOR+"ROWNUM"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+0+DQMS_SEPARATOR+"}");
		}
		//jsonstr.append("]");
		//System.out.println(jsonstr.toString());
		return jsonstr.toString();
	}

	//将Object[2]存储过程返回信息转换成JSON(RESTful接口错误输出专用)
	public String[] Obj2JSONRest(Object[] obj) {
		String[] result = new String[1];
		StringBuffer jsonstr = new StringBuffer();
		int length = obj.length;
		if (length != 0&&length == 2) {
			//jsonstr.append("[");
			jsonstr.append("{");
			jsonstr.append(DQMS_SEPARATOR+"errCode"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[0].toString()+DQMS_SEPARATOR);
			jsonstr.append(",");
			jsonstr.append(DQMS_SEPARATOR+"errMsg"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[1].toString()+DQMS_SEPARATOR);
			jsonstr.append("}");
			//jsonstr.append("]");
		}

		result[0] = jsonstr.toString();
		jsonstr.setLength(0);//用完清空

		return result;
	}

	//将ArrayName与ArrayValue字符串数组转换成JSON(RESTful接口特殊输出专用)
	public String[] Array2JSONRest(String[] ArrayName, String[] ArrayValue) {
		String[] result = new String[1];
		StringBuffer jsonstr = new StringBuffer();
		int length = ArrayName.length;

		jsonstr.append("{");
		for (int i = 0; i < length; i++) {
			jsonstr.append(DQMS_SEPARATOR+ArrayName[i]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+ArrayValue[i].toString()+DQMS_SEPARATOR);
			if(length > 1&&i < length - 1) {jsonstr.append(",");}
		}
		jsonstr.append("}");

		result[0] = jsonstr.toString();
		jsonstr.setLength(0);//用完清空

		return result;
	}

	//查询返回错误信息Response代码与json串
	public ResponseEntity<Object> ReturnResponseQueryObj(Object[] obj){
	//public static Response ReturnResponseQueryObj(Object[] obj){
		Object return_json  = Obj2JSONBZHQuery(obj);

		if (obj[0].equals("1")||obj[0].equals("0")){
			//return Response.status(Response.Status.OK).entity(return_json).type("application/json").build();
			return ResponseEntity.status(HttpStatus.OK).body(return_json);
		}
		else{
			//return Response.status(Response.Status.NOT_ACCEPTABLE).entity(return_json).type("application/json").build();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(return_json);
		}
	}

	//将查询错误信息Object[2]转换成JSON(标准化与调度中台查询RESTful接口错误输出专用)
	public String Obj2JSONBZHQuery(Object[] obj) {
		String result = null;
		String bodystr = null;
		String ls_md5 = null;

		StringBuffer jsonstr = new StringBuffer();
		StringBuffer jsonbodystr = new StringBuffer();

		int length = obj.length;

		if (length != 0&&length == 2) {
			jsonbodystr.append("{");
			jsonbodystr.append(DQMS_SEPARATOR+"APPCODE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[0].toString()+DQMS_SEPARATOR);
			jsonbodystr.append(",");
			jsonbodystr.append(DQMS_SEPARATOR+"ERRORMSG"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[1].toString()+DQMS_SEPARATOR);
			jsonbodystr.append("}");

			bodystr = jsonbodystr.toString();

			jsonstr.append("{");
			jsonstr.append("\"head\":");
			jsonstr.append("{");
			jsonstr.append(DQMS_SEPARATOR+"VERSION"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"1.0"+DQMS_SEPARATOR);
			jsonstr.append(",");
			jsonstr.append(DQMS_SEPARATOR+"UID"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+MakeUID(bodystr)+DQMS_SEPARATOR);
			jsonstr.append(",");
			jsonstr.append(DQMS_SEPARATOR+"ANDATE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+GetSysdate()+DQMS_SEPARATOR);
			jsonstr.append(",");
			jsonstr.append(DQMS_SEPARATOR+"ROWCOUNT"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"0"+DQMS_SEPARATOR);
			jsonstr.append("}");

			jsonstr.append(",");

			jsonstr.append("\"body\":");
			jsonstr.append(bodystr);
			jsonstr.append("}");
		}

		result = jsonstr.toString();
		jsonstr.setLength(0);//用完清空
		jsonbodystr.setLength(0);//用完清空

		return result;
	}

	//业务操作返回Response代码与json串
	public ResponseEntity<Object> ReturnResponseDo(Object[] obj){
	//public static Response ReturnResponseDo(Object[] obj){
		Object return_json  = Obj2JSONBZHDo(obj);

		if (obj[0].equals("1")||obj[0].equals("0")){
			//return Response.status(Response.Status.OK).entity(return_json).type("application/json").build();
			return ResponseEntity.status(HttpStatus.OK).body(return_json);
		}
		else{
			//return Response.status(Response.Status.NOT_ACCEPTABLE).entity(return_json).type("application/json").build();
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(return_json);
		}
	}

	//将操作错误信息Object[2]转换成JSON(标准化与调度中台查询RESTful接口错误输出专用)
	public String Obj2JSONBZHDo(Object[] obj) {
		String result = null;
		String bodystr = null;

		StringBuffer jsonstr = new StringBuffer();
		StringBuffer jsonbodystr = new StringBuffer();

		int length = obj.length;

		if (length != 0&&length == 2) {
			jsonbodystr.append("{");
			jsonbodystr.append(DQMS_SEPARATOR+"APPCODE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[0].toString()+DQMS_SEPARATOR);
			jsonbodystr.append(",");
			jsonbodystr.append(DQMS_SEPARATOR+"ERRORMSG"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+obj[1].toString()+DQMS_SEPARATOR);
			jsonbodystr.append("}");

			bodystr = jsonbodystr.toString();

			jsonstr.append("{");
			jsonstr.append("\"head\":");
			jsonstr.append("{");
			jsonstr.append(DQMS_SEPARATOR+"VERSION"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"1.0"+DQMS_SEPARATOR);
			jsonstr.append(",");
			jsonstr.append(DQMS_SEPARATOR+"UID"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+MakeUID(bodystr)+DQMS_SEPARATOR);
			jsonstr.append(",");
			jsonstr.append(DQMS_SEPARATOR+"ANDATE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+GetSysdate()+DQMS_SEPARATOR);
			jsonstr.append("}");

			jsonstr.append(",");

			jsonstr.append("\"body\":");
			jsonstr.append(bodystr);
			jsonstr.append("}");
		}

		result = jsonstr.toString();
		jsonstr.setLength(0);//用完清空
		jsonbodystr.setLength(0);//用完清空

		return result;
	}

	//将查询结果（List<Map<String, Object>>）的数据转换成JSON格式的String（标准化与调度中台查询）
	public String ListMap2JSON(List<Map<String, Object>> list_map, String sqlHead) {
		//System.out.println("sqlHead_len:"+sqlHead.length());

		String column_head[] = null;

		if (sqlHead.length() > 0){
			column_head = actionUtils.FieldtoStringArrayNOUpper(sqlHead);
		}

		//记录数
		int size = list_map == null ? 0 : list_map.size();

		String bodystr = null;

		StringBuffer jsonstr = new StringBuffer();
		StringBuffer jsonbodystr = new StringBuffer();

		jsonbodystr.append("[");

		for (int i = 0; i < size; i++) {
			Map<String, Object> row_map = list_map.get(i);
			Set<String> set = row_map.keySet();

			jsonbodystr.append("{");

			for (String key : set) {
				//排除list_map中已有的项
				if (sqlHead.length() > 0){
					for (int head=0; head<column_head.length; head++) {
			            if (key.equals(column_head[head])){
			            	column_head[head] = "";
			            	break;
			            }
			        }
				}

				String ls_data = "";
				Object obj_data = row_map.get(key);

				//根据键找对应值
				if (obj_data == null){
					ls_data = "";
				}
				else{
					ls_data = row_map.get(key).toString();
				}

    			ls_data = ls_data.replace(">", "&gt;");
				ls_data = ls_data.replace("<", "&lt;");
				ls_data = ls_data.replace(" ", "&nbsp;");
				ls_data = ls_data.replace("\"", "&quot;");
				ls_data = ls_data.replace("\'", "&apos;");
				//ls_data = ls_data.replace("&", "&amp;");
				ls_data = ls_data.replace("\\", "\\\\");
				ls_data = ls_data.replace("\n", "\\n");
				ls_data = ls_data.replace("\r", "\\r");
				ls_data = ls_data.replace("{", "(");
				ls_data = ls_data.replace("}", ")");
				ls_data = ls_data.replace("[", "(");
				ls_data = ls_data.replace("]", ")");
				//ls_data = ls_data.replace(",", "，");
				//ls_data = ls_data.replace(":", "：");

				jsonbodystr.append(DQMS_SEPARATOR+key+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+ls_data+DQMS_SEPARATOR);

				jsonbodystr.append(",");
    		}

			//将list_map没有的字段生成json空项数据
			if (sqlHead.length() > 0){
				for (int head=0; head<column_head.length; head++) {
		            if (!column_head[head].equals("")){
		            	jsonbodystr.append(DQMS_SEPARATOR+column_head[head]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+""+DQMS_SEPARATOR);
						jsonbodystr.append(",");
		            }
		        }
			}

			jsonbodystr.append("}");

			//去掉最后的“,”
			int jbs_len = jsonbodystr.length();
			jsonbodystr.replace(jbs_len - 2,jbs_len,"}");

			if (i < size - 1){
				jsonbodystr.append(",");
			}
		}
		jsonbodystr.append("]");

		bodystr = jsonbodystr.toString();

		jsonstr.append("{");
		jsonstr.append("\"head\":");
		jsonstr.append("{");
		jsonstr.append(DQMS_SEPARATOR+"VERSION"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"1.0"+DQMS_SEPARATOR);
		jsonstr.append(",");
		jsonstr.append(DQMS_SEPARATOR+"UID"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+MakeUID(bodystr)+DQMS_SEPARATOR);
		jsonstr.append(",");
		jsonstr.append(DQMS_SEPARATOR+"ANDATE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+GetSysdate()+DQMS_SEPARATOR);
		jsonstr.append(",");
		jsonstr.append(DQMS_SEPARATOR+"ROWCOUNT"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+ String.valueOf(size)+DQMS_SEPARATOR);
		jsonstr.append("}");

		jsonstr.append(",");

		jsonstr.append("\"body\":");
		jsonstr.append(bodystr);

		jsonstr.append("}");

		String ls_json = jsonstr.toString();
		//System.out.println(ls_json);

		jsonstr.setLength(0);//用完清空
		jsonbodystr.setLength(0);//用完清空

		return ls_json;
	}

	//将分页查询结果（List<Map<String, Object>>）的数据转换成JSON格式的String（标准化与调度中台查询）
	public String ListMapPage2JSON(List<Map<String, Object>> list_map, String sqlHead, Map<String, Object> pageinfo_map) {
		//System.out.println("sqlHead_len:"+sqlHead.length());

		String column_head[] = null;

		if (sqlHead.length() > 0){
			column_head = actionUtils.FieldtoStringArrayNOUpper(sqlHead);
		}

		//获取分页信息
		String current_page = String.valueOf(pageinfo_map.get("current_page"));
		String current_page_count = String.valueOf(pageinfo_map.get("current_page_count"));
		String total_page_num = String.valueOf(pageinfo_map.get("total_page_num"));
		String total_count = String.valueOf(pageinfo_map.get("total_count"));

		//记录数
		int size = list_map == null ? 0 : list_map.size();

		String bodystr = null;

		StringBuffer jsonstr = new StringBuffer();
		StringBuffer jsonbodystr = new StringBuffer();

		jsonbodystr.append("[");

		for (int i = 0; i < size; i++) {
			Map<String, Object> row_map = list_map.get(i);
			Set<String> set = row_map.keySet();

			jsonbodystr.append("{");

			for (String key : set) {
				//排除list_map中已有的项
				if (sqlHead.length() > 0){
					for (int head=0; head<column_head.length; head++) {
			            if (key.equals(column_head[head])){
			            	column_head[head] = "";
			            	break;
			            }
			        }
				}

				String ls_data = "";
				Object obj_data = row_map.get(key);

				//根据键找对应值
				if (obj_data == null){
					ls_data = "";
				}
				else{
					ls_data = row_map.get(key).toString();
				}

    			ls_data = ls_data.replace(">", "&gt;");
				ls_data = ls_data.replace("<", "&lt;");
				ls_data = ls_data.replace(" ", "&nbsp;");
				ls_data = ls_data.replace("\"", "&quot;");
				ls_data = ls_data.replace("\'", "&apos;");
				//ls_data = ls_data.replace("&", "&amp;");
				ls_data = ls_data.replace("\\", "\\\\");
				ls_data = ls_data.replace("\n", "\\n");
				ls_data = ls_data.replace("\r", "\\r");
				ls_data = ls_data.replace("{", "(");
				ls_data = ls_data.replace("}", ")");
				ls_data = ls_data.replace("[", "(");
				ls_data = ls_data.replace("]", ")");
				//ls_data = ls_data.replace(",", "，");
				//ls_data = ls_data.replace(":", "：");

				jsonbodystr.append(DQMS_SEPARATOR+key+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+ls_data+DQMS_SEPARATOR);

				jsonbodystr.append(",");
    		}

			//将list_map没有的字段生成json空项数据
			if (sqlHead.length() > 0){
				for (int head=0; head<column_head.length; head++) {
		            if (!column_head[head].equals("")){
		            	jsonbodystr.append(DQMS_SEPARATOR+column_head[head]+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+""+DQMS_SEPARATOR);
						jsonbodystr.append(",");
		            }
		        }
			}

			jsonbodystr.append("}");

			//去掉最后的“,”
			int jbs_len = jsonbodystr.length();
			jsonbodystr.replace(jbs_len - 2,jbs_len,"}");

			if (i < size - 1){
				jsonbodystr.append(",");
			}
		}
		jsonbodystr.append("]");

		bodystr = jsonbodystr.toString();

		jsonstr.append("{");
		jsonstr.append("\"head\":");
		jsonstr.append("{");
		jsonstr.append(DQMS_SEPARATOR+"VERSION"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+"1.0"+DQMS_SEPARATOR);
		jsonstr.append(",");
		jsonstr.append(DQMS_SEPARATOR+"UID"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+MakeUID(bodystr)+DQMS_SEPARATOR);
		jsonstr.append(",");
		jsonstr.append(DQMS_SEPARATOR+"ANDATE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+GetSysdate()+DQMS_SEPARATOR);
		jsonstr.append(",");
		jsonstr.append(DQMS_SEPARATOR+"ROWCOUNT"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+total_count+DQMS_SEPARATOR);
		jsonstr.append(",");
		jsonstr.append(DQMS_SEPARATOR+"CURRENT_PAGE"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+current_page+DQMS_SEPARATOR);
		jsonstr.append(",");
		jsonstr.append(DQMS_SEPARATOR+"CURRENT_PAGE_COUNT"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+current_page_count+DQMS_SEPARATOR);
		jsonstr.append(",");
		jsonstr.append(DQMS_SEPARATOR+"TOTAL_PAGE_NUM"+DQMS_SEPARATOR+":"+DQMS_SEPARATOR+total_page_num+DQMS_SEPARATOR);
		jsonstr.append("}");

		jsonstr.append(",");

		jsonstr.append("\"body\":");
		jsonstr.append(bodystr);

		jsonstr.append("}");

		String ls_json = jsonstr.toString();
		//System.out.println(ls_json);

		jsonstr.setLength(0);//用完清空
		jsonbodystr.setLength(0);//用完清空

		return ls_json;
	}

	//查询返回Response代码与json串
	//public static Response ReturnResponseQuery(Object json){
	public ResponseEntity<Object> ReturnResponseQuery(Object json){
		//return Response.status(Response.Status.OK).entity(json).type("application/json").build();
		return ResponseEntity.status(HttpStatus.OK).body(json);
	}

	//获取数据库时间字串
	public String GetSysdate(){
		//String strSysdate = SqlUtil.getInstance().selectOne("select to_char(sysdate,'YYYYMMDDHH24MISS') from dual");
		String strSysdate = sqlUtil.selectOne("select to_char(sysdate,'YYYYMMDDHH24MISS') from dual");
		return strSysdate;
	}

	//校验ClientID
	public String CheckClientID(String ClientID){
		String check_result = "0";
		check_result = "1";
		//to do 后台表设计完成后，需要增加代码实现

		return check_result;
	}

	//校验UID
	public String CheckUID(String UID, String input_json){
		String check_result = "0";
		String body = "";

		String[] splitstr = input_json.split("\"body\":");
		if (splitstr.length > 1){
			//System.out.println("splitstr[1]:"+splitstr[1]);
			String bodystr = splitstr[1];
			body = bodystr.replaceAll("}}","}");
			//System.out.println("body_split:"+body);
		}
		else{
			body = "";
		}

		//根据body生成UID比对串
		String body_MD5 = MakeUID(body);
		//System.out.println("body_MD5:"+body_MD5);
		if (UID.equals(body_MD5)){
			check_result = "1";
		}
		else{
			check_result = "0";
		}

		return check_result;
	}

	//生成UID(md5校验串)
	public String MakeUID(String strinput){
		String strMD5 = "";
		strMD5 = MD5(strinput,"GBK");
		strMD5 = strMD5.toUpperCase();
		//System.out.println("strMD5:"+strMD5);
		return strMD5;
	}

	public String MD5(String str, String enCode){
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(str.getBytes(enCode));
        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

	//判断字符串是否为数字
	public static int isNumber(String str){
		Boolean strResult = str.matches("-?[0-9]+.?[0-9]*");
		if(strResult == true) {
			return 1;
        }
		else{
        	return 0;
        }
    }

}

