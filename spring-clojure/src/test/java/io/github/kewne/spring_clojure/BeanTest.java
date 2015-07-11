package io.github.kewne.spring_clojure;

import org.junit.Test;
import static org.junit.Assert.*;

public class BeanTest {

    @Test
    public void testBasicBean() {
      TestGreeter greeter = ClojureBean.<TestGreeter>beanFromClojure("test.spring-clojure", "testBean", "World");
      assertEquals("Hello World", greeter.greet());
    }
}