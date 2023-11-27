import list.CustomArrayListTest;
import list.CustomLinkedListTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({CustomArrayListTest.class, CustomLinkedListTest.class})
class AllTests {

}