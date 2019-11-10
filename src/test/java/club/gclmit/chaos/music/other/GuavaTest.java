package club.gclmit.chaos.music.other;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Guava 测试情况
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/10 10:56
 * @version: V1.0
 */
@Slf4j
public class GuavaTest {

    public static void main(String[] args) {
         String xml = "<!-- ABC -->";
         /**
          * 复制字符串
          */
        String repeat = Strings.repeat(xml, 3);
        log.info("=={}==",repeat);

        String suffix = Strings.commonSuffix(xml, "<!--");
        log.info("=={}==",suffix);

//        String remove =  CharMatcher.is("<!--").removeFrom(xml);
        String remove =  CharMatcher.anyOf("<!---->").removeFrom(xml);
        log.info("=={}==",remove);
        String remove2 =  CharMatcher.whitespace().and(CharMatcher.anyOf("<!---->")).removeFrom(xml);
        log.info("=={}==",remove2);

    }
}
