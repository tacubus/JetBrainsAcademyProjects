package cinema;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    private static final Scanner scanner    = new Scanner(System.in);

    public static int askNumberOfRows ()
    {
        System.out.print("Enter the number of rows:\n> ");

        return scanner.nextInt();
    }

    public static int askSeatsPerRow ()
    {
        System.out.print("Enter the number of seats in each row:\n> ");

        return scanner.nextInt();
    }

    public static int askRowNumber ()
    {
        System.out.print("\nEnter a row number:\n> ");

        return scanner.nextInt();
    }

    public static int askSeatNumberInRow ()
    {
        System.out.print("Enter a seat number in that row:\n> ");

        return scanner.nextInt();
    }

    public static int showMainMenu ()
    {
        System.out.print(
            "\n1. Show the seats\n" +
            "2. Buy a ticket\n" +
            "3. Statistics\n" +
            "0. Exit\n" +
            "> "
        );

        return scanner.nextInt();
    }

    public static void showRoomSeats (ArrayList<ArrayList<Room.Seat>> seats)
    {
        int rows        = seats.size();
        int seatsPerRow = seats.get(0).size();

        System.out.println("\nCinema:");

        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seatsPerRow + 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                } else if (i == 0) {
                    System.out.print(j + " ");
                } else if (j == 0) {
                    System.out.print(i + " ");
                } else {
                    if ((seats.get(i - 1).get(j - 1).isBuyed())) {
                        System.out.print("B ");
                    } else {
                        System.out.print("S ");
                    }
                }
            }

            System.out.println();
        }
    }

    public static void showSeatPrice (int price)
    {
        System.out.println("\nTicket price: $" + price);
    }

    public static void showStatistics (int purchasedTickets, double soldPercentage, int currentIncome, int totalIncome)
    {
        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%", soldPercentage);
        System.out.println("\nCurrent income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void showErrorMessage (String message) {
        System.out.println(message);
    }
}
