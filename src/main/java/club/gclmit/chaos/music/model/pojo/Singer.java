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
 * music_singer
 * </p>
 *
 * @author 孤城落寞
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music_singer")
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 Id

     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 歌手 Id
     */
    private String singerId;

    /**
     * 歌手mid
     */
    private String singerMid;

    /**
     * 歌手名字
     */
    private String singerName;

    /**
     * 歌手图片
     */
    private String singerPic;

}
