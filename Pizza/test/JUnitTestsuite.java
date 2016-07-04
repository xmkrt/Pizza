import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ JUnitTestEnum.class,JUnitTestExceptions.class,JUnitTestIOSerializer.class,JUnitTestCollections.class, JUnitTestIOSpeiseKarteImporter.class})
public class JUnitTestsuite {
}