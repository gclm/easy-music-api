package club.gclmit.chaos.music.model.pojo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 歌曲图片
 * </p>
 *
 * @author 孤城落寞
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music_pic")
public class Pic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 歌曲 id

     */
    private String songId;

    /**
     * 特大图片
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

    public Pic(String songId, String extraLarge, String large, String medium, String small) {
        this.songId = songId;
        this.extraLarge = extraLarge;
        this.large = large;
        this.medium = medium;
        this.small = small;
    }
}
