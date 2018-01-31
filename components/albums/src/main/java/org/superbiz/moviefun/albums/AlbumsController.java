package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
    private final AlbumsRepository repo;

    public AlbumsController(AlbumsRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public void addAlbum(@RequestBody Album album) {
        repo.addAlbum(album);
    }

    @GetMapping
    public List<Album> index() {
        return repo.getAlbums();
    }

    @GetMapping("/{albumId}")
    public Album details(@PathVariable Long albumId) {
        return repo.find(albumId);
    }
}
