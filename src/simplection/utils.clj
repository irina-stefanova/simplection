(ns simplection.utils)

(defn ranges-coefficient
  "Gets the coefficient of two ranges."
  [^double old-min ^double old-max ^double new-min ^double new-max]
  (/ (- old-max old-min) (- new-max new-min)))

(defn remap-values
  "Converts the value from the old range to a value in the new range, using the coefficient k."
  [^doubles old-range ^doubles new-range ^doubles coll-old-values]
  (let[[old-min old-max] old-range
       [new-min new-max] new-range
       k (ranges-coefficient old-min old-max new-min new-max)]
    (map #(- new-max (/ (- old-max %) k)) coll-old-values)))