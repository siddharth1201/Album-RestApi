package org.studyeasy.SpringRest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyeasy.SpringRest.model.Photo;
import org.studyeasy.SpringRest.repository.PhotoRepository;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    public Photo save(Photo photo){
        return photoRepository.save(photo);
    }

    public Optional<Photo> findById(long id){
        return photoRepository.findById(id);
    }

     public List<Photo> findByAlbumId(long id){
        return photoRepository.findByAlbum_id(id);
    }
}
