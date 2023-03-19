package ru.levelp.at.homework4.suite;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags(value = {"suite"})
@SelectPackages("ru.levelp.at.homework4.tests")
public class SuiteTest {
}
