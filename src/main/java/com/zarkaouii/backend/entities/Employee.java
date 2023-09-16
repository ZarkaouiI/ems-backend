package com.zarkaouii.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
//    private String phoneNumber;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private ProfilePhoto profilePhoto;

    // When post an employee => make two req : one for adding the employee and the other for uploading the image.


    public ProfilePhoto getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhotos(ProfilePhoto profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
