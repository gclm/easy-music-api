package club.gclmit.chaos.music.service.impl;

import club.gclmit.chaos.music.model.pojo.Pic;
import club.gclmit.chaos.music.model.pojo.Song;
import club.gclmit.chaos.music.model.pojo.TopList;
import club.gclmit.chaos.music.service.TencentMusicService;
import club.gclmit.chaos.music.api.TencentAPI;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.net.URL;
import java.util.*;

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

    /**
     * <p>
     *  获取图片列表
     * </p>
     *
     * @author gclm
     * @param: songId
     * @date 2019/11/10 15:16
     * @return: club.gclmit.chaos.music.pojo.Pic
     * @throws
     */
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

        return new Pic(songId,sl,large, medium, small);
    }


    @Override
    public List<Song> getTopList(Integer type){
        return null;
    }

    @Override
    public List<TopList> getTopList() {
        /**
         *  重新封装 HttpEntity
         */
        String data = "{\"req_0\":{\"module\":\"musicToplist.ToplistInfoServer\",\"method\":\"GetAll\",\"param\":{}},\"comm\":{\"g_tk\":5381,\"uin\":0,\"format\":\"json\",\"ct\":20,\"cv\":1724}}";
        httpEntity = new HttpEntity(data,httpEntity.getHeaders());

        String result = request(TencentAPI.MUSIC_LIST);
        JSONArray group = JSONObject.parseObject(result).getJSONObject("req_0").getJSONObject("data").getJSONArray("group");

        List<TopList> topList = new ArrayList<>();

        for (int i = 0; i < group.size(); i++) {
            JSONArray toplist = group.getJSONObject(i).getJSONArray("toplist");
            for (int j = 0; j < toplist.size(); j++) {
                JSONObject jsonObject = toplist.getJSONObject(j);
                String topId = jsonObject.getString("topId");
                String title = jsonObject.getString("titleShare");
                String intro = jsonObject.getString("intro");
                String updateTime = jsonObject.getString("updateTime");
                String headPicUrl = jsonObject.getString("headPicUrl");
                String frontPicUrl = jsonObject.getString("frontPicUrl");
                String mbFrontPicUrl = jsonObject.getString("mbFrontPicUrl");
                String mbHeadPicUrl = jsonObject.getString("mbHeadPicUrl");
                topList.add(new TopList(topId,title,intro,updateTime,headPicUrl,frontPicUrl,mbHeadPicUrl,mbHeadPicUrl));
            }
        }

        return topList;
    }


    @Override
    public List<Song> getPlayList(String id) {
        return null;
    }

    @Override
    public String getSong(String songMid) {
        return null;
    }

    /**
     * <p>
     *  获取歌词
     * </p>
     *
     * @author gclm
     * @param: songId/songMid
     * @date 2019/11/10 11:38
     * @return: java.lang.String
     * @throws
     */
    @Override
    public String getLrc(String songId) {

        String url = String.format(TencentAPI.LYRIC_ID.getUrl(), songId);

        /**
         * 获取响应结果进行JSON 处理，因为响应的为 Base64,所以需要进行解码
         */
        String result = request(url,TencentAPI.LYRIC_ID.getMethod());
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
        URI uri = getUri(TencentAPI.SONG_INFO.getUrl(),data);

        String result = request(uri, TencentAPI.SONG_INFO.getMethod());

        return songInfoBuilder(result);
    }

    /**
     * <p>
     *  获取歌曲信息
     * </p>
     *
     * @author gclm
     * @param: songMid
     * @date 2019/11/12 8:50
     * @return: club.gclmit.chaos.music.pojo.Song
     * @throws
     */
    @Override
    public Song getSongInfo(String songMid) {
        String param = "{\"comm\":{\"ct\":24,\"cv\":0},\"songinfo\":{\"method\":\"get_song_detail_yqq\",\"param\":{\"song_type\":0,\"song_mid\":\"%s\"},\"module\":\"music.pf_song_detail_svr\"}}";
        String data = String.format(param, songMid);
        URI uri = getUri(TencentAPI.SONG_INFO.getUrl(), data);
        String result = request(uri, TencentAPI.SONG_INFO.getMethod());
        return songInfoBuilder(result);
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
     *  获取用户头像和昵称
     * </p>
     *
     * @author gclm
     * @param: number
     * @date 2019/11/12 8:59
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @throws
     */
    @Override
    public Map<String, String> userHeadImg(String number) {
        String param = "{\"comm\":{\"ct\":24,\"cv\":0},\"vip\":{\"module\":\"userInfo.VipQueryServer\",\"method\":\"SRFVipQuery_V2\",\"param\":{\"uin_list\":[\"%s\"]}},\"base\":{\"module\":\"userInfo.BaseUserInfoServer\",\"method\":\"get_user_baseinfo_v2\",\"param\":{\"vec_uin\":[\"%s\"]}}}";
        String data = String.format(param, number, number);

        String result = request(getUri(TencentAPI.USER_HEAD_IMG.getUrl(), data), TencentAPI.USER_HEAD_IMG.getMethod());
        JSONObject qq = JSONObject.parseObject(result).getJSONObject("base").getJSONObject("data").getJSONObject("map_userinfo").getJSONObject(number);
        Map<String,String> map = new HashMap<>();
        map.put("uin",qq.getString("uin"));
        map.put("nickname",qq.getString("nick"));
        map.put("headUrl",qq.getString("headurl"));
        return map;
    }

    /**
     * <p>
     * 为不同的接口扩建通用的数据拼接方法
     * </p>
     *
     * @author gclm
     * @param: result
     * @date 2019/11/12 8:47
     * @return: club.gclmit.chaos.music.pojo.Song
     * @throws
     */
    private Song songInfoBuilder(String result) {

        /**
         * 解析处理响应的JOSN 数据
         */
        JSONObject track_info = JSONObject.parseObject(result).getJSONObject("songinfo").getJSONObject("data").getJSONObject("track_info");
        JSONObject album = track_info.getJSONObject("album");
        JSONArray singers = track_info.getJSONArray("singer");


        /**
         * 拼装 Song 对象
         */
        String songId = track_info.getString("id");
        String songMid = track_info.getString("mid");
        String singer = getSinger(singers);
        String name = track_info.getString("name");
        String albumId = album.getString("id");
        String albumMid = album.getString("mid");
        String albumName = album.getString("name");

        return new Song(songId,songMid,name,albumId,albumMid,albumName,singer);
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
     *  URL 编码器
     * </p>
     *
     * @author gclm
     * @param: url
     * @param: data
     * @date 2019/11/12 8:40
     * @return: java.net.URI
     * @throws
     */
    private URI getUri(String url,String data) {
        return new UriTemplate(url).expand(data);
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
     * @param: tencentAPI
     * @date 2019/11/14 21:12
     * @return: java.lang.String
     */
    private String request(TencentAPI tencentAPI){
        return restTemplate.exchange(tencentAPI.getUrl(), tencentAPI.getMethod(), httpEntity, String.class).getBody();
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
