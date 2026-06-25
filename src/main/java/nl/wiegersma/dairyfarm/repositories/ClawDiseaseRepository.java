package nl.wiegersma.dairyfarm.repositories;

import nl.wiegersma.dairyfarm.models.ClawDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClawDiseaseRepository extends JpaRepository<ClawDisease, Long> {


}
