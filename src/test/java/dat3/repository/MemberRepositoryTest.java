package dat3.repository;

import dat3.car.repository.MemberRepository;
import dat3.car.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    private boolean dataInitialized = false;

    @BeforeEach
    void setUp() {
        if(!dataInitialized) {
            memberRepository.deleteAll();
            memberRepository.save(new Member("testor", "password1", "testor@testmail.net", "Test", "Testesen", "Prøvestenen", "Testby", "1234"));
            memberRepository.save(new Member("lysfyr", "apple", "lucifer@hell.no", "Satan", "Sfyr", "Highway to Hell", "Hell", "666"));
            dataInitialized = true;
        }
    }

    @Test
    void testSaveMethod() {
        Member newMember = new Member("kontrafakto", "nej", "strange@bel.ly", "Strange", "Persson", "Afvej", "Udby", "404");
        assertEquals("kontrafakto", newMember.getUsername(), "expects new user to be created with username (primary key) kontrafakto");
    }
}