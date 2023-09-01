package dat3.car.service;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
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
        //TODO
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        //TODO
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        //TODO
    }

    @Test
    void testSetRankingForUser() {
        //TODO
    }

    @Test
    void testSetRankingForNoExistingUser() {
        //TODO
    }
    @Test
    void testDeleteMemberByUsername() {
        memberService.deleteMemberByUsername("user1");
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        //TODO
    }
}

