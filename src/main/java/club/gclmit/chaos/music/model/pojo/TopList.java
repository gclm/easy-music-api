package club.gclmit.chaos.music.model.pojo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/14 20:39
 * @version: V1.0
 * @since 1.8
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TopList {

    /**
     *  榜单 ID
     */
    private String topId;

    /**
     *  榜单详情
     */
    private String title;

    /**
     *  榜单描述
     */
    private String intro;

    /**
     *  更新时间
     */
    private String updateTime;


    private String headPicUrl;

    private String frontPicUrl;

    private String mbFrontPicUrl;

    private String mbHeadPicUrl;

}
