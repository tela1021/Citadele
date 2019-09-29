import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by retinka on 13.01.2016.
 */
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