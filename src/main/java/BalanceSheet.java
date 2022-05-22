import java.util.*;

public class BalanceSheet {
    private final Map<String, Map<String, Float>> balance;

    public BalanceSheet() {
        balance = new HashMap<>();
    }

    public void createExpense(Expense expense) {
        String payerId = expense.getPayerId();
        String[] userList = expense.getListOfUsers();
        Float[] amounts = expense.getAmounts();

        for(int i = 0; i < userList.length; i++) {
            if(userList[i].equals(payerId)) continue; // One cannot owe themselves
            if(!balance.containsKey(userList[i])) {
                balance.put(userList[i], new HashMap<>());
            }
            balance.get(userList[i])
                    .put(payerId,
                            balance.get(userList[i]).getOrDefault(payerId, 0f) + amounts[i]);
        }
    }

    public Map<String, Map<String, Float>> getBalance() {
        return balance;
    }

    public Map<String, Float> show(String userId) {
        Map<String, Float> userOwes = new HashMap<>();

        if(balance.containsKey(userId)) {
            for (Map.Entry<String, Float> entry : balance.get(userId).entrySet()) {
                String owedToId = entry.getKey();
                Float amountOwed = entry.getValue();

                Float amountToReceive = balance.containsKey(owedToId)
                        ? balance.get(owedToId).getOrDefault(userId, 0f)
                        : 0f;

                if(amountOwed <= amountToReceive) continue;

                userOwes.put(owedToId, amountOwed-amountToReceive);
            }
        }

        return userOwes;
    }

    public Map<String, Map<String, Float>> show() {
        Map<String, Map<String, Float>> balances = new HashMap<>();

        for(String userId: balance.keySet()) {
            balances.put(userId, show(userId));
        }

        return balances;
    }
}
