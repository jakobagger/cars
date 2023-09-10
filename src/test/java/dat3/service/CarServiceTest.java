package dat3.service;

import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import dat3.car.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CarServiceTest {

    @Autowired
    CarRepository carRepository;

    CarService carService;

    Car c1, c2;

    private boolean dataInitialized = false;
    @BeforeEach
    void setUp() {
            c1 = carRepository.save(new Car("brand1", "model1", 1, 1));
            c2 = carRepository.save(new Car("brand2", "model2", 2, 2));
            carService = new CarService(carRepository);
    }

    @Test
    void getCars() {
    }

    @Test
    void findById() {
    }

    @Test
    void addCar() {
    }

    @Test
    void editCar() {
    }

    @Test
    void deleteCarById() {
    }
}