package com.sea.baselibrary.base.util;

import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isEquals(String s1, String s2) {
        if (null == s1 && null == s2) {
            return true;
        }
        if (null != s1 && null != s2) {
            return s1.trim().equals(s2.trim());
        }
        return false;
    }


    /**
     * 获取点点的字符串
     *
     * @param oldStr 原字符串
     * @param length 字符串长度
     * @return 带 ...的字符串
     */
    public static String getDotSizeStr(String oldStr, int length) {
        if (isNotEmpty(oldStr)) {
            if (oldStr.length() > length) {
                return oldStr.substring(0, length) + "...";
            } else {
                return oldStr;
            }
        } else {
            return "";
        }


    }

    public static boolean isLetterDigit(String str) {
        //只有英文和数字
        String regex = "[a-zA-Z0-9]+";
        //是否包含英文
        String eng = ".*[a-zA-z].*";
        //是否包含数字
        String reg = ".*[0-9].*";
        if (str.matches(regex) && str.matches(eng) && str.matches(reg)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断一个字符串是否为字母
     *
     * @param fstrData
     * @return
     */

    public static boolean checkStr(String fstrData) {

        char c = fstrData.charAt(0);

        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {

            return true;

        } else {

            return false;

        }

    }

    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }

    /**
     * 判断字符串是不是空的方法，
     */
    public static boolean isBlank(String param) {
        if (param == null || param.trim().equals("") || param.trim().equals("null"))
            return true;
        else
            return false;
    }

    public static boolean isNull(String param) {
        if (param == null || param.trim().equals(""))
            return true;
        else
            return false;
    }

    public static boolean isJson(String param) {
        boolean b = isBlank(param);
        if (!b) {
            if (param.startsWith("{") || param.startsWith("[")) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public static String filterHtml(String content) {
        return content.replaceAll("</?[^<]+>", "").replaceAll("\\s*|\t|\r|\n", "");
    }

    public static boolean isBoolean(String param) {
        boolean b = isBlank(param);
        if (!b) {
            if (param.trim().equals("true") || param.trim().equals("false"))
                b = true;
            else
                b = false;
        }
        return b;
    }

    public static boolean checkMobile(String value) {
        // return value.matches("^[1][3,4,5,8]+\\d{9}");
        return checkNum(value) && value.length() == 11;
    }

    public static boolean checkNum(String value) {
        return value != null && value.matches("^[0-9]*$");
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || "null".equals(input) || "[]".equals(input) || "{}".equals(input) || "NULL".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        if (isEmpty(input)) return "";
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 截取需要长度的String
     *
     * @return
     */
    public static String toGetStr(String inputStr, int intSize) {
        String returnStr = "";
        if (inputStr != null) {
            if (!isEmpty(inputStr)) {
                int strSize = inputStr.length();
                if (strSize > intSize) {
                    returnStr = inputStr.substring(0, intSize) + "..";
                } else
                    returnStr = inputStr;
            }

        }
        return returnStr;
    }

    public static boolean checkEmail(String email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher("dffdfdf@qq.com");
        return matcher.matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    public static float toFloat(String str, float defValue) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    public static double toDouble(String str, int defValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        if (isEmpty(obj)) return 0;
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }


    //给TextView设置部分大小
    public static String getText(TextView tv) {
        if (null == tv) return "";
        else {
            if (null != tv.getText()) return tv.getText().toString();
            else return "";
        }
    }

    public static boolean isJSON2(String str) {
        boolean result = false;
        try {
            Object obj = JSON.parse(str);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }


    //前一个String是否包括后一个
    public static boolean isContains(String s1, String s2) {
        if (null == s1 && null == s2) {
            return true;
        }
        if (null != s1 && null != s2) {
            if (s1.trim().contains(s2.trim())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @param url
     * @param name
     * @return
     */
    public static String getQueryString(String url, String name) {
        Map<String, String> map = new HashMap<String, String>();
        if (url != null && url.contains("?") && url.contains("=")) {
            String[] arr = url.split("\\?");
            if (arr.length > 1) {
                String[] arrTemp = arr[1].split("&");
                if(arrTemp!=null&&arrTemp.length>0) {
                    for (String str : arrTemp) {
                        String[] qs = str.split("=");
                        if(qs.length>1) {
                            map.put(qs[0], qs[1]);
                        }
                    }
                }
            }
        }
        if (map.containsKey(name)) {
            return map.get(name);

        }
        return "";
    }

    /**
     * 给链接添加参数
     *
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String addQueryString(String url, String name, String value) {
        if (url != null && url.contains("?")) {
            url = url + "&" + name + "=" + value;
        } else {
            url = url + "?" + name + "=" + value;
        }
        return url;
    }


    /**
     * 万级处理
     *
     * @param num
     * @return
     */
    public static String getNumberWan(long num) {
        if (num >= 1000) {
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(num));
            // 转换为万元（除以10000）
            BigDecimal decimal = bigDecimal.divide(new BigDecimal("10000"));
            // 保留一位小数
            DecimalFormat formater = new DecimalFormat("0.0");
            formater.setRoundingMode(RoundingMode.DOWN);
            // 格式化完成之后得出结果
            String rs = formater.format(decimal);
            return rs.concat("w");
        } else {
            return String.valueOf(num);
        }
    }


    public static int findIndex(TextView textView, CharSequence mainContent, CharSequence suffix) {
        for (int i = 1; i < mainContent.length(); i++) {
            textView.setText(mainContent.subSequence(0, i).toString() + suffix.toString());
            int lineCount0 = textView.getLayout().getLineCount();
            textView.setText(mainContent.subSequence(0, i + 1).toString() + suffix.toString());
            int lineCount1 = textView.getLayout().getLineCount();
            if (lineCount0 == 2 && lineCount1 == 3) {
                return i;
            }
        }
        return -1;
    }



    private static String getString(long t){
        String m="";
        if(t>0){
            if(t<10){
                m="0"+t;
            }else{
                m=t+"";
            }
        }else{
            m="00";
        }
        return m;
    }

    /**
     *
     * @param t 毫秒
     * @return
     * @author Peter（张春玲）
     */
    public static String videoTimeFormat(long t){
        if(t<60000){
            return "00:"+getString((t % 60000 )/1000);
        }else if((t>=60000)&&(t<3600000)){
            return getString((t % 3600000)/60000)+":"+getString((t % 60000 )/1000);
        }else {
            return getString(t / 3600000)+":"+getString((t % 3600000)/60000)+":"+getString((t % 60000 )/1000);
        }
    }



}
