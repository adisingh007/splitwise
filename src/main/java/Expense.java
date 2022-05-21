public class Expense {
    private final float amount;
    private final String payerId;
    private final int numberOfUsers;
    private final String[] listOfUsers;
    private final SplitType splitType;
    private final float[] amounts;

    public Expense(float amount,
                   String payerId,
                   int numberOfUsers,
                   String[] listOfUsers,
                   SplitType splitType,
                   float[] amounts) {
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
        this.amounts = new float[numberOfUsers];
        for(int i = 0; i < numberOfUsers; i++) {
            amounts[i] = perPersonAmount;
        }
    }

    public float getAmount() {
        return amount;
    }

    public String getPayerId() {
        return payerId;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public String[] getListOfUsers() {
        return listOfUsers;
    }

    public float[] getAmounts() {
        return amounts;
    }

    public SplitType getSplitType() {
        return splitType;
    }
}
