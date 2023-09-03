package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    CarRepository carRepository;
    public CarService(CarRepository carRepository){this.carRepository = carRepository;}
    public List<CarResponse> getCars(boolean includeAll) {
        List<Car> cars = carRepository.findAll();
        //ChatGPT magic, I'm working on understanding lambda and streams:
        return cars.stream()
                .map(car -> new CarResponse(car, includeAll))
                .collect(Collectors.toList());
    }

    public CarResponse findById(int id) {
        Car car = getCarById(id);
        return new CarResponse(car, false);
    }

    public CarResponse addCar(CarRequest body) {
        if(carRepository.existsById(body.getId())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"This car already exists");
        }
        Car newCar = CarRequest.getCarEntity(body);

        newCar = carRepository.save(newCar);

        return new CarResponse(newCar, true);
    }

    public ResponseEntity<Boolean> editCar(CarRequest body, int id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this id does not exist"));
        if(body.getId() != (id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change id");
        }
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }

    public void deleteCarById(int id) {
        Car car = getCarById(id);
        carRepository.delete(car);
    }

    private Car getCarById(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this id does not exist"));
    }
}
