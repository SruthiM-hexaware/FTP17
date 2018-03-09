import mockit.MockUp;
import mockit.Mock;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BankAccountTest {
    @Test
    public void testBankProcessAccount() {
        new MockUp<DBManager>(){
            @SuppressWarnings("unused")
            @Mock
            public String retreiveAccountHolderName(int accountId){
                return "Abhi";
            }
        };

        BankAccount bank = new BankAccount();
        String name = bank.processAccount(20);
        assertEquals("Account holder name fot A/C id 20 is 'Abhi'","Abhi",name);
    }
}