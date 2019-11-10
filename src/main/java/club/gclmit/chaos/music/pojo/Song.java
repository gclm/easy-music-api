package club.gclmit.chaos.music.pojo;

import lombok.Data;

/**
 * <p>
 *  歌曲列表
 * </p>
 *
 * @author: gclm
 * @date: 2019/11/9 0:20
 * @version: V1.0
 */
@Data
public class Song {

    /**
     * 数据库自增
     */
    private String id;

     /**
      * 歌曲 Id
      */
    private String songId;

    /**
     * 歌曲 mid
     */
    private String songMid;

    /**
     * 歌名
     */
    private String name;

    /**
     * 专辑 id
     */
    private String albumId;

    /**
     * 专辑 Mid
     */
    private String albumMid;

    /**
     * 专辑名字
     */
    private String albumName;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 歌曲图片
     */
    private String pic;

    /**
     * 歌词
     */
    private String lrc;

    /**
     * 歌曲链接
     */
    private String url;

    public Song(String songId, String songMid, String name, String albumId, String albumMid, String albumName, String singer) {
        this.songId = songId;
        this.songMid = songMid;
        this.name = name;
        this.albumId = albumId;
        this.albumMid = albumMid;
        this.albumName = albumName;
        this.singer = singer;
    }
}
