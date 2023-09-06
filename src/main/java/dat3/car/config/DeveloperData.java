package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Random;

@Controller
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;

    ReservationRepository reservationRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("DevData is running");

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

        Member member1 = new Member("agern", "sesam", "eikagger@gmail.com", "Eik", "Agger", "Skoleholdervej", "Copenhagen", "2400");
        Member member2 = new Member("pjevs", "hundeerdumme", "tullemis@gmail.com", "Tulle", "Tunmousse", "Skoleholdervej", "Copenhagen", "2400");
        memberRepository.save(member1);
        memberRepository.save(member2);
        Car car1 = new Car("Toyota","Carina",45,5);
        carRepository.save(car1);
        LocalDate date1 = LocalDate.now().plusDays(3);

        Reservation reservation = new Reservation(date1, member1, car1);
        reservationRepository.save(reservation);

    }
}

