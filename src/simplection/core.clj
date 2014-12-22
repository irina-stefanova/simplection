(ns simplection.core
  (:require hiccup.core))

(defn svg-quotes-helper
  "In the REPL all double quotes are escaped. Therefore for quick copy-paste testing purposes use this helper to convert double quotes to single quotes"
  [incoming-html]
  (clojure.string/replace incoming-html "\"" "'"))

(defn hiccup-to-svg
  [svg]
  (hiccup.core/html svg))

(defn foo
  "Test svg."
  []
  [:svg {:width "400" :height "400"} [:path {:stroke "black" :D "m 150,100 l 300,300 z"}]])

(defrecord point-2d [x y])
(defrecord raw-series [point-2d])
(defrecord coll-series [raw-series])


(defn geometry-to-svg
  "Convert geometry instructions to svg path"
  [geo]
  (for [path geo]
    [:path {:d (clojure.string/join " " path)}]
  ))
