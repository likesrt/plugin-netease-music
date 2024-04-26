package la.moony.music.service;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Mono;

public interface MusicService {
    Mono<JsonNode> neteaseSong(String id);

    Mono<JsonNode> neteaseComments(String id);

    Mono<JsonNode> neteaseSongMedia(String songId);

    Mono<JsonNode> getBasicConfig();

    Mono<JsonNode> neteaseAlbum(String albumId);

    Mono<JsonNode> neteasePlaylist(String playlistId);

    Mono<JsonNode> neteaseRadio(String id);
}
