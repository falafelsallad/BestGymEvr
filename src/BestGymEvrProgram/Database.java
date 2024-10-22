package BestGymEvrProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Database {


    protected List<Person> members = new ArrayList<>();


    public List<Person> memberList(BufferedReader bufferedReader) throws IOException {

        String line;
        while ((line = bufferedReader.readLine()) != null) {

            String[] personInfo = line.split(",");
            String personID = personInfo[0];
            String name = personInfo[1].trim();
            String dateStr = bufferedReader.readLine();
            LocalDate lastPayment = LocalDate.parse(dateStr);

            Person person = new Person(personID, name, lastPayment);
            members.add(person);

        }
        return members;

    }

    public Person checkMembership(String input, List<Person> members) {

        for (Person member : members) {
            if (member.getPersonID().equals(input) || member.getName().equalsIgnoreCase(input)) {
                return member;
            }
        }
        return null;
    }

    public void visitLog(Person customer) {
        if (customer.isCurrentMember()) {
            Path pathPTFile = Paths.get("src/BestGymEvrProgram/PTVisitlog.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathPTFile.toFile(), true))) {
                writer.write(customer.getName() + " " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                writer.newLine();
                System.out.println("Visit logged for PT: " + customer.getName() + "\n");
            } catch (IOException e) {
                System.out.println("Could not write to file" + e.getMessage() + "\n");
            }
        }
    }

}


