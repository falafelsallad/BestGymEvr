package BestGymEvr;

import BestGymEvrProgram.Database;
import BestGymEvrProgram.Person;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GymTest {

    @Test
    void checkMembershipValidMember() throws IOException {
        List<Person> members = new ArrayList<>();
        members.add(new Person("7703021234", "Alhambra Aromes", LocalDate.now().minusMonths(6)));
        Database database = new Database();


        assertNull(database.checkMembership("Mr. Notamember", members));
    }


    @Test
    void checkMembershipExpiredMember() throws IOException {
        List<Person> members = new ArrayList<>();
        members.add(new Person("7703021234", "Alhambra Aromes", LocalDate.now().minusYears(2))); // 2 years ago
        Database database = new Database();

        assertNotNull(database.checkMembership("Alhambra Aromes", members));
        assertFalse(database.checkMembership("Alhambra Aromes", members).isCurrentMember());
    }

    @Test
    void checkMembershipById() throws IOException {
        List<Person> members = new ArrayList<>();
        members.add(new Person("7703021234", "Alhambra Aromes", LocalDate.now().minusMonths(6))); // 6 months ago
        Database database = new Database();


        assertTrue(database.checkMembership("7703021234", members).isCurrentMember());
        assertFalse(database.checkMembership("7703021234", members).isFormerMember("7703021234"));
    }


    @Test
    void checkMembershipTest() throws IOException {
        List<Person> members = new ArrayList<>();

        Person A = new Person("7703021234", "Alhambra Aromes", LocalDate.parse("2024-07-01"));
        Person B = new Person("8204021234", "Bear Belle", LocalDate.parse("2019-12-02"));


        members.add(A);
        members.add(B);

        Database database = new Database();


        assertNull(database.checkMembership("not a person", members));

    }


    @Test
    public void memberListParsesCorrectly() throws IOException {

        Path path = Paths.get("TestBestGymEvr/BestGymEvr/GymMembersTest");
        FileReader fileReader = new FileReader(path.toFile());
        BufferedReader br = new BufferedReader(fileReader);
        Database database = new Database();


        List<Person> members = database.memberList(br);

        assertNotNull(members);
        assertEquals(3, members.size());
        assertEquals("770", members.get(0).getPersonID());
        assertEquals("Ima Member", members.get(0).getName());
        assertEquals(LocalDate.parse("2024-07-01"), members.get(0).getLastPayment());
        assertEquals("820", members.get(1).getPersonID());
        assertEquals("Ima Membertoo", members.get(1).getName());
        assertEquals(LocalDate.parse("2019-12-02"), members.get(1).getLastPayment());
        assertEquals("851", members.get(2).getPersonID());
        assertEquals("My NameaJeff", members.get(2).getName());
        assertEquals(LocalDate.parse("2024-03-12"), members.get(2).getLastPayment());
    }

    @Test
    public void memberListHandlesEmptyInput() throws IOException {
        String input = "";
        BufferedReader br = new BufferedReader(new StringReader(input));
        Database database = new Database();
        List<Person> members = database.memberList(br);

        assertTrue(members.isEmpty());
    }


}