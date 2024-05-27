package com.prueba.demo.repository;


import com.prueba.demo.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByNombre(String nombre);
    void deleteByNombre(String nombre);
}

