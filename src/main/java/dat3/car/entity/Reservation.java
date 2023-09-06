package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter

@Entity
public class Reservation extends AdminDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private LocalDate reservationDate;

    @ManyToOne
    Member member;

    @ManyToOne
    Car car;

    public Reservation(LocalDate reservationDate, Member member, Car car) {
        this.reservationDate = reservationDate;
        this.member = member;
        this.car = car;
        member.addReservation(this);
        car.addReservation(this);
    }
}
