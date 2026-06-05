package com.spiritualfamily.backend.controller.gallery;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spiritualfamily.backend.dto.gallery.AlbumRequest;
import com.spiritualfamily.backend.dto.gallery.PhotoRequest;
import com.spiritualfamily.backend.entity.gallery.Album;
import com.spiritualfamily.backend.entity.gallery.Photo;
import com.spiritualfamily.backend.service.gallery.GalleryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService service;

    @PostMapping("/albums")
    public Album createAlbum(
            @RequestBody AlbumRequest request
    ) {

        return service.createAlbum(request);
    }

    @PostMapping("/photos")
    public Photo addPhoto(
            @RequestBody PhotoRequest request
    ) {

        return service.addPhoto(request);
    }

    @GetMapping("/albums")
    public List<Album> getAlbums() {

        return service.getAlbums();
    }

    @GetMapping("/photos/{albumId}")
    public List<Photo> getPhotosByAlbum(
            @PathVariable Long albumId
    ) {

        return service.getPhotosByAlbum(albumId);
    }

    @DeleteMapping("/albums/{id}")
    public String deleteAlbum(
            @PathVariable Long id
    ) {

        service.deleteAlbum(id);

        return "Album Deleted";
    }

    @DeleteMapping("/photos/{id}")
    public String deletePhoto(
            @PathVariable Long id
    ) {

        service.deletePhoto(id);

        return "Photo Deleted";
    }
}