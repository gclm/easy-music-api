package club.gclmit.chaos.music.service;

import club.gclmit.chaos.music.model.constants.TopList;
import club.gclmit.chaos.music.model.pojo.Pic;
import club.gclmit.chaos.music.model.pojo.Song;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 音乐服务接口
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/9 17:18
 * @version: V1.0
 */
public interface TencentMusicService {

    /**
     * 获取图片列表
     */
    public Pic getPic(String songId) throws DocumentException;

    /**
     * 获取排行榜
     */
    public  List<Song> getTopList(TopList type);

    /**
     * 获取歌单
     */
    public List<Song> getPlayList(String id);

    /**
     * 获取歌词
     */
    public  String getLrc(String songId);

    /**
     * 获取歌曲详情
     */
    public Song getSongInfo(String songMid, String songId);

    /**
     * 获取歌曲详情
     */
    public Song getSongInfo(String songMid);

    /**
     * 获取歌曲
     */
    public String getSong(String songMid);

    /**
     * 搜索列表
     */
    public List<Song> search(String keyword);

    /**
     * 用户头像
     */
    public Map<String,String> userHeadImg(String number);
}
