package com.zarkaouii.backend.repositories;

import com.zarkaouii.backend.entities.ProfilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Long> {

    public Optional<ProfilePhoto> findProfilePhotoByEmployeeId(Long id);
}
