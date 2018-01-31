package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {
    private String url;
    private RestOperations rest;
    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType =
            new ParameterizedTypeReference<List<AlbumInfo>>() {};

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.url = albumsUrl;
        this.rest = restOperations;
    }

    public String index(Map<String, Object> model) {
        return rest.getForObject(url, String.class);
    }

    public void addAlbum(AlbumInfo album) {
        rest.postForEntity(url, album, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        return rest.getForEntity(url + "/" + id, AlbumInfo.class).getBody();
    }

    public List<AlbumInfo> getAlbums() {
        return rest.exchange(url, GET, null, albumListType).getBody();
    }
}
