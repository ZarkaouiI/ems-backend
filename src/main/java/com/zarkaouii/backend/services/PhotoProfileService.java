package com.zarkaouii.backend.services;

import com.zarkaouii.backend.entities.ProfilePhoto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface PhotoProfileService {
    void uploadProfilePhoto(MultipartFile profilePhoto, Long id) throws IOException;
    ProfilePhoto getProfilePhoto(Long id);
}
