package club.gclmit.chaos.music.model.constants;

/**
 * <p>
 * 榜单列表
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/10 17:02
 * @version: V1.0
 */
public enum TopList {

    /**
     *  流行指数榜
     */
    TOPLIst4(4),

    /**
     *  热歌榜
     */
    TOPLIst26(26),

    /**
     * 新歌榜
     */
    TOPLIst27(27),

    /**
     *  飙升榜
     */
    TOPLIst62(62);

    private Integer code;

    TopList(Integer code) {
        this.code = code;
    }
}