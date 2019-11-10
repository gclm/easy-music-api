package club.gclmit.chaos.music.Service.impl;

import club.gclmit.chaos.music.Service.TencentMusicService;
import club.gclmit.chaos.music.api.TencentAPI;
import club.gclmit.chaos.music.pojo.Pic;
import club.gclmit.chaos.music.pojo.Song;
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

import java.util.Base64;
import java.util.List;

/**
 * <p>
 * TODO
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

        return Base64.getDecoder().decode(lyric).toString();
    }

    @Override
    public String getSongInfo(String songMid, String songId) {
        return null;
    }

    @Override
    public String getSong(String songMid) {
        return null;
    }

    @Override
    public List<Song> searchList(String keyword) {
        String url = String.format(TencentAPI.SEARCH.getUrl(), keyword);
        String result = request(url,TencentAPI.SEARCH.getMethod());
        log.info("响应：{}",result);
        return null;
    }

    private String request(String url, HttpMethod httpMethod){
       return restTemplate.exchange(url, httpMethod, httpEntity, String.class).getBody();
    }
}
