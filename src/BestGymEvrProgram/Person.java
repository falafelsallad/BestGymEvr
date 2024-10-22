package BestGymEvrProgram;

import java.time.LocalDate;

public class Person {
    private final String name;
    private final String personID;
    private final LocalDate lastPayment;


    public Person(String personID, String name, LocalDate lastPayment) {

        this.personID = personID;
        this.name = name;
        this.lastPayment = lastPayment;

    }

    public String getPersonID() {
        return personID;
    }

    public LocalDate getLastPayment() {
        return lastPayment;
    }

    public String getName() {
        return name;
    }

    public boolean isCurrentMember() {
        return lastPayment.isAfter(LocalDate.now().minusYears(1));
    }

    public boolean isFormerMember() {
        return lastPayment.isBefore(LocalDate.now().minusYears(1));

    }
}
