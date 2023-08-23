package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.Random;

@Controller
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;

    public DeveloperData(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("what is up y'all");

        String[] brands = {"Toyota", "Honda", "Ford", "Chevrolet", "BMW", "Mercedes"};
        String[] models = {"Corolla", "Civic", "Mustang", "Cruze", "X5", "E-Class"};
        int[] discounts = {2, 5, 10, 15, 20, 25, 30, 35, 40, 45};

        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            String brand = brands[random.nextInt(brands.length)];
            String model = models[random.nextInt(models.length)];
            double price = 200 + random.nextInt(800); // Random price between 200 and 999
            int discount = discounts[random.nextInt(discounts.length)];

            Car car = new Car(brand, model, price, discount);
            carRepository.save(car);

        }

    }
}

