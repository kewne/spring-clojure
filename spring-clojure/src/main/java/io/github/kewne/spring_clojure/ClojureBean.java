package io.github.kewne.spring_clojure;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public final class ClojureBean {

  private ClojureBean() {}

  public static <T> T beanFromClojure(String namespace, String factoryMethodName, Object dep) {
    Clojure.var("clojure.core/require").invoke(Clojure.read(namespace));
    IFn factoryMethod = Clojure.var(namespace, factoryMethodName);
    return (T) factoryMethod.invoke(dep);
  }
  
}