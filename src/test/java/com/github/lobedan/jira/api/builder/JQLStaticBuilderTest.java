package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.types.OrderType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author svenklemmer
 * @since 0.1.0
 */
@RunWith(JUnit4.class)
public class JQLStaticBuilderTest {

  @Before
  public void setup() {
    JQLStaticBuilder.clear();
  }


  @Test
  public void testHoldsInstanceOfJQLBuilder() throws Exception {
    assertThat(JQLStaticBuilder.field("person"), is(instanceOf(JQLBuilder.class)));
    JQLStaticBuilder.clear();
    assertThat(JQLStaticBuilder.field("person").build(), is("person"));
  }

  @Test
  public void testHoldsSpecialMethods() throws Exception {
    assertThat(JQLStaticBuilder.nul(), is("null"));
    assertThat(JQLStaticBuilder.empty(), is("empty"));
    assertThat(JQLStaticBuilder.on("2012-01-01"), is("on 2012-01-01"));
    assertThat(JQLStaticBuilder.asc(), is(OrderType.ASC));
    assertThat(JQLStaticBuilder.desc(), is(OrderType.DESC));
  }
}
