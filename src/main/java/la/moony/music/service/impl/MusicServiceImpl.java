package la.moony.music.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Nonnull;
import la.moony.music.service.MusicService;
import la.moony.music.utils.NeteaseHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import run.halo.app.plugin.ReactiveSettingFetcher;

@Component
public class MusicServiceImpl  implements MusicService {

    private final Logger log = LoggerFactory.getLogger(MusicServiceImpl.class);

    private static final String NETEASE_API_SONG_URL = "http://music.163.com/api/song/detail/?id={id}&ids=%5B{id}%5D";

    private final NeteaseHttpClient neteaseHttpClient;

    private final ReactiveSettingFetcher settingFetcher;

    public MusicServiceImpl(NeteaseHttpClient neteaseHttpClient, ReactiveSettingFetcher settingFetcher) {
        this.neteaseHttpClient = neteaseHttpClient;
        this.settingFetcher = settingFetcher;
    }

    @Override
    public Mono<JsonNode> neteaseSong(String id) {
        String url = "http://music.163.com/api/song/detail/?id="+id+"&ids=%5B"+id+"%5D";
        JsonNode jsonNode = neteaseHttpClient.neteaseHttp(url);
        return getJsonNode(jsonNode);
    }

    @Override
    public Mono<JsonNode> neteaseComments(String id) {
        JsonNode jsonNode = neteaseHttpClient.comments(id);
        return getJsonNode(jsonNode);
    }

    @Override
    public Mono<JsonNode> neteaseSongMedia(String songId) {
        String url = "http://music.163.com/api/song/media?id="+songId;
        JsonNode jsonNode = neteaseHttpClient.neteaseHttp(url);
        return getJsonNode(jsonNode);
    }

    @Override
    public Mono<JsonNode> getBasicConfig() {
        return this.settingFetcher.get("basic");
    }

    @Override
    public Mono<JsonNode> neteaseAlbum(String albumId) {
        String url = "http://music.163.com/api/album/"+albumId;
        JsonNode jsonNode = neteaseHttpClient.neteaseHttp(url);
        return getJsonNode(jsonNode);
    }

    @Override
    public Mono<JsonNode> neteasePlaylist(String playlistId) {
        String url = "http://music.163.com/api/playlist/detail?id="+playlistId;
        JsonNode jsonNode = neteaseHttpClient.neteaseHttp(url);
        return getJsonNode(jsonNode);
    }

    @Override
    public Mono<JsonNode> neteaseRadio(String id) {
        String url = "http://music.163.com/api/dj/program/detail?id="+id;
        JsonNode jsonNode = neteaseHttpClient.neteaseHttp(url);
        return getJsonNode(jsonNode);
    }


    private Mono<JsonNode> getJsonNode(@Nonnull JsonNode jsonNode) {
        return Mono.just(jsonNode);
    }


}
