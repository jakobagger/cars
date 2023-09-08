package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        setupUserWithRoleUsers();


    }

    @Autowired
    UserWithRolesRepository userWithRolesRepository;

    final String passwordUsedByAll = "test12";

    /*****************************************************************************************
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     *****************************************************************************************/
    private void setupUserWithRoleUsers() {

        System.out.println("******************************************************************************");
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println("******************************************************************************");
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }

}

