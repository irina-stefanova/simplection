(ns simplection.datapath.straight
  (:require simplection.core)
  (:use simplection.datapath.core))

(defrecord Straight[coll])

(extend-protocol PDataPathGenerator
  Straight
  (generate-data-path [{points :coll}] 
    (vec 
      (for [point points]
        (vec
          (flatten
            (let [start-point (first point)
                  x (first start-point)
                  y (rest start-point)
                  path (list "M" x y)]
              (concat path
                      (for [y (rest point)]
                        (list "L" y))))))))))

(def test-series (Straight. [[[22 13][31 13][7 13]]]))
(def test-series2 (Straight. [
                                 [[22 13][31 2][7 10][7 402][7 313]]
                                 [[22 13][31 13][7 13][7 13][7 13]]
                                 [[22 13][31 13][7 13][7 13][7 13]]
                                 [[22 13][31 13][7 13][7 13][7 13]]
                                 ]))

(def test (simplection.core/svg-quotes-helper (simplection.core/hiccup-to-svg (simplection.core/geometry-to-svg (generate-data-path test-series2)))))