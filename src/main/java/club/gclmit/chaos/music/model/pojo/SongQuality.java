package club.gclmit.chaos.music.model.pojo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.*;
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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SongQuality implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 歌曲 id
     */
    private String songId;

    /**
     * 歌曲 mid
     */
    private String songMid;

    /**
     * size_192ogg
     */
    private String ogg;

    /**
     * M500 size_128mp3
     */
    private String size128;

    /**
     * M800 size_320mp3
     */
    private String size320;

    /**
     * size_ape
     */
    private String ape;

    /**
     * flac
     */
    private String flac;


    public SongQuality(String songId, String songMid, String ogg, String size128, String size320, String ape, String flac) {
        this.songId = songId;
        this.songMid = songMid;
        this.ogg = ogg;
        this.size128 = size128;
        this.size320 = size320;
        this.ape = ape;
        this.flac = flac;
    }
}
