package nl.wiegersma.dairyfarm.repositories;

import nl.wiegersma.dairyfarm.models.ClawTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ClawTreatmentRepository extends JpaRepository<ClawTreatment, Long> {

    List<ClawTreatment> findByDiseaseId(Long clawDiseases);



}
