package la.moony.music.controller;

import com.fasterxml.jackson.databind.JsonNode;
import la.moony.music.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import run.halo.app.plugin.ApiVersion;

@ApiVersion("v1alpha1")
@RequestMapping("/music")
@RestController
@Slf4j
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }


    @GetMapping("/getBasicConfig")
    public Mono<JsonNode> getBasicConfig(){
        return musicService.getBasicConfig();
    }

    /**
     * 网易云单曲
     * @param id
     * @return
     */
    @GetMapping("/neteaseSong")
    public Mono<JsonNode> neteaseSong(@RequestParam("id") String id){
        return musicService.neteaseSong(id);
    }


    /**
     * 网易云歌曲评论
     * @param id
     * @return
     */
    @GetMapping("/neteaseComments")
    public Mono<JsonNode> neteaseComments(@RequestParam("id") String id){
        return musicService.neteaseComments(id);
    }

    /**
     * 网易云歌曲评论
     * @param songId 歌曲id
     * @return
     */
    @GetMapping("/neteaseSongMedia")
    public Mono<JsonNode> neteaseSongMedia(@RequestParam("songId") String songId){
        return musicService.neteaseSongMedia(songId);
    }

    /**
     * 网易云专辑
     * @return
     */
    @GetMapping("/neteaseAlbum")
    public Mono<JsonNode> neteaseAlbum(@RequestParam("albumId") String albumId){
        return musicService.neteaseAlbum(albumId);
    }

    /**
     * 网易云歌单
     * @return
     */
    @GetMapping("/neteasePlaylist")
    public Mono<JsonNode> neteasePlaylist(@RequestParam("playlistId") String playlistId){
        return musicService.neteasePlaylist(playlistId);
    }

    /**
     * 网易云电台
     * @return
     */
    @GetMapping("/neteaseRadio")
    public Mono<JsonNode> neteaseRadio(@RequestParam("id") String id){
        return musicService.neteaseRadio(id);
    }



}
