package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.junit.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.gotoGroupPage();

    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Test2").withHeader("Test3").withFooter("Test4");
    app.group().create(group);
    Groups after = app.group().all();

    Assert.assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt(g->g.getId()).max().getAsInt()))));

  }

  @Test
  public void testBadGroupCreation() {

    app.gotoGroupPage();

    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Test'").withHeader("Test3").withFooter("Test4");
    app.group().create(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));

  }

}
