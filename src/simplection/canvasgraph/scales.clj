(ns simplection.canvasgraph.scales
  (:require simplection.utils))

(defprotocol PScales
  (generate-coordinates [this]))

(defrecord Category[coll coordinates-range])

(extend-protocol PScales
  Category
  (generate-coordinates [{categories :coll coordinates-range :coordinates-range}] 
    (let [categories-count (count (distinct categories))
          categories-range [1 categories-count]]
      (simplection.utils/remap-values categories-range coordinates-range (range 1 (inc categories-count))))))

(defrecord Numeric[coll coordinates-range])

(extend-protocol PScales
  Numeric
  (generate-coordinates [{values :coll coordinates-range :coordinates-range}] 
    (let [values-range [(apply min values) (apply max values)]]
      (simplection.utils/remap-values values-range coordinates-range values))))