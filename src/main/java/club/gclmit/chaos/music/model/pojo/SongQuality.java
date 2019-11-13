package club.gclmit.chaos.music.model.pojo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * music_song_quality
 * </p>
 *
 * @author 孤城落寞
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music_song_quality")
public class SongQuality implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 歌曲 mid
     */
    private String songMid;

    /**
     * size_48m4a
     */
    private Integer m4a;

    /**
     * size_192ogg
     */
    private Integer ogg;

    /**
     * M500 size_128mp3
     */
    private Integer mp3;

    /**
     * M800 size_320mp3
     */
    @TableField("largeMp3")
    private Integer largeMp3;

    /**
     * size_ape
     */
    private Integer ape;

    /**
     * flac
     */
    private Integer flac;

}
