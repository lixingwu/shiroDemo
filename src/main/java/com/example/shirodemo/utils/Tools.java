package com.example.shirodemo.utils;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author lixingwu
 */
public class Tools extends PropertyUtilsBean {

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request the request
     * @return string
     * @author "lixingwu"
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 方法描述：自定义判断是否为空
     * 创建作者：李兴武
     * 创建日期：2017-06-22 19:50:01
     *
     * @param str the str
     * @return the boolean
     * @author "lixingwu"
     */
    private static Boolean isBlank(String str) {
        if (str != null) {
            str = str.replaceAll("\r\n|\n\r|\n|\r|\f|\t", "");
        }
        if (str == null) {
            return true;
        } else if ("".equals(str)) {
            return true;
        } else if ("null".equals(str)) {
            return true;
        } else if ("NULL".equals(str)) {
            return true;
        } else if ("(null)".equals(str)) {
            return true;
        } else if ("(NULL)".equals(str)) {
            return true;
        } else if (str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 方法描述：判断obj是否为空
     * 创建作者：李兴武
     * 创建日期：2017-06-22 19:50:01
     *
     * @param obj the 判断的对象
     * @return the boolean
     * @author "lixingwu"
     */
    public static Boolean isBlank(Object obj) {
        if (obj != null) {
            return isBlank(String.valueOf(obj));
        }
        return true;
    }

    /**
     * 字节b转化为 kb、mb、gb
     *
     * @param size 字节数大小
     * @return string
     * @author "lixingwu"
     */
    public static String bit2KMG(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

    /**
     * 方法描述：把map参数转化为URL参数字符串.
     * 创建时间：2018-11-25 22:30:11
     *
     * @param map 参数Map
     * @return the string
     * @author "lixingwu"
     */
    public static String mapToUrl(Map<String, String> map) {
        StringBuilder sb = new StringBuilder("?");
        if (map.size() > 0) {
            for (String key : map.keySet()) {
                sb.append(key).append("=");
                if (StringUtils.isEmpty(map.get(key))) {
                    sb.append("&");
                } else {
                    String value = map.get(key);
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    sb.append(value).append("&");
                }
            }
        }

        String s = sb.toString();
        if (s.endsWith("&")) {
            s = org.apache.commons.lang3.StringUtils.substringBeforeLast(s, "&");
        }
        return s;

    }


}
