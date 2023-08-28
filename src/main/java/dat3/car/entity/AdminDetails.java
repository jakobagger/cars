package dat3.car.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AdminDetails {
    @CreationTimestamp
    protected LocalDateTime created;
    @UpdateTimestamp
    protected LocalDateTime edited;
}
