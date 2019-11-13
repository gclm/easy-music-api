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
    TOPLIst4(4),
    TOPLIst26(26),
    TOPLIst27(27),
    TOPLIst62(62),
    MVTOPLIST(999);

    private Integer code;

    TopList(Integer code) {
        this.code = code;
    }
}