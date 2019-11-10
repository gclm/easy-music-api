package club.gclmit.chaos.music.service;

import club.gclmit.chaos.music.Service.TencentMusicService;
import club.gclmit.chaos.music.pojo.Pic;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
