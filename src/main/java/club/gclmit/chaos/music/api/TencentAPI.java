package club.gclmit.chaos.music.api;

import lombok.Getter;
import org.springframework.http.HttpMethod;

/**
 * <p>
 * 腾讯音乐API
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/7 19:41
 * @version: V1.0
 */
@Getter
public enum  TencentAPI {

    MUSIC_LIST_DATE("https://u.y.qq.com/cgi-bin/musicu.fcg",HttpMethod.POST),
    MUSIC_LIST("https://u.y.qq.com/cgi-bin/musicu.fcg",HttpMethod.POST),
    VKEY("https://u.y.qq.com/cgi-bin/musicu.fcg?g_tk=5381&format=json&inCharset=utf8&outCharset=utf-8&data=%s",HttpMethod.GET),
    SEARCH("https://c.y.qq.com/soso/fcgi-bin/client_search_cp?g_tk=806131863&format=json&inCharset=utf8&outCharset=utf-8&p=1&n=20&w=%s",HttpMethod.GET),
    USER_HEAD_IMG("https://u.y.qq.com/cgi-bin/musicu.fcg?loginUin=1719982754&inCharset=utf8&outCharset=GB2312&data=%s",HttpMethod.GET),
    SONG_INFO("https://u.y.qq.com/cgi-bin/musicu.fcg?g_tk=5381&format=json&inCharset=utf8&outCharset=utf-8&data=%s",HttpMethod.GET),
    SINGER_LIST("https://u.y.qq.com/cgi-bin/musicu.fcg?format=json&inCharset=utf-8&outCharset=utf-8&data=%s",HttpMethod.GET),
    PIC_LIST("https://c.y.qq.com/photocgi/fcgi-bin/qm_search_photo.fcg?songid=%s",HttpMethod.GET),
    LYRIC("https://c.y.qq.com/lyric/fcgi-bin/fcg_query_lyric_yqq.fcg?musicid=%s&g_tk=5381&format=json&inCharset=utf8&outCharset=utf-8",HttpMethod.GET);
//            lyric
     /**
      * API URL
      */
    private String url;

     /**
      * API 请求类型
      */
    private HttpMethod method;

    TencentAPI(String url,HttpMethod method) {
        this.method = method;
        this.url = url;
    }
}
