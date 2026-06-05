package com.spiritualfamily.backend.entity.gallery;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "albums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 3000)
    private String description;

    @Builder.Default
    private LocalDateTime createdAt =
            LocalDateTime.now();

    @OneToMany(mappedBy = "album",
            cascade = CascadeType.ALL)
    private List<Photo> photos;
}