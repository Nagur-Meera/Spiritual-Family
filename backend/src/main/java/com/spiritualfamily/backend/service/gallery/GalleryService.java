package com.spiritualfamily.backend.service.gallery;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spiritualfamily.backend.dto.gallery.AlbumRequest;
import com.spiritualfamily.backend.dto.gallery.PhotoRequest;
import com.spiritualfamily.backend.entity.gallery.Album;
import com.spiritualfamily.backend.entity.gallery.Photo;
import com.spiritualfamily.backend.repository.gallery.AlbumRepository;
import com.spiritualfamily.backend.repository.gallery.PhotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final AlbumRepository albumRepository;

    private final PhotoRepository photoRepository;

    public Album createAlbum(
            AlbumRequest request
    ) {

        Album album =
                Album.builder()
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .build();

        return albumRepository.save(album);
    }

    public Photo addPhoto(
            PhotoRequest request
    ) {

        Album album =
                albumRepository.findById(
                        request.getAlbumId()
                ).orElseThrow();

        Photo photo =
                Photo.builder()
                        .photoUrl(request.getPhotoUrl())
                        .caption(request.getCaption())
                        .album(album)
                        .build();

        return photoRepository.save(photo);
    }

    public List<Album> getAlbums() {

        return albumRepository.findAll();
    }

    public List<Photo> getPhotosByAlbum(
            Long albumId
    ) {

        return photoRepository.findByAlbumId(albumId);
    }

    public void deleteAlbum(
            Long id
    ) {

        albumRepository.deleteById(id);
    }

    public void deletePhoto(
            Long id
    ) {

        photoRepository.deleteById(id);
    }
}