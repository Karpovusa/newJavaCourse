package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    //check if there any group, if not - create one
    if (!app.getGroupHelper().isThereAGroup())
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    //list of existing groups before modification
    List<GroupData> before = app.getGroupHelper().getGroupList();

    //group modification
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size()-1).getId(),"group1", "header", "footer");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();

    //list of existing groups after modification
    List<GroupData> after = app.getGroupHelper().getGroupList();

    before.remove(before.size()-1);
    before.add(group);
    Comparator<? super GroupData> byId = (g1,g2)-> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


  }

}
