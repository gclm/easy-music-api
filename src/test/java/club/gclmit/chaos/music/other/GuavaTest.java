package club.gclmit.chaos.music.other;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Guava 测试情况
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/10 10:56
 * @version: V1.0
 */
public class GuavaTest {

    private static Logger logger = LoggerFactory.getLogger(GuavaTest.class);

    public static void main(String[] args) {
         String xml = "<!-- ABC -->";
         /**
          * 复制字符串
          */
        String repeat = Strings.repeat(xml, 3);
        logger.info("=={}==",repeat);

        String suffix = Strings.commonSuffix(xml, "<!--");
        logger.info("=={}==",suffix);

//        String remove =  CharMatcher.is("<!--").removeFrom(xml);
        String remove =  CharMatcher.anyOf("<!---->").removeFrom(xml);
        logger.info("=={}==",remove);
        String remove2 =  CharMatcher.whitespace().and(CharMatcher.anyOf("<!---->")).removeFrom(xml);
        logger.info("=={}==",remove2);

    }
}
