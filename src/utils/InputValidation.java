package utils;

import java.util.Scanner;

public class InputValidation {
    public static int readInt(Scanner input) {
        while (true) {
            if (input.hasNextInt()){
                int num = input.nextInt();
                input.nextLine();
                return num;
            }
            System.err.println("Invalid input.");
            input.nextLine();
        }
    }
}
