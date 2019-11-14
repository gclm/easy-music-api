package club.gclmit.chaos.music.util;

/**
 * <p>
 * 字符串处理工具类
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/14 20:36
 * @version: V1.0
 * @since 1.8
 */
public class StringUtils {

    /**
     * <p>
     *  判断字符串是否是数字
     * </p>
     *
     * @author gclm
     * @param: str
     * @date 2019/11/14 20:36
     * @return: boolean
     * @throws
     */
    public static boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
