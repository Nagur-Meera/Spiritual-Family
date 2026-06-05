package com.spiritualfamily.backend.repository.gallery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.gallery.Album;

public interface AlbumRepository
        extends JpaRepository<Album, Long> {
}