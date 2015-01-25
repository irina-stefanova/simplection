(ns simplection.data-aggregation)

(defn select-values [map column-names]
  (reduce #(conj %1 (map %2)) [] column-names))

(defn filter-table-columns
  "Filter the table by columns."
  [table column-names]
  (for [row table]
    (select-keys row column-names)))

(defn filter-table-rows
  "Filter the incoming data"
  [table filtering-fn]
  [])

(defn group-table
  "Group table by a chosen column combination."
  [table column-names]
  (group-by #(select-keys % column-names) table))

(defn merge-with-multiple-aggregates
  ""
  [table-section aggregates]
  (zipmap
    (keys aggregates)
    (apply map #(eval (vals %&)) (conj table-section aggregates))))

(defn aggregate-table
  "Aggregates the table."
  [table aggregates]
  (for [[_ rows] table]
    (merge-with-multiple-aggregates table aggregates)))

(defn organize-table
  "Group, aggregate, filter, sort, etc. the whole table"
  [table-to-organize organization-map]
  [])

(merge-with-multiple-aggregates '({:DO "a" :DC "main" :DY1 120 :DY2 70}{:DO "a" :DC "gene" :DY1 40  :DY2 0}) {:DO distinct? :DC distinct? :DY1 + :DY2 +})
