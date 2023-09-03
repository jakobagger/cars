package dat3.car.dto;

import dat3.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {
    private int id;
    private String brand;
    private String model;
    private double pricePrDay;
    private int bestDiscount;


    public CarRequest(Car c){
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
    }

    public static Car getCarEntity(CarRequest c) {
        return new Car(c.brand, c.model, c.pricePrDay, c.bestDiscount);
    }
}
