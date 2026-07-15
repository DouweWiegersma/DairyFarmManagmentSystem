package nl.wiegersma.dairyfarm.repositories;

import nl.wiegersma.dairyfarm.models.CowPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CowPhotoRepository extends JpaRepository<CowPhoto, Long> {
}
