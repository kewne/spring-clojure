(ns test.spring-clojure
  (:import io.github.kewne.spring_clojure.TestGreeter))

(defn testBean [greetee]
  (reify
    TestGreeter
    (greet [this] (str "Hello " greetee))))