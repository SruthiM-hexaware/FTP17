public class BankAccount {
    DBManager dbManager = new DBManager();

    public String processAccount(int accountID){
        String accountHolderName = dbManager.retreiveAccountHolderName(accountID);

        return accountHolderName;
    }
}