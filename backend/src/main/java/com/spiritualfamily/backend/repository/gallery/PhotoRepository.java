package com.spiritualfamily.backend.repository.gallery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spiritualfamily.backend.entity.gallery.Photo;

public interface PhotoRepository
        extends JpaRepository<Photo, Long> {

    List<Photo> findByAlbumId(Long albumId);
}