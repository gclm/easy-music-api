package club.gclmit.chaos.music.pojo;
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
public class Pic {

    /**
     * Id
     */
    private String id;

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

    public Pic(String extraLarge, String large, String medium, String small) {
        this.extraLarge = extraLarge;
        this.large = large;
        this.medium = medium;
        this.small = small;
    }
}
