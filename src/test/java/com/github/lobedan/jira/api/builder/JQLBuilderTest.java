package com.github.lobedan.jira.api.builder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.builder.JQLStaticBuilder.asc;
import static com.github.lobedan.jira.api.builder.JQLStaticBuilder.desc;
import static com.github.lobedan.jira.api.builder.JQLStaticBuilder.empty;
import static com.github.lobedan.jira.api.builder.JQLStaticBuilder.field;
import static com.github.lobedan.jira.api.builder.JQLStaticBuilder.nul;
import static com.github.lobedan.jira.api.builder.JQLStaticBuilder.on;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author svenklemmer
 * @since 0.1.0
 */
@RunWith(JUnit4.class)
public class JQLBuilderTest {

  private JQLBuilder builder;

  @Before
  public void setup() {
    builder = new JQLBuilder();
  }

  @Test
  public void testCanBuildSimpleJQL() throws Exception {
    assertThat(builder.field("person").is("tom").build(), is("person is tom"));
  }

  @Test
  public void testCanBuildJQLChain() throws Exception {
    assertThat(builder.field("person").is("ralf").and().field("assignee").isNot("markus").buildAndClear(),
               is("person is ralf AND assignee is not markus"));
    assertThat(builder.field("person").is("ralf").or().field("person").is("markus").buildAndClear(),
               is("person is ralf OR person is markus"));
  }

  @Test
  public void testCanBuildWithDifferentOperations() throws Exception {
    assertThat(builder.field("person").equal("ralf").buildAndClear(), is("person = ralf"));
    assertThat(builder.field("person").notEqual("ralf").buildAndClear(), is("person != ralf"));

    assertThat(builder.field("votes").greaterThan(4).buildAndClear(), is("votes > 4"));
    assertThat(builder.field("votes").greaterThanEquals(5).buildAndClear(), is("votes >= 5"));

    assertThat(builder.field("votes").lessThan(4).buildAndClear(), is("votes < 4"));
    assertThat(builder.field("votes").lessThanEquals(5).buildAndClear(), is("votes <= 5"));

    assertThat(builder.field("reporter").in("jsmith", "jbrown", "jjones").buildAndClear(),
               is("reporter in (jsmith,jbrown,jjones)"));
    assertThat(builder.field("assignee").notIn("Jack", "Jill", "John").buildAndClear(),
               is("assignee not in (Jack,Jill,John)"));

    assertThat(builder.field("summary").contains("issue", "collector").buildAndClear(),
               is("summary ~ \"issue collector\""));
    assertThat(builder.field("summary").doesNotContain("issue", "collector").buildAndClear(),
               is("summary !~ \"issue collector\""));

    assertThat(builder.field("person").is(empty()).buildAndClear(), is("person is empty"));
    assertThat(builder.field("person").isNot(nul()).buildAndClear(), is("person is not null"));

    assertThat(builder.field("status").was("In Progress").buildAndClear(), is("status was In Progress"));
    assertThat(builder.field("status").wasNot("In Progress").buildAndClear(),
               is("status WAS NOT In Progress"));

    assertThat(builder.field("status").wasIn("Resolved", "In Progress").buildAndClear(),
               is("status WAS IN (Resolved,In Progress)"));
    assertThat(builder.field("status").wasNotIn("Resolved", "In Progress").buildAndClear(),
               is("status WAS NOT IN (Resolved,In Progress)"));

    assertThat(builder.field("assignee").changed().buildAndClear(), is("assignee CHANGED"));
  }

  @Test
  public void testSpecialValues() throws Exception {
    assertThat(builder.field("person").isNot(empty()).buildAndClear(), is("person is not empty"));
    assertThat(builder.field("person").notEqual(nul()).buildAndClear(), is("person != null"));
  }

  @Test
  public void testDifferentValueTypes() throws Exception {
    assertThat(builder.field("field").is("tom").buildAndClear(), is("field is tom"));
    assertThat(builder.field("field").greaterThan(1).buildAndClear(), is("field > 1"));
    assertThat(builder.field("field").greaterThan(1L).buildAndClear(), is("field > 1"));
    assertThat(builder.field("field").greaterThan(1.00).buildAndClear(), is("field > 1.0"));
    assertThat(builder.field("field").greaterThan(1f).buildAndClear(), is("field > 1.0"));
    assertThat(builder.field("active").is(true).buildAndClear(), is("active is true"));
  }

  @Test
  public void testSpecialValueMethods() throws Exception {
    assertThat(builder.field("status").wasNot("In Progress")
                   .after("2011/02/02").buildAndClear(), is("status WAS NOT In Progress AFTER 2011/02/02"));
    assertThat(builder.field("status").was("In Progress")
                   .before("2011/02/02").buildAndClear(), is("status was In Progress BEFORE 2011/02/02"));
    assertThat(builder.field("status").changed().from("In Progress").to("Open").buildAndClear(), is(
        "status CHANGED FROM In Progress TO Open"));
    assertThat(builder.field("priority").changed().by("freddo").before("endOfWeek()").after("startOfWeek()")
                   .buildAndClear(), is("priority CHANGED BY freddo BEFORE endOfWeek() AFTER startOfWeek()"));
    assertThat(builder.field("status").was("Resolved").by("jsmith").during("2010/01/01", "2011/01/01")
                   .buildAndClear(), is("status was Resolved BY jsmith DURING (2010/01/01, 2011/01/01)"));
    assertThat(builder.field("status").was("Resolved").by("jsmith").during("2010/01/01", "2011/01/01").buildAndClear(),
               is("status was Resolved BY jsmith DURING (2010/01/01, 2011/01/01)"));
        assertThat(builder.field("created").was(on("2010/01/01")).buildAndClear(), is("created was on 2010/01/01"));
  }

  @Test
  public void testOrderByMethod() throws Exception {
    assertThat(builder.field("created").before("2010/01/01").orderBy("created", asc()).buildAndClear(),
               is("created BEFORE 2010/01/01 order by created asc"));
    assertThat(builder.field("created").before("2010/01/01").orderBy("created", desc()).buildAndClear(),
               is("created BEFORE 2010/01/01 order by created desc"));

    assertThat(builder.field("created").before("2010/01/01").orderBy(asc(), "created", "updated")
                   .buildAndClear(), is("created BEFORE 2010/01/01 order by created,updated asc"));
    assertThat(builder.field("created").before("2010/01/01").orderBy(desc(), "created", "updated")
                   .buildAndClear(), is("created BEFORE 2010/01/01 order by created,updated desc"));
  }

  @Test
  public void testMakeBrackets() throws Exception {
    assertThat(builder.brackets(field("person").is("ralf").or().field("person").is("markus")).and()
                   .field("created").was(on("2010/01/01")).build(),
               is("(person is ralf OR person is markus) AND created was on 2010/01/01"));
  }
}
