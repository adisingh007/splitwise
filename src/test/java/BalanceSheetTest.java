import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class BalanceSheetTest {
    @Test
    public void shouldMaintainBalanceAsExpected() {
        Expense expense = new Expense(1000f,
                "user1",
                4,
                new String[]{"user1", "user2", "user3", "user4"},
                SplitType.EXACT,
                new float[]{200f, 300f, 250f, 250f});

        Expense expense2 = new Expense(1000f,
                "user2",
                4,
                new String[]{"user1", "user2", "user3", "user4"});


        BalanceSheet balanceSheet = new BalanceSheet();
        balanceSheet.createExpense(expense);
        balanceSheet.createExpense(expense2);

        Map<String, Map<String, Float>> balance = balanceSheet.getBalance();

        Assert.assertEquals(balance.get("user1").get("user2"), 250f, 0.1f);
        Assert.assertEquals(balance.get("user2").get("user1"), 300f, 0.1f);
        Assert.assertEquals(balance.get("user3").get("user1"), 250f, 0.1f);
        Assert.assertEquals(balance.get("user3").get("user2"), 250f, 0.1f);
        Assert.assertEquals(balance.get("user4").get("user1"), 250f, 0.1f);
        Assert.assertEquals(balance.get("user4").get("user2"), 250f, 0.1f);
    }


    @Test
    public void showReturnsMapForUser() {
        Expense expense = new Expense(1000f,
                "user1",
                4,
                new String[]{"user1", "user2", "user3", "user4"},
                SplitType.EXACT,
                new float[]{200f, 300f, 250f, 250f});

        Expense expense2 = new Expense(1000f,
                "user2",
                4,
                new String[]{"user1", "user2", "user3", "user4"});


        BalanceSheet balanceSheet = new BalanceSheet();
        balanceSheet.createExpense(expense);
        balanceSheet.createExpense(expense2);

        Map<String, Float> userOwes1 = balanceSheet.show("user1");
        Map<String, Float> userOwes2 = balanceSheet.show("user2");
        Map<String, Float> userOwes3 = balanceSheet.show("user3");
        Map<String, Float> userOwes4 = balanceSheet.show("user4");

        Assert.assertEquals(userOwes1.size(), 0);
        Assert.assertEquals(userOwes2.get("user1"), 50f, 0.1f);
        Assert.assertEquals(userOwes3.get("user1"), 250f, 0.1f);
        Assert.assertEquals(userOwes3.get("user2"), 250f, 0.1f);
        Assert.assertEquals(userOwes4.get("user1"), 250f, 0.1f);
        Assert.assertEquals(userOwes4.get("user2"), 250f, 0.1f);
    }

    @Test
    public void showAllIsConsistent() {
        Expense expense = new Expense(1000f,
                "user1",
                4,
                new String[]{"user1", "user2", "user3", "user4"},
                SplitType.EXACT,
                new float[]{200f, 300f, 250f, 250f});

        Expense expense2 = new Expense(1000f,
                "user2",
                4,
                new String[]{"user1", "user2", "user3", "user4"});


        BalanceSheet balanceSheet = new BalanceSheet();
        balanceSheet.createExpense(expense);
        balanceSheet.createExpense(expense2);

        Map<String, Map<String, Float>> intactBalanceSheet = balanceSheet.show();

        Assert.assertEquals(intactBalanceSheet.get("user1").size(), 0);
        Assert.assertEquals(intactBalanceSheet.get("user2").get("user1"), 50f, 0.1f);
        Assert.assertEquals(intactBalanceSheet.get("user3").get("user1"), 250f, 0.1f);
        Assert.assertEquals(intactBalanceSheet.get("user3").get("user2"), 250f, 0.1f);
        Assert.assertEquals(intactBalanceSheet.get("user4").get("user1"), 250f, 0.1f);
        Assert.assertEquals(intactBalanceSheet.get("user4").get("user2"), 250f, 0.1f);
    }

    @Test
    public void shouldMaintainBalanceAsExpectedWithPercentages() {
        Expense expense = new Expense(1000f,
                "user1",
                4,
                new String[]{"user1", "user2", "user3", "user4"},
                SplitType.EXACT,
                new float[]{200f, 300f, 250f, 250f});

        Expense expense2 = new Expense(1000f,
                "user2",
                4,
                new String[]{"user1", "user2", "user3", "user4"});

        Expense expense3 = new Expense(1000f,
                "user3",
                4,
                new String[]{"user1", "user2", "user3", "user4"},
                SplitType.PERCENT,
                new float[]{30, 40, 13, 17});


        BalanceSheet balanceSheet = new BalanceSheet();
        balanceSheet.createExpense(expense);
        balanceSheet.createExpense(expense2);
        balanceSheet.createExpense(expense3);

        Map<String, Map<String, Float>> intactBalanceSheet = balanceSheet.show();

        Assert.assertEquals(intactBalanceSheet.get("user1").get("user3"), 50f, 0.1f);
        Assert.assertEquals(intactBalanceSheet.get("user2").get("user1"), 50f, 0.1f);
        Assert.assertEquals(intactBalanceSheet.get("user2").get("user3"), 150f, 0.1f);
        Assert.assertEquals(intactBalanceSheet.get("user3").size(),0);
        Assert.assertEquals(intactBalanceSheet.get("user4").get("user1"), 250f, 0.1f);
        Assert.assertEquals(intactBalanceSheet.get("user4").get("user2"), 250f, 0.1f);
        Assert.assertEquals(intactBalanceSheet.get("user4").get("user3"), 170f, 0.1f);
    }

    @Test
    public void newTest() {
        Expense expense1 = new Expense(1000,
                "user1",
                4,
                new String[] {"user1", "user2", "user3", "user4"});

        Expense expense2 = new Expense(1000,
                "user2",
                4,
                new String[] {"user1", "user2", "user3", "user4"},
                SplitType.EXACT,
                new float[] {250, 250, 250, 250});

        Expense expense3 = new Expense(1000,
                "user3",
                4,
                new String[] {"user1", "user2", "user3", "user4"},
                SplitType.PERCENT,
                new float[] {25, 25, 25, 25});

        Expense expense4 = new Expense(1000,
                "user4",
                4,
                new String[] {"user1", "user2", "user3", "user4"});

        BalanceSheet balanceSheet = new BalanceSheet();
        balanceSheet.createExpense(expense1);
        balanceSheet.createExpense(expense2);
        balanceSheet.createExpense(expense3);
        balanceSheet.createExpense(expense4);

        Map<String, Map<String, Float>> balances = balanceSheet.show();
        System.out.println(balances);
    }
}
