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

(defrecord Line-series [raw-series])

(defprotocol Geometry
  (geometry [this]))

(extend-protocol Geometry
  Line-series
  (geometry [{raw-series :raw-series}] 
    (vec 
      (for [point raw-series]
        (conj 
          (vec
            (flatten
              (let [
                    start-point (first point)
                    x (first start-point)
                    y (rest start-point)
                    path (list "M" x y)
                    ]
                (concat path
                        (for [y (rest point)]
                          (list "L" y))
                        ))))
          "Z")))))

(defn geometry-to-svg
  "Convert geometry instructions to svg path"
  [geo]
  (for [path geo]
    [:path {:d (clojure.string/join " " path)}]
  ))

(def test-series (Line-series. [[[22 13][31 13][7 13]]]))
(def test-series2 (Line-series. [
                                 [[22 13][31 13][7 13][7 13][7 13]]
                                 [[22 13][31 13][7 13][7 13][7 13]]
                                 [[22 13][31 13][7 13][7 13][7 13]]
                                 [[22 13][31 13][7 13][7 13][7 13]]
                                 ]))

(def test (svg-quotes-helper (hiccup-to-svg (geometry-to-svg (geometry test-series2)))))