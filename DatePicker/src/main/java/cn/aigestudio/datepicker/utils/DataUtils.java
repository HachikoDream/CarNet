package cn.aigestudio.datepicker.utils;

import android.os.SystemClock;
import android.text.format.DateUtils;

import java.util.Calendar;

/**
 * 数组操作工具类
 * <p/>
 * Utils of data operation
 *
 * @author AigeStudio 2015-07-22
 */
public final class DataUtils {
    /**
     * 一维数组转换为二维数组
     *
     * @param src    ...
     * @param row    ...
     * @param column ...
     * @return ...
     */
    public static String[][] arraysConvert(String[] src, int row, int column) {
        String[][] tmp = new String[row][column];
        for (int i = 0; i < row; i++) {
            tmp[i] = new String[column];
            System.arraycopy(src, i * column, tmp[i], 0, column);
        }
        return tmp;
    }

    public static int getCurrentYear() {
        long currentTimeMills = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMills);
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        long currentTimeMills = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMills);
        return calendar.get(Calendar.MONTH) + 1;

    }
}
