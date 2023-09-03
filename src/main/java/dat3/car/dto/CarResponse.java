package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    private int id;
    private String brand;
    private String model;
    private double pricePrDay;
    private int bestDiscount;

    public CarResponse(Car car, boolean includeAll){
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePrDay = car.getPricePrDay();
        if(includeAll){
            this.id = car.getId();
            this.bestDiscount = car.getBestDiscount();
        }
    }

}
