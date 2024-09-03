package com.example.SpringBootTest.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ImageResult", schema = "imagelist")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgName;

    @Column(nullable = false)
    private String prediction;

    @Column(nullable = false)
    private String imgPath;
}
