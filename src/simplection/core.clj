(ns simplection.core
  (:require hiccup.core))

(defn svg-quotes-helper
  "In the REPL all double quotes are escaped. Therefore for quick copy-paste testing purposes use this helper to convert double quotes to single quotes"
  [incoming-html]
  (clojure.string/replace incoming-html "\"" "'"))

(defn hiccup-to-svg
  [svg]
  (hiccup.core/html svg))

(defn svg-element
  "Writes the enclosing svg element used for all report items. Pass to it the whole rendered report."
  [report]
  [:svg {:width width :height height} report])
  

(defn foo
  "Test svg."
  []
  [:svg {:width "400" :height "400"} [:path {:stroke "black" :D "m 150,100 l 300,300 z"}]])