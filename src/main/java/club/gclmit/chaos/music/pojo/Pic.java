package club.gclmit.chaos.music.pojo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * 图片链接
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/9 0:21
 * @version: V1.0
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Pic {

    /**
     * 特大
     */
    private String extraLarge;

     /**
      * 大图片
      */
    private String large;

     /**
      * 中图片
      */
    private String medium;

    /**
     * 小图片
     */
    private String small;

}
