package nl.wiegersma.dairyfarm.repositories;

import nl.wiegersma.dairyfarm.models.MedicationInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationInventoryRepository extends JpaRepository<MedicationInventory, Long> {
}
