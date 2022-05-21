public class Main {
    public static void main(String[] args) {
        Expense expense = new Expense(1000f,
                "user1",
                4,
                new String[]{"user1", "user2", "user3", "user4"},
                SplitType.EXACT,
                new float[]{200f, 300f, 250f, 250f});

        Expense expense2 = new Expense(1000f,
                "user1",
                4,
                new String[]{"user1", "user2", "user3", "user4"});


        BalanceSheet balanceSheet = new BalanceSheet();
        balanceSheet.createExpense(expense);
        balanceSheet.createExpense(expense2);
    }
}
