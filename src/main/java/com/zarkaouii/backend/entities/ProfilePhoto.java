package com.zarkaouii.backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.java.BlobJavaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Blob;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "profile_photos")
public class ProfilePhoto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image", unique = false, nullable = true, length = 100000)
    private byte[] image;
    private String name;
    private String type;

    @OneToOne
    private Employee employee;
}
