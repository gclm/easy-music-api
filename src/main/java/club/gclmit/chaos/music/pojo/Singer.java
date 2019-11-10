package club.gclmit.chaos.music.pojo;

import lombok.Data;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/9 0:21
 * @version: V1.0
 */
@Data
public class Singer {

    /**
     * 歌手 Id
     */
    private String singer_id;

    /**
     * 歌手mid
     */
    private String singer_mid;
    /**
     * 歌手名字
     */
    private String singer_name;
    /**
     * 歌手图片
     */
    private String singer_pic;
}
