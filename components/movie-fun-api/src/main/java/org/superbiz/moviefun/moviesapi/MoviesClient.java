package org.superbiz.moviefun.moviesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class MoviesClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String moviesApiUrl;
    private final RestOperations moviesRestOps;
    private static ParameterizedTypeReference<List<MovieInfo>> movieListType =
            new ParameterizedTypeReference<List<MovieInfo>>() {};

    public MoviesClient(String url, RestOperations ops) {
        this.moviesApiUrl = url;
        this.moviesRestOps = ops;
    }

    public MovieInfo find(Long id) {
        return null;
    }

    public void addMovie(MovieInfo movie) {
        moviesRestOps.postForEntity(moviesApiUrl, movie, MovieInfo.class);
    }

    public void deleteMovieId(long id) {
        moviesRestOps.delete(moviesApiUrl + "/" + id);
    }

    public List<MovieInfo> getMovies() {
        return moviesRestOps.exchange(moviesApiUrl, GET, null, movieListType).getBody();
    }

    public List<MovieInfo> findAll(int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesApiUrl)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return moviesRestOps.exchange(builder.toUriString(), GET, null, movieListType).getBody();
    }

    public int countAll() {
        return moviesRestOps.getForObject(moviesApiUrl + "/count", Integer.class);
    }

    public int count(String field, String key) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesApiUrl + "/count")
                .queryParam("field", field)
                .queryParam("key", key);

        return moviesRestOps.getForObject(builder.toUriString(), Integer.class);
    }

    public List<MovieInfo> findRange(String field, String key, int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesApiUrl)
                .queryParam("field", field)
                .queryParam("key", key)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return moviesRestOps.exchange(builder.toUriString(), GET, null, movieListType).getBody();
    }
}
