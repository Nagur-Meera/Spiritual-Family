package com.spiritualfamily.backend.dto.gallery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoRequest {

    private String photoUrl;

    private String caption;

    private Long albumId;
}