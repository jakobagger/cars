package dat3.car.repository;

import dat3.car.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    private boolean dataInitialized = false;

    @BeforeEach
    void setUp() {
        if(!dataInitialized) {
            carRepository.deleteAll();
            carRepository.save(new Car("Toyota", "Carina", 500, 50));
            carRepository.save(new Car("Ford", "Aztec", 7000, 25));
            dataInitialized = true;
        }
    }

    @Test
    public void findByBrandTest(){
        Car car = carRepository.findCarByBrand("Toyota");
        assertEquals("Toyota", car.getBrand());
    }

}