package cn.gov.spb.cq.yzglfzgj.utils;

import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class WebUtils {
	private static final transient Logger log = Logger
			.getLogger(WebUtils.class);

	/**
	 * 转化HTML字符，可以在HTML标签中显示
	 * 
	 * @param str
	 * @return
	 */
	public static String htmlToChars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	//
	// // 分页时要用的可能是用来数据在第几页显示吧，默认是第一页
	// public static int getPageNumber(HttpServletRequest req) {
	//
	// int pageNumber = 1;
	// // TableTagParameters.PARAMETER_EXPORTING
	// if
	// (!Validator.isNullEmpty(req.getParameter(TableTagParameters.PARAMETER_EXPORTING)))
	// {
	// pageNumber = 0;
	// } else {
	// String page = req.getParameter("page");
	// if (!Validator.isNullEmpty(page)) {
	// pageNumber = Integer.parseInt(page);
	// }
	// }
	// return pageNumber;
	// }


	// /*
	// * 这个方法不知道时咋怎的
	// * 好像要取出event=""
	// * 判断request的提交方式
	// * 我明白了是用来保存，action中event后面的方法名的哟
	// *
	// */
	// public static String saveQuerySting(String key, HttpServletRequest req,
	// HttpServletResponse res) {
	// String queryString = req.getQueryString();
	// if ("POST".equals(req.getMethod().toUpperCase())) {
	// queryString = makeQueryString(req.getParameterMap());
	// }
	// return saveQuerySting(key, queryString, res);
	// }
	//
	// /*
	// * 返回action中url后面的event=""方法了
	// */
	// public static String saveQuerySting(String key, String queryString,
	// HttpServletResponse res) {
	// log.info("saveQuerySting:" + key + ":" + queryString);
	// res.addCookie(new Cookie(key, queryString));
	// return queryString;
	// }
	//
	// public static String loadQuerySting(String key, HttpServletRequest req,
	// HttpServletResponse res) {
	// String queryString = (String) ((Map) getInfoByCookie(req, new String[]
	// {key})).get(key);
	// log.info("loadQuerySting:" + key + ":" + queryString);
	// return queryString;
	// }

	public static Map fetchData(String[] keys, Map properties) {
		Map data = new HashMap();
		// Loop through the property name/value pairs to be set
		Iterator names = properties.keySet().iterator();
		for (int i = 0; i < keys.length; i++) {
			String name = keys[i];
			Object value = properties.get(name);
			if (value instanceof String) {
				data.put(name, value);
			} else if (value instanceof String[]) {
				data.put(name, ((String[]) value)[0]);
			}
		}
		return data;
	}

	// public static String makeQueryString(Map properties) {
	// StringBuffer buf = new StringBuffer();
	// // Loop through the property name/value pairs to be set
	// Iterator names = properties.keySet().iterator();
	// while (names.hasNext()) {
	// String name = (String) names.next();
	// Object value = properties.get(name);
	// if (Validator.isNullEmpty(value)) {
	// continue;
	// }
	// String temp = "";
	// if (value instanceof String) {
	// temp = (String) value;
	// } else if (value instanceof String[]) {
	// temp = ((String[]) value)[0];
	// }
	// try {
	// buf.append("&" + name + "=" + URLEncoder.encode(temp, "utf-8"));
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// log.error(e);
	// }
	// }
	// if (buf.length() == 0) {
	// return "";
	// }
	// return buf.substring(1).toString();
	// }

	public static List split(String text, String regex) {
		List list = new ArrayList();
		int start = 0;
		int pos = 0;
		int step = regex.length();
		while ((pos = text.indexOf(regex, start)) > -1) {
			list.add(text.substring(start, pos));
			start = pos + step;
		}
		list.add(text.substring(start));
		return list;
	}

	/*
     * 
     */
	public static List split(String text) {
		List list = new ArrayList();
		for (int i = 0; i < text.length(); i++) {
			list.add(text.charAt(i));
		}
		return list;
	}

	public static String ltrim(String text, char ch) {
		int pos = 0;
		for (; pos < text.length(); pos++) {
			if (ch != text.charAt(pos)) {
				break;
			}
		}
		return text.substring(pos);
	}

	public static String rtrim(String text, char ch) {
		int pos = text.length() - 1;
		for (; pos > 0; pos--) {
			if (ch != text.charAt(pos)) {
				break;
			}
		}
		return text.substring(0, pos + 1);
	}

	public static String replace(String text, int begin, int end, char ch) {
		StringBuffer buff = new StringBuffer(text);
		for (int i = begin; i < end; i++) {
			buff.setCharAt(i, ch);
		}
		return buff.toString();
	}

	public static void populate(Map bean, Map properties) {

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}

		// Loop through the property name/value pairs to be set
		Iterator names = properties.keySet().iterator();
		while (names.hasNext()) {
			// Identify the property name and value(s) to be assigned
			String name = (String) names.next();
			if (name == null) {
				continue;
			}
			Object value = properties.get(name);
			if (value instanceof String) {
				bean.put(name, value);
			} else if (value instanceof String[]) {
				bean.put(name, ((String[]) value)[0]);
			}
		}
	}

	public static String getSeverletPart(String url) {
		int pos = url.lastIndexOf("/");
		return url.substring(pos);
	}

	/**
	 * 根据keys从cookie取得相应的值
	 * 
	 * @param request
	 * @param keys
	 * @return
	 */
	public static Map<String, String> getInfoByCookie(
			HttpServletRequest request, String[] keys) {
		Map<String, String> info = new HashMap<String, String>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < keys.length; i++) {
				for (int j = 0; j < cookies.length; j++) {
					if (cookies[j].getName().equals(keys[i])) {
						info.put(keys[i], cookies[j].getValue());
						break;
					}
				}
			}
		}
		return info;
	}



	/*
	 * 用来生成id的方法，好像全球唯一id，3240年不重复的哟
	 */
	public static String generateUuidHex() {
		/*
		 * GUID是一个128位长的数字，一般用16进制表示。算法的核心思想是结合机器的网卡、当地时间、一个随即数来生成GUID
		 * 。从理论上讲，如果一台机器每秒产生10000000个GUID，则可以保证（概率意义上）3240年不重复。
		 * UUID是1.5中新增的一个类，在java.util下，用它可以产生一个号称全球唯一的ID import java.util.UUID;
		 * public class Test { public static void main(String[] args) { UUID
		 * uuid = UUID.randomUUID(); log.info (uuid); } } 编译运行输出：
		 * 07ca3dec-b674-41d0-af9e-9c37583b08bb
		 * 
		 * UUID是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。通常平台会提供生成UUID的API。UUID按照开放软件基金会
		 * (OSF)制定的标准计算，用到了以太网卡地址、纳秒级时间、
		 * 芯片ID码和许多可能的数字。由以下几部分的组合：当前日期和时间(UUID的第一个部分与时间有关
		 * ，如果你在生成一个UUID之后，过几秒又生成一个UUID，则第一个部分不同，其余相同)， 时钟序列，
		 * 全局唯一的IEEE机器识别号（如果有网卡
		 * ，从网卡获得，没有网卡以其他方式获得），UUID的唯一缺陷在于生成的结果串会比较长。关于UUID这个标准使用最普遍的是微软的
		 * GUID(Globals Unique Identifiers)。 调查：有 4 种不同的基本 UUID 类型：基于时间的
		 * UUID、DCE 安全 UUID、基于名称的 UUID 和随机生成的 UUID。这四种类型的UUID产生方法请调查。
		 * 同时，是否可以控制产生的UUID长度在一定范围内。
		 */
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}

	/*
	 * 生成时间的方法
	 */
	public static String getTime(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(Calendar.getInstance().getTime());
	}

	public static String getDateTimeString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null == date){
			return null;
		}
		return format.format(date);
	}

	/*
	 * 生成时间的方法
	 */
	public static String getCreateDate() {
		return getTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExt(String fileName) {
		int pos = fileName.lastIndexOf(".");
		if (pos > -1) {
			String temp = fileName.substring(pos + 1).toLowerCase();
			pos = temp.indexOf("?");
			if (pos > -1) {
				return temp.substring(0, pos);
			}
			return temp.trim().replaceAll("\"", "");
		}
		return "";
	}

	/**
	 * 生成随机字符串
	 * 
	 * @param size
	 *            随机字符串长度
	 * @return 随机字符
	 */
	public static String getRandomString(int size) {
		char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q',
				'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd',
				'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
		Random random = new Random(); // 初始化随机数产生器
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(random.nextInt()) % c.length]);
		}
		return sb.toString();
	}

	/**
	 * 生成随机数字
	 * 
	 * @param size
	 *            随机字符串长度
	 * @return 随机数字
	 */
	public static String getRandomNum(int size) {
		char[] c = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		Random random = new Random(); // 初始化随机数产生器
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(c[Math.abs(random.nextInt()) % c.length]);
		}
		return sb.toString();
	}

	// 返回a到b之間(包括a,b)的任意一個自然数,如果a > b || a < 0，返回-1
	public static int getRandomInt(int a, int b) {
		if (a > b || a < 0)
			return -1;
		return a + (int) (Math.random() * (b - a + 1));
	}

	// 返回a到b之間(包括a,b)的任意一個自然数,但不包括最大数
	public static int getExtRandomInt(int a, int b) {

		int c = 0;
		do {
			c = getRandomInt(a, b);
		} while (c == b);

		return c;
	}

	public static void copy(InputStream in, OutputStream out)
			throws IOException {
		copy(in, out, false);
	}

	public static void copy(InputStream in, OutputStream out, boolean notClose)
			throws IOException {
		int size = 1024;
		byte[] b = new byte[size];
		while ((size = in.read(b)) > 0) {
			out.write(b, 0, size);
		}
		if (notClose) {
			out.close();
			in.close();
		}

	}


	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		// Map log = new Hashtable();
		// log.put("ID", WebUtils.generateUuidHex());
		//
		// log.put("CREATE_DATE", "わたし私");
		//
		// String text = serialize(log, "UTF-8");
		// log.info("serialize:"+text);
		// log.info("unserialize:"+(Map)unserialize(text, "UTF-8"));

		// log.info(WebUtils.formatString("fdafda", 2, "..."));
		// System.out.println(getFileExt("http://xx.jpg?xx=2?"));

		System.out.println();
	}

}
