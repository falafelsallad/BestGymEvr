package BestGymEvrProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Gym {

    public static void main(String[] args)  {

        String memberMessage = "Welcome member!";
        String formerMemberMSG = "Not a member anymore, your last payment was: ";
        String notMemberMSG = "Not a member, person not found in database\n";
        String exit = "exit";
        String enterMessage = "Enter your personID or name: ";
        String errorFile = "Could not read file ";

        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        Path path = Paths.get("src/BestGymEvrProgram/GymMembers");

        try {
            try (FileReader fileReader = new FileReader(path.toFile())) {
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                database.memberList(bufferedReader);
            }
        } catch (IOException e) {
            System.out.println(errorFile + e.getMessage());
            return;
        }

        while (true) {
            System.out.println(enterMessage);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase(exit)) { // Exit program "exit"
                break;
            }

            Person customer = database.checkMembership(input, database.members);

            if (customer != null) {
                if (customer.isCurrentMember()) {
                    System.out.println(memberMessage); // Welcome message
                    database.visitLog(customer);
                } else if (customer.isFormerMember()) { // Former member message
                    System.out.println(formerMemberMSG + customer.getLastPayment());
                }
            } else {
                System.out.println(notMemberMSG); // Not a member message

            }

        }
    }
}



