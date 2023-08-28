package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
/////
@Entity
public class Car extends AdminDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "car_brand", nullable = false, length = 50)
    private String brand;

    @Column(name="car_model", nullable = false, length = 60)
    private String model;

    @Column(name="rental_price_day")
    private double pricePrDay;

    @Column(name="max_discount")
    private int bestDiscount;


    public Car(String brand, String model, double pricePrDay, int bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }
}
