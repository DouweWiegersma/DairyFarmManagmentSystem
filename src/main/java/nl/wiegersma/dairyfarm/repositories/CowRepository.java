package nl.wiegersma.dairyfarm.repositories;

import nl.wiegersma.dairyfarm.models.Cow;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CowRepository extends JpaRepository<Cow, Long> {

    List<Cow> findAllByOrderByCowNumberAsc();

    List<Cow> findAllByOrderByCowNumberDesc();
}
