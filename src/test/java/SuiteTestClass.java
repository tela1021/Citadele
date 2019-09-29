import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        visualTest.class,
        loginEmptyFields.class,
        loginEmptyPassword.class,
        loginEmptyWronPass.class,
        loginBlockedUser.class,
        loginWrongUsername.class
        } )
public class SuiteTestClass {

}