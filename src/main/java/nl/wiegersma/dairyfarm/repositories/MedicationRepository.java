package nl.wiegersma.dairyfarm.repositories;
import nl.wiegersma.dairyfarm.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
