package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
//        app.goTo().groupPage();
//        if (app.group().all().size() == 0) {
//            app.group().create(new GroupDate().withName("test1").withHeader("test2"));
//        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupDate().withName("test1").withHeader("test2"));
        }
    }

    @Test//(enabled = false)
    public void testGroupModification() {
        //Groups before = app.group().all();// из веба
        Groups before = app.db().groups();// из базы
        GroupDate modifiedGroup = before.iterator().next();
        GroupDate group = new GroupDate()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.goTo().groupPage();
        app.group().modify(group);
        //Groups after = app.group().all();// из веба
        Groups after = app.db().groups();// из базы
        assertEquals(after.size(), before.size());
        //app.goTo().logoutUser(); // из одного браузера не запускаются тесты т.к. происходит logout
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();
    }

}
