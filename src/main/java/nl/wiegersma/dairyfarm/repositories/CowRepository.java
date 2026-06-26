package nl.wiegersma.dairyfarm.repositories;

import nl.wiegersma.dairyfarm.models.Cow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CowRepository extends JpaRepository<Cow, Long> {
}
