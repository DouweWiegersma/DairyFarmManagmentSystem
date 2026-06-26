package nl.wiegersma.dairyfarm.repositories;

import nl.wiegersma.dairyfarm.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
