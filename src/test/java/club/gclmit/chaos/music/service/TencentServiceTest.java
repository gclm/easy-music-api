package club.gclmit.chaos.music.service;


import club.gclmit.chaos.music.model.pojo.Pic;
import club.gclmit.chaos.music.model.pojo.Song;
import club.gclmit.chaos.music.model.pojo.SongQuality;
import club.gclmit.chaos.music.model.pojo.TopList;
import com.alibaba.fastjson.JSON;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 腾讯音乐服务测试
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/10 10:41
 * @version: V1.0
 */
@SpringBootTest
public class TencentServiceTest {

    @Autowired
    private TencentMusicService tencentMusicService;

    @Test
    public void picTest() throws DocumentException {
        Pic pic = tencentMusicService.getPic("121577504");
        System.out.println(pic);
    }

    @Test
    public void lyricTest(){
        String lrc = tencentMusicService.getLrc("121577504");
        System.out.println(lrc);
    }

    @Test
    public void songInfoTest(){
        Song songInfo = tencentMusicService.getSongInfo("002COmzJ0SPZMl", "244499239");
        System.out.println(songInfo);

        Song song = tencentMusicService.getSongInfo("002COmzJ0SPZMl");
        System.out.println(song);
    }

    @Test
    public void searchList(){
        List<Song>  song = tencentMusicService.search("爱");
        for (Song song1 : song){
            System.out.println(song1);
        }
    }

    @Test
    public void userHeaderImgTest(){
        Map<String, String> stringStringMap = tencentMusicService.userHeadImg("1719982754");
        System.out.println(JSON.toJSON(stringStringMap));
    }

    @Test
    public void getTopListTest(){
        List<TopList> topLists = tencentMusicService.getTopList();

        for (TopList top: topLists) {
            System.out.println(top);
        }
    }

    @Test
    public void getSongSizeTest() throws DocumentException {
        SongQuality quality = tencentMusicService.getSongSize("244499239");
        System.out.println(quality);
    }

    @Test
    public void getVkeyTest(){
        String purl = tencentMusicService.getPurl("002COmzJ0SPZMl");
        System.out.println(purl);
    }

    @Test
    public void getOggVkeyTest(){
        String vkey = tencentMusicService.getOggVkey("000a2bMl33mXkU");
        System.out.println(vkey);
    }

    @Test
    public void getMusicUrlTest(){
        String songmid = "002COmzJ0SPZMl";

//        System.out.println("flac:\n" + tencentMusicService.getMusicUrl(songmid,"flac"));
//        System.out.println("ape:\n" + tencentMusicService.getMusicUrl(songmid,"ape"));
//        System.out.println("320:\n" + tencentMusicService.getMusicUrl(songmid,"320"));
//        System.out.println("mgg:\n" + tencentMusicService.getMusicUrl(songmid,"mgg"));
//        System.out.println("128:\n" + tencentMusicService.getMusicUrl(songmid,"128"));
        System.out.println("m4a:\n" + tencentMusicService.getMusicUrl(songmid,"m4a"));
//        System.out.println("1280:\n" + tencentMusicService.getMusicUrl(songmid,"1280"));
    }

}
