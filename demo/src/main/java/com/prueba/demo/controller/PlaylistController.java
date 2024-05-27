package com.prueba.demo.controller;

import com.prueba.demo.model.Playlist;
import com.prueba.demo.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<?> addPlaylist(@RequestBody Playlist playlist) {
        if (playlist.getNombre() == null) {
            return ResponseEntity.badRequest().body("Nombre de la lista no es válido");
        }
        Playlist createdPlaylist = playlistService.createPlaylist(playlist);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{nombre}")
                .buildAndExpand(createdPlaylist.getNombre())
                .toUri();
        return ResponseEntity.created(location).body(createdPlaylist);
    }

    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> getPlaylist(@PathVariable String nombre) {
        Optional<Playlist> playlist = playlistService.getPlaylistByName(nombre);
        if (playlist.isPresent()) {
            return ResponseEntity.ok(playlist.get());
        } else {
            return ResponseEntity.status(404).body("Lista no encontrada");
        }
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> deletePlaylist(@PathVariable String nombre) {
        Optional<Playlist> playlist = playlistService.getPlaylistByName(nombre);
        if (playlist.isPresent()) {
            playlistService.deletePlaylist(nombre);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body("Lista no encontrada");
        }
    }
}
