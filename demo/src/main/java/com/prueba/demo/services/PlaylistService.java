package com.prueba.demo.services;

import com.prueba.demo.model.Playlist;
import com.prueba.demo.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Optional<Playlist> getPlaylistByName(String nombre) {
        return playlistRepository.findByNombre(nombre);
    }

    public void deletePlaylist(String nombre) {
        playlistRepository.deleteByNombre(nombre);
    }
}

