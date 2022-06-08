package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRows = scanner.nextInt();
        int totalSeats = numOfRows * seatsInRows;
        String [][] cinemaSeats = new String[numOfRows + 1][seatsInRows + 1];
        for (int i = 0; i <= numOfRows; i++) {
            for (int j = 0; j <= seatsInRows; j++) {
                if (i == 0 && j != 0) {
                    cinemaSeats[i][j] = Integer.toString(j);
                } else if (i != 0 && j == 0) {
                    cinemaSeats[i][j] = Integer.toString(i);
                } else if (i == 0) {
                    cinemaSeats[i][j] = " ";
                } else {
                    cinemaSeats[i][j] = "S";
                }
            }
        }
        boolean breakMenu = true;
        menu();
        while (breakMenu) {
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    showSeats(numOfRows, seatsInRows, cinemaSeats);
                    menu();
                    break;
                case 2:
                    buyATicket(scanner, totalSeats, numOfRows, seatsInRows, cinemaSeats);
                    menu();
                    break;
                case 3:
                    statistics(numOfRows, seatsInRows, cinemaSeats, totalSeats);
                    menu();
                    break;
                case 0:
                    breakMenu = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }

    public static void showSeats(int numOfRows, int seatsInRows, String[][] cinemaSeats) {
        System.out.println("Cinema:");
        for (int i = 0; i <= numOfRows; i++) {
            for (int j = 0; j <= seatsInRows; j++) {
                System.out.print(cinemaSeats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void buyATicket(Scanner scanner, int totalSeats, int numOfRows, int seatsInRows, String[][] cinemaSeats) {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();
        boolean check = checkInput(row, seat, numOfRows, seatsInRows);
        while (!check) {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();
            check = checkInput(row, seat, numOfRows, seatsInRows);
        }
        int seatCost = 10;
        if (cinemaSeats[row][seat].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();
            check = checkInput(row, seat, numOfRows, seatsInRows);
            while (!check) {
                System.out.println("Enter a row number:");
                row = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                seat = scanner.nextInt();
                check = checkInput(row, seat, numOfRows, seatsInRows);
            }
        }
        if (totalSeats > 60) {
            if (row > numOfRows / 2) {
                seatCost = 8;
            }
        }
        System.out.println("Ticket price: $" + seatCost);
        cinemaSeats[row][seat] = "B";
    }

    public static void statistics(int numOfRows, int seatsInRows, String[][] cinemaSeats, int totalSeats) {
        int count = 0;
        int cost = 0;
        int totalIncome;
        int mid = numOfRows / 2;
        for (int i = 1; i <= numOfRows; i++) {
            for (int j = 1; j <= seatsInRows; j++) {
                if (cinemaSeats[i][j].equals("B")) {
                    count = count + 1;
                    if (totalSeats > 60 && i > mid) {
                        cost += 8;
                    } else {
                        cost += 10;
                    }
                }
            }
        }
        if (totalSeats > 60) {
            totalIncome = (10 * mid + 8 * (numOfRows - mid)) * seatsInRows;
        } else {
            totalIncome = 10 * totalSeats;
        }
        double percentage = ((double) count / totalSeats) * 100;
        System.out.printf("Number of purchased tickets: %d%n", count);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", cost);
        System.out.printf("Total income: $%d%n", totalIncome);

    }

    public static boolean checkInput(int row, int seat, int m, int n) {
        if (row > m || seat > n) {
            System.out.println("Wrong input!");
            return false;
        } else {
            return true;
        }
    }

    public static void menu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}