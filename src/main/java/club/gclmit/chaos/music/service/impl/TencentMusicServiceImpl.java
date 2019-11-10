package club.gclmit.chaos.music.service.impl;

import club.gclmit.chaos.music.service.TencentMusicService;
import club.gclmit.chaos.music.api.TencentAPI;
import club.gclmit.chaos.music.pojo.Pic;
import club.gclmit.chaos.music.pojo.Song;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jndi.toolkit.url.Uri;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * <p>
 * 腾讯音乐服务
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/9 17:27
 * @version: V1.0
 */
@Slf4j
@Service
public class TencentMusicServiceImpl implements TencentMusicService {

    @Autowired
    private RestTemplate restTemplate;

    private HttpEntity httpEntity = null;

    /**
     * <p>
     *  初始化构造器，设置默认的 HttpEntity
     * </p>
     *
     * @author gclm
     * @date 2019/11/9 19:29
     * @throws
     */
    public TencentMusicServiceImpl() {
        MultiValueMap<String,Object> header = new LinkedMultiValueMap<>();
        header.add(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
        header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        header.add(HttpHeaders.REFERER,"https://y.qq.com/");
        httpEntity = new HttpEntity(header);
    }

    @Override
    public Pic getPic(String songId) throws DocumentException {
        String url = String.format(TencentAPI.PIC_LIST.getUrl(),songId);

        /**
         * 发送请求处理响应数据
         */
        String result = request(url,TencentAPI.SEARCH.getMethod());
        String xml = result.replace("<!--","").replace("-->","");

//        log.info("响应:{}\t处理XML:{}",result,xml);

        /**
         * 解析 XML 文档
         */
        Document document = DocumentHelper.parseText(xml);
        String sl = document.selectSingleNode("//url/a_sl").getText();
        String large = document.selectSingleNode("//url/a_l").getText();
        String medium = document.selectSingleNode("//url/a_m").getText();
        String small = document.selectSingleNode("//url/a_s").getText();

        return new Pic(sl,large, medium, small);
    }

    @Override
    public List<Song> getSongList(String type) {
        return null;
    }

    /**
     * <p>
     *  获取歌词
     * </p>
     *
     * @author gclm
     * @param: songId
     * @date 2019/11/10 11:38
     * @return: java.lang.String
     * @throws
     */
    @Override
    public String getLrc(String songId) {
        String url = String.format(TencentAPI.LYRIC.getUrl(), songId);

        /**
         * 获取响应结果进行JSON 处理，因为响应的为 Base64,所以需要进行解码
         */
        String result = request(url,TencentAPI.SEARCH.getMethod());
        String lyric = JSONObject.parseObject(result).getString("lyric");

        log.info("响应:{}\nlyric:{}",result,lyric);

        return new String(Base64.getDecoder().decode(lyric));
    }

    /**
     * <p>
     * 获取歌曲详情
     * </p>
     *
     * @author gclm
     * @param: songMid
     * @param: songId
     * @date 2019/11/10 14:53
     * @return: club.gclmit.chaos.music.pojo.Song
     * @throws
     */
    @Override
    public Song getSongInfo(String songMid, String songId) {
        /**
         * 拼接请求url,交给UriTemplate 进行转码
         */
        String param = "{\"comm\":{\"ct\":24,\"cv\":0},\"songinfo\":{\"method\":\"get_song_detail_yqq\",\"param\":{\"song_type\":0,\"song_mid\":\"%s\",\"song_id\":%s},\"module\":\"music.pf_song_detail_svr\"}}";
        String data = String.format(param,songMid,songId);
        URI uri = new UriTemplate(TencentAPI.SONG_INFO.getUrl()).expand(data);

        String result = request(uri, TencentAPI.SONG_INFO.getMethod());

        /**
         * 解析处理响应的JOSN 数据
         */
        JSONObject track_info = JSONObject.parseObject(result).getJSONObject("songinfo").getJSONObject("data").getJSONObject("track_info");
        JSONObject album = track_info.getJSONObject("album");
        JSONArray singers = track_info.getJSONArray("singer");


        /**
         * 拼装 Song 对象
         */
        String singer = getSinger(singers);
        String name = track_info.getString("name");
        String albumId = album.getString("id");
        String albumMid = album.getString("mid");
        String albumName = album.getString("name");

        return new Song(songId,songMid,name,albumId,albumMid,albumName,singer);
    }



    @Override
    public String getSong(String songMid) {
        return null;
    }

    /**
     * <p>
     *  搜索歌曲
     * </p>
     *
     * @summary httpdoc 方法注解
     * @author gclm
     * @param: keyword
     * @date 2019/11/10 15:13
     * @return: java.util.List<club.gclmit.chaos.music.pojo.Song>
     * @throws
     */
    @Override
    public List<Song> search(String keyword) {
        String url = String.format(TencentAPI.SEARCH.getUrl(), keyword);
        String result = request(url,TencentAPI.SEARCH.getMethod());
        log.info("响应：{}",result);
        List<Song> songList = new ArrayList<>();

        /**
         * 解析 JSON 拼装成 Song
         */
        JSONArray songs = JSONObject.parseObject(result).getJSONObject("data").getJSONObject("song").getJSONArray("list");
        for (int i = 0; i < songs.size(); i++) {
            JSONObject songJson = songs.getJSONObject(i);
            String albumId = songJson.getString("albumid");
            String albumMid = songJson.getString("albummid");
            String albumName = songJson.getString("albumname");
            String songId = songJson.getString("songid");
            String songMid = songJson.getString("songmid");
            String songName = songJson.getString("songname");
            String singer = getSinger(songJson.getJSONArray("singer"));
            songList.add(new Song(songId,songMid,songName,albumId,albumMid,albumName,singer));
        }

        return songList;
    }

    /**
     * <p>
     *  获取歌手名字
     * </p>
     *
     * @author gclm
     * @param: singers
     * @date 2019/11/10 15:08
     * @return: java.lang.String
     * @throws
     */
    private String getSinger(JSONArray singers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < singers.size(); i++) {
            sb.append(singers.getJSONObject(i).getString("name")).append("/");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * <p>
     *  restTemplate 封装
     * </p>
     *
     * @author gclm
     * @param: url
     * @param: httpMethod
     * @date 2019/11/10 12:54
     * @return: java.lang.String
     * @throws
     */
    private String request(String url, HttpMethod httpMethod){

       return restTemplate.exchange(url, httpMethod, httpEntity, String.class).getBody();
    }

    /**
     * <p>
     *  restTemplate 封装
     * </p>
     *
     * @author gclm
     * @param: url
     * @param: httpMethod
     * @date 2019/11/10 14:30
     * @return: java.lang.String
     * @throws
     */
    private String request(URI url, HttpMethod httpMethod){

        return restTemplate.exchange(url, httpMethod, httpEntity, String.class).getBody();
    }
}
