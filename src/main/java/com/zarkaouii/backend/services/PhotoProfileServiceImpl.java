package com.zarkaouii.backend.services;

import com.zarkaouii.backend.entities.Employee;
import com.zarkaouii.backend.entities.ProfilePhoto;
import com.zarkaouii.backend.repositories.EmployeeRepository;
import com.zarkaouii.backend.repositories.ProfilePhotoRepository;
import com.zarkaouii.backend.utils.ProfilePhotoUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhotoProfileServiceImpl implements PhotoProfileService{

    private final EmployeeRepository employeeRepository;
    private final ProfilePhotoRepository profilePhotoRepository;
    @Override
    public void uploadProfilePhoto(MultipartFile profilePhoto, Long id) throws IOException {
        Employee emp = employeeRepository.findById(id).orElse(null);
        // Careful with this emp when null !!!
        var photo = ProfilePhoto
                .builder()
                .name(profilePhoto.getOriginalFilename())
                .type(profilePhoto.getContentType())
                .image(ProfilePhotoUtility.compressImage(profilePhoto.getBytes()))
                .employee(emp)
                .build();
        this.profilePhotoRepository.save(photo);
        // update the employee in question :
        emp.setProfilePhoto(photo);
        employeeRepository.save(emp);
    }

    @Override
    public ProfilePhoto getProfilePhoto(Long id) {

        final Optional<ProfilePhoto> pPhoto = profilePhotoRepository.findProfilePhotoByEmployeeId(id);

        var profilePhoto = new ProfilePhoto();

        if(pPhoto.isPresent()) {
            profilePhoto = ProfilePhoto.builder()
                    .name(pPhoto.get().getName())
                    .type(pPhoto.get().getType())
                    .image(ProfilePhotoUtility.decompressImage(pPhoto.get().getImage()))
                    .build();
        }
        return profilePhoto;
    }


}
