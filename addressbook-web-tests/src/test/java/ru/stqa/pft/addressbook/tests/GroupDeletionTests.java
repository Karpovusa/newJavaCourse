package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {


  @Test
  public void testGroupDeletion() {
    app.gotoGroupPage();

    //check if there any group, if not - create one
    if (!app.getGroupHelper().isThereAGroup())
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));

    //list of existing groups before deletion
    List<GroupData> before = app.getGroupHelper().getGroupList();

    //group deletion

    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();

    //list of existing groups after deletion
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }


}
