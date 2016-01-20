package me.thomas.knowledge.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

public class ParamUtil {

    public static boolean get(HttpServletRequest request, String param, boolean defaultValue) {

        return GetterUtil.get(request.getParameter(param), defaultValue);
    }

    public static Date get(HttpServletRequest request, String param, DateFormat dateFormat, Date defaultValue) {

        return GetterUtil.get(request.getParameter(param), dateFormat, defaultValue);
    }

    public static double get(HttpServletRequest request, String param, double defaultValue) {

        return GetterUtil.get(request.getParameter(param), defaultValue);
    }

    public static float get(HttpServletRequest request, String param, float defaultValue) {

        return GetterUtil.get(request.getParameter(param), defaultValue);
    }

    public static int get(HttpServletRequest request, String param, int defaultValue) {

        return GetterUtil.get(request.getParameter(param), defaultValue);
    }

    public static long get(HttpServletRequest request, String param, long defaultValue) {

        return GetterUtil.get(request.getParameter(param), defaultValue);
    }

    public static short get(HttpServletRequest request, String param, short defaultValue) {

        return GetterUtil.get(request.getParameter(param), defaultValue);
    }

    public static String get(HttpServletRequest request, String param, String defaultValue) {

        String returnValue = GetterUtil.get(request.getParameter(param), defaultValue);

        if (returnValue != null) {
            return returnValue.trim();
        }

        return null;
    }

    public static boolean getBoolean(HttpServletRequest request, String param) {
        return GetterUtil.getBoolean(request.getParameter(param));
    }

    public static boolean getBoolean(HttpServletRequest request, String param, boolean defaultValue) {

        return get(request, param, defaultValue);
    }

    public static boolean[] getBooleanValues(HttpServletRequest request, String param) {

        return getBooleanValues(request, param, new boolean[0]);
    }

    public static boolean[] getBooleanValues(HttpServletRequest request, String param, boolean[] defaultValue) {

        return GetterUtil.getBooleanValues(getParameterValues(request, param), defaultValue);
    }

    public static Date getDate(HttpServletRequest request, String param, DateFormat dateFormat) {

        return GetterUtil.getDate(request.getParameter(param), dateFormat);
    }

    public static Date getDate(HttpServletRequest request, String param, DateFormat dateFormat, Date defaultValue) {

        return get(request, param, dateFormat, defaultValue);
    }

    public static Date[] getDateValues(HttpServletRequest request, String param, DateFormat dateFormat) {

        return getDateValues(request, param, dateFormat, new Date[0]);
    }

    public static Date[] getDateValues(HttpServletRequest request, String param, DateFormat dateFormat,
            Date[] defaultValue) {

        return GetterUtil.getDateValues(getParameterValues(request, param), dateFormat, defaultValue);
    }

    public static double getDouble(HttpServletRequest request, String param) {
        return GetterUtil.getDouble(request.getParameter(param));
    }

    public static double getDouble(HttpServletRequest request, String param, double defaultValue) {

        return get(request, param, defaultValue);
    }

    public static double[] getDoubleValues(HttpServletRequest request, String param) {

        return getDoubleValues(request, param, new double[0]);
    }

    public static double[] getDoubleValues(HttpServletRequest request, String param, double[] defaultValue) {

        return GetterUtil.getDoubleValues(getParameterValues(request, param), defaultValue);
    }

    public static float getFloat(HttpServletRequest request, String param) {
        return GetterUtil.getFloat(request.getParameter(param));
    }

    public static float getFloat(HttpServletRequest request, String param, float defaultValue) {

        return get(request, param, defaultValue);
    }

    public static float[] getFloatValues(HttpServletRequest request, String param) {

        return getFloatValues(request, param, new float[0]);
    }

    public static float[] getFloatValues(HttpServletRequest request, String param, float[] defaultValue) {

        return GetterUtil.getFloatValues(getParameterValues(request, param), defaultValue);
    }

    public static int getInteger(HttpServletRequest request, String param) {
        return GetterUtil.getInteger(request.getParameter(param));
    }

    public static int getInteger(HttpServletRequest request, String param, int defaultValue) {

        return get(request, param, defaultValue);
    }

    public static int[] getIntegerValues(HttpServletRequest request, String param) {

        return getIntegerValues(request, param, new int[0]);
    }

    public static int[] getIntegerValues(HttpServletRequest request, String param, int[] defaultValue) {

        return GetterUtil.getIntegerValues(getParameterValues(request, param), defaultValue);
    }

    public static long getLong(HttpServletRequest request, String param) {
        return GetterUtil.getLong(request.getParameter(param));
    }

    public static long getLong(HttpServletRequest request, String param, long defaultValue) {

        return get(request, param, defaultValue);
    }

    public static long[] getLongValues(HttpServletRequest request, String param) {

        return getLongValues(request, param, new long[0]);
    }

    public static long[] getLongValues(HttpServletRequest request, String param, long[] defaultValue) {

        return GetterUtil.getLongValues(getParameterValues(request, param), defaultValue);
    }

    public static String[] getParameterValues(HttpServletRequest request, String param) {

        return getParameterValues(request, param, new String[0]);
    }

    public static String[] getParameterValues(HttpServletRequest request, String param, String[] defaultValue) {

        String[] values = request.getParameterValues(param);

        if (values == null) {
            return defaultValue;
        }

        if (values.length == 1) {
            return StringUtil.split(values[0]);
        }

        return values;
    }

    public static short getShort(HttpServletRequest request, String param) {
        return GetterUtil.getShort(request.getParameter(param));
    }

    public static short getShort(HttpServletRequest request, String param, short defaultValue) {

        return get(request, param, defaultValue);
    }

    public static short[] getShortValues(HttpServletRequest request, String param) {

        return getShortValues(request, param, new short[0]);
    }

    public static short[] getShortValues(HttpServletRequest request, String param, short[] defaultValue) {

        return GetterUtil.getShortValues(getParameterValues(request, param), defaultValue);
    }

    public static String getString(HttpServletRequest request, String param) {
        return GetterUtil.getString(request.getParameter(param));
    }

    public static String getString(HttpServletRequest request, String param, String defaultValue) {

        return get(request, param, defaultValue);
    }

    public static void print(HttpServletRequest request) {
        Map<String, String[]> parameters = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String name = entry.getKey();
            String[] values = entry.getValue();

            for (int i = 0; i < values.length; i++) {
                System.out.println(name + "[" + i + "] = " + values[i]);
            }
        }
    }

}