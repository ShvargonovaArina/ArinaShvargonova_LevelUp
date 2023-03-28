package ru.levelp.at.homework2.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ru.levelp.at.homework2.LuckyTicketIT;

@Suite
@IncludeTags(value = {"negative", "positive"})
@SelectClasses(LuckyTicketIT.class)
public class AllSuite {
}
