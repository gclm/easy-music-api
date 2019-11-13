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
 * music_song
 * </p>
 *
 * @author 孤城落寞
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music_song")
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 歌曲 Id
     */
    private String songId;

    /**
     * 歌曲 mid
     */
    private String songMid;

    /**
     * 歌名
     */
    private String name;

    /**
     * 专辑 id
     */
    private String albumId;

    /**
     * 专辑 Mid
     */
    private String albumMid;

    /**
     * 专辑名字
     */
    private String albumName;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 歌曲图片
     */
    private String pic;

    /**
     * 歌词
     */
    private String lrc;

    /**
     * 歌曲链接
     */
    private String url;

    public Song(String songId, String songMid, String name, String albumId, String albumMid, String albumName, String singer) {
        this.songId = songId;
        this.songMid = songMid;
        this.name = name;
        this.albumId = albumId;
        this.albumMid = albumMid;
        this.albumName = albumName;
        this.singer = singer;
    }
}
