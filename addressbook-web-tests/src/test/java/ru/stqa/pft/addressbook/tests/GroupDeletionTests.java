package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.gotoGroupPage();
    //check if there any group, if not - create one
    if (app.group().all().size()==0)
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));

  }

  @Test
  public void testGroupDeletion() {

    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();

    Assert.assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedGroup)));
  }





}
