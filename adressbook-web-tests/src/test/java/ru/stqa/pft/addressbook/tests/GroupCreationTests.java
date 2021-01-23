package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().initGroupCreation();
    GroupDate group = new GroupDate("test1", "test1", null);
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.getNavigationHelper().logoutUser();

    int max = 0;
    for (GroupDate g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
