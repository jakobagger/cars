package dat3.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.service.MemberService;
import dat3.car.entity.Member;
import dat3.car.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email1", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> responses = memberService.getMembers(true);
        assertEquals(2, responses.size(), "expects a database with two members");
        LocalDateTime created = responses.get(0).getCreated();
        assertNotNull(created, "Since getMembers() received true it should have set a date for the DTO");
    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> responses = memberService.getMembers(false);
        assertEquals(2, responses.size(), "expects a database with two members");
        LocalDateTime created = responses.get(0).getCreated();
        assertNull(created, "Since getMembers() received false it should not have set a date for the DTO");
    }

    @Test
    void testFindByIdFound() {
        MemberResponse response = memberService.findById("user1");
        assertEquals("user1", response.getUsername());
    }

    @Test
    void testFindByIdNotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> memberService.findById("non-existing user"));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testAddMember_UserDoesNotExist() {
        MemberRequest request = MemberRequest.builder()
                .username("user3")
                .password("1234")
                .email("email3")
                .build();

        MemberResponse response = memberService.addMember(request);
        assertEquals("user3", response.getUsername());
        assertTrue(memberRepository.existsById("user3"));
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        MemberRequest request = new MemberRequest();
        request.setUsername("user2");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> memberService.addMember(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        MemberRequest memberRequest = new MemberRequest(m1);

        memberRequest.setEmail("New Email");
        memberRequest.setLastName("New Last Name");
        memberRequest.setFirstName("New First Name");
        memberRequest.setCity("New City");
        memberService.editMember(memberRequest, "user1");

        memberRepository.flush();
        MemberResponse response = memberService.findById("user1");

        assertEquals("user1", response.getUsername());
        assertEquals("New Email", response.getEmail());
        assertEquals("New Last Name", response.getLastName());
        assertEquals("New City", response.getCity());
        assertEquals("New First Name", response.getFirstName());
        assertEquals("zip1", response.getZip());
        assertEquals("street1", response.getStreet());

    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        MemberRequest memberRequest = new MemberRequest();
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> memberService.editMember(memberRequest, "non-existing user"));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        MemberRequest request = new MemberRequest(m1);
        request.setUsername("New username");
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> memberService.editMember(request, "user1"));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testSetRankingForUser() {
        memberService.setRankingForUser("user1", 3);
        MemberResponse response = memberService.findById("user1");
        assertEquals(3, response.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> memberService.setRankingForUser("I don't exist", 3));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMemberByUsername("user1");
        assertFalse(memberRepository.existsById("user1"));
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> memberService.deleteMemberByUsername("I don't exist"));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}

