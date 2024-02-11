package org.studyeasy.SpringRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studyeasy.SpringRest.model.Album;
import org.studyeasy.SpringRest.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
   List<Photo> findByAlbum_id(long id);
}
