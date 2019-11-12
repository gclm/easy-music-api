package club.gclmit.chaos.music.service;

import club.gclmit.chaos.music.pojo.Pic;
import club.gclmit.chaos.music.pojo.Song;
import com.alibaba.fastjson.JSON;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO
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
}
