package club.gclmit.chaos.music.other;

import club.gclmit.chaos.music.util.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 测试方法
 */
public class StringUtilsTest {

    public static void main(String[] args) {
        System.out.println(StringUtils.isNumeric("002COmzJ0SPZMl"));
        System.out.println(StringUtils.isNumeric("244499239"));

        long min = (long) Math.pow(10, 9);
        System.out.println(ThreadLocalRandom.current().nextLong(min, min * 10));
    }
}
