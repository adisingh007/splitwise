import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            sc.close();
            System.out.println("Scanner closed!");
        }));

        BalanceSheet balanceSheet = new BalanceSheet();
        while(true) {
            System.out.println("****** OPERATION TYPE *****");
            System.out.println("1. EXPENSE");
            System.out.println("2. SHOW <user_id>");
            System.out.println("3. SHOW ALL");
            System.out.print("Enter operation number: ");
            int operationNumber = Integer.parseInt(sc.nextLine());

            switch (operationNumber) {
                case 1 -> { // EXPENSE
                    System.out.print("Enter payer id: ");
                    String payerId = sc.nextLine();
                    System.out.print("Enter amount to be split: ");
                    float amount = Float.parseFloat(sc.nextLine());
                    System.out.print("Enter number of users: ");
                    int numberOfUsers = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter list of user_ids (space separated): ");
                    String[] userIds = sc.nextLine().split(" ");
                    System.out.println("***** SPLIT TYPE *****");
                    System.out.println("1. EQUAL");
                    System.out.println("2. EXACT");
                    System.out.println("3. PERCENT");
                    System.out.print("Enter split type number: ");
                    int splitTypeNumber = Integer.parseInt(sc.nextLine());
                    Expense expense;
                    if (splitTypeNumber == 2 || splitTypeNumber == 3) {
                        System.out.print("Enter list of amounts (space separated): ");
                        Float[] amounts = Arrays.stream(sc.nextLine().split(" "))
                                .map(Float::parseFloat)
                                .toArray(Float[]::new);
                        expense = new Expense(amount,
                                payerId,
                                numberOfUsers,
                                userIds,
                                splitTypeNumber == 2 ? SplitType.EXACT : SplitType.PERCENT,
                                amounts
                        );
                    } else if (splitTypeNumber == 1) {
                        expense = new Expense(amount, payerId, numberOfUsers, userIds);
                    } else {
                        System.err.println("No such split type possible");
                        continue;
                    }
                    balanceSheet.createExpense(expense);
                }
                case 2 -> { // SHOW
                    System.out.print("Enter user_id of user whose balance sheet is to be shown: ");
                    String userId = sc.nextLine();
                    System.out.println(balanceSheet.show(userId));
                }
                case 3 -> // SHOW ALL
                        System.out.println(balanceSheet.show());
            }
        }
    }
}
