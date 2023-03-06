package ru.levelp.at.homework3;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags(value = {"suite"})
@SelectClasses({FirstMailTest.class, SecondMailTest.class, ThirdMailTest.class})
public class SuiteTest {
}
