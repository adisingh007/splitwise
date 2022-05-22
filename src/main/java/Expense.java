public class Expense {
    private final float amount;
    private final String payerId;
    private final int numberOfUsers;
    private final String[] listOfUsers;
    private final SplitType splitType;
    private final Float[] amounts;

    public Expense(float amount,
                   String payerId,
                   int numberOfUsers,
                   String[] listOfUsers,
                   SplitType splitType,
                   Float[] amounts) {
        this.amount = amount;
        this.payerId = payerId;
        this.numberOfUsers = numberOfUsers;
        this.listOfUsers = listOfUsers;
        this.splitType = splitType;

        if(splitType == SplitType.PERCENT) {
            for(int i = 0; i < numberOfUsers; i++) {
                amounts[i] = (amounts[i] / 100) * amount;
            }
        }
        this.amounts = amounts;
    }

    public Expense(float amount,
                   String payerId,
                   int numberOfUsers,
                   String[] listOfUsers) {
        this.amount = amount;
        this.payerId = payerId;
        this.numberOfUsers = numberOfUsers;
        this.listOfUsers = listOfUsers;
        this.splitType = SplitType.EQUAL;

        float perPersonAmount = amount / numberOfUsers; // 0 ArithmeticException
        this.amounts = new Float[numberOfUsers];
        for(int i = 0; i < numberOfUsers; i++) {
            amounts[i] = perPersonAmount;
        }
    }

    public String getPayerId() {
        return payerId;
    }

    public String[] getListOfUsers() {
        return listOfUsers;
    }

    public Float[] getAmounts() {
        return amounts;
    }
}
