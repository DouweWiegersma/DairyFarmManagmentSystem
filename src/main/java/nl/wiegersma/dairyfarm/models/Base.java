package nl.wiegersma.dairyfarm.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDate createdAt;
    @LastModifiedDate
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = LocalDate.now();
    }
}
