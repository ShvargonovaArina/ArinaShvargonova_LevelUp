package ru.levelp.at.homework3.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ru.levelp.at.homework3.tests.FirstMailTest;
import ru.levelp.at.homework3.tests.SecondMailTest;
import ru.levelp.at.homework3.tests.ThirdMailTest;

@Suite
@IncludeTags(value = {"suite"})
@SelectClasses({FirstMailTest.class, SecondMailTest.class, ThirdMailTest.class})
public class SuiteTest {
}
