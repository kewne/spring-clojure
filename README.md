# spring-clojure

A bunch of utilities for plugging Clojure code into Spring-based apps.

# Why should I use this?

* You want to use Clojure to build better apps
* You want to use your existing Spring knowledge

In [Neal's Master Plan for Clojure Enterprise Mindshare Domination](https://youtu.be/2WLgzCkhN2g),
Neal Ford mentions that one of the ways that Clojure can gain adoption by the enterprise software
world is by having bridges that connect it to mainstream technologies.
This is such a bridge to Spring.

While you could just AOT compile your Clojure code and declare it in Spring, this misses
out on many Clojure features and requires tons of boilerplate.

## Why Spring?

You could say that Spring tries to do too much, and is never best-of-breed because of it.

However, it's the only one-stop shop I know that lets me build stuff quickly, actually figure out
where best-of-breed solutions are needed and swap those in.

And it's well documented.

And I already know how to build apps with it.

# How do I use this?

If you have an interface (Spring loves interfaces :D):
```java
public interface Greeter {
  String getGreeting();
}
```
and a bean that uses it:
```java
@Autowired
private final Greeter greeter;
```
and this Clojure function:
```clojure
(defn my-greet [greetee] (str "Hello " greetee))
```
you can easily create a factory method for implementations
```clojure
(defn greeter-factory [greetee]
  (reify
    Greeter
    (greet [this] (my-greet greetee))))
```
and put it in your Spring app
```java
@Bean
public Greeter clojureGreeter(@Value("${greetee.name}") String greetee) {
  return ClojureBean.beanFromClojure("my-namespace", "greeter-factory", greetee);
}
```