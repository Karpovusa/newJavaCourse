package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;


public class GroupModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    //check if there any group, if not - create one
    if (app.group().all().size()==0)
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));

  }

  @Test
  public void testGroupModification() {


    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("group111").withHeader("header111").withFooter("footer111");
    app.group().modify(group);

    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));


  }

}
