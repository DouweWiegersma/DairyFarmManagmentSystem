package nl.wiegersma.dairyfarm.repositories;

import nl.wiegersma.dairyfarm.models.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

}
