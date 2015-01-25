(ns simplection.testing.graph-testing
  (:require [simplection.canvasgraph.data-paths :refer [generate-data-path]] 
            [simplection.report :refer [hiccup->svg generate-report]]
            [simplection.testing.helpers :refer [svg-quotes-helper]])
  (:import [simplection.canvasgraph.data_paths Straight]
           [simplection.report Report ReportItem]))

(def test-table [{:DO "a"   :DC "main"      :DSC "main-a"      :DSSC "main-a-1"      :DX1 10    :DX2 90    :DY1 120   :DY2 70}
                 {:DO "b"   :DC "general"   :DSC "general-b"   :DSSC "general-b-1"   :DX1 50    :DX2 100   :DY1 40    :DY2 80}
                 {:DO "c"   :DC "general"   :DSC "general-c"   :DSSC "general-c-1"   :DX1 20    :DX2 30    :DY1 60    :DY2 20}
                 {:DO "a"   :DC "general"   :DSC "general-d"   :DSSC "general-d-1"   :DX1 40    :DX2 20    :DY1 40    :DY2 0}
                 {:DO "c"   :DC "main"      :DSC "main-b"      :DSSC "main-b-1"      :DX1 60    :DX2 80    :DY1 110   :DY2 50}
                 {:DO "a"   :DC "main"      :DSC "main-c"      :DSSC "main-c-1"      :DX1 100   :DX2 60    :DY1 40    :DY2 20}
                 {:DO "a"   :DC "general"   :DSC "general-d"   :DSSC "general-d-1"   :DX1 10    :DX2 40    :DY1 110   :DY2 80}
                 {:DO "b"   :DC "general"   :DSC "general-e"   :DSSC "general-e-1"   :DX1 70    :DX2 120   :DY1 10    :DY2 20}
                 {:DO "c"   :DC "general"   :DSC "general-b"   :DSSC "general-b-1"   :DX1 20    :DX2 10    :DY1 0     :DY2 90}])

(def canvas-graph-definition {:aggregates [{:aggregate "category-group" :data [0]}
                                           {:aggregate "series-group" :data [1]}
                                           {:aggregate "+" :data [4]}
                                           {:aggregate "average" :data [5]}]})

(def test-data-1 (Straight. [[[180 455][189 412][199 355][208 381][217 377][226 416][236 407][245 338][254 312][263 282][273 316][282 303][291 325][301 282]
                              [310 273][319 251][328 264][338 247][347 229][356 221][366 273][375 216][384 212][393 208][403 195][412 238][421 229][430 234]
                              [440 242][449 225][458 216][468 195][477 190][486 199][495 186][505 173][514 186][523 203][532 216][542 190][551 177][560 173]
                              [570 182][579 169][588 164][597 156][607 156][616 151][625 143][634 160][644 169][653 173][662 164][672 151][681 147][690 143]
                              [699 138][709 130][718 151][727 160][737 177][746 195][755 173][764 164][774 160][783 156][792 151][801 143][811 130][820 108]]
                             
                             [[180 108][189 130][199 143][208 151][217 156][226 160][236 164][245 173][254 195][263 177][273 160][282 151][291 130][301 138]
                              [310 143][319 147][328 151][338 164][347 173][356 169][366 160][375 143][384 151][393 156][403 156][412 164][421 169][430 182]
                              [440 173][449 177][458 190][468 216][477 203][486 186][495 173][505 186][514 186][523 173][532 186][542 203][551 186][560 173]
                              [570 186][579 199][588 190][597 195][607 208][616 212][625 216][634 273][644 221][653 229][662 247][672 264][681 251][690 273]
                              [699 282][709 325][718 303][727 316][737 282][746 312][755 338][764 407][774 416][783 377][792 381][801 355][811 412][820 455]]]))

(def test-data-2 (Straight. [[[180 455][189 452][199 448][208 431][217 434][226 424][236 417][245 413][254 413][263 406][273 410][282 406][291 399][301 403]
                              [310 396][319 393][328 386][338 389][347 393][356 399][366 396][375 386][384 382][393 368][403 365][412 372][421 375][430 365]
                              [440 361][449 372][458 361][468 358][477 382][486 379][495 372][505 361][514 334][523 327][532 323][542 330][551 327][560 316]
                              [570 306][579 299][588 292][597 302][607 309][616 316][625 282][634 275][644 271][653 275][662 264][672 254][681 240][690 233]
                              [699 236][709 247][718 236][727 233][737 233][746 247][755 229][764 223][774 219][783 216][792 212][801 195][811 143][820 108]]
                             
                             [[180 125][189 143][199 160][208 177][217 170][226 177][236 191][245 181][254 191][263 233][273 219][282 212][291 195][301 202]
                              [310 205][319 209][328 212][338 223][347 229][356 226][366 219][375 282][384 261][393 268][403 257][412 250][421 271][430 282]
                              [440 285][449 292][458 288][468 285][477 278][486 292][495 285][505 271][514 278][523 275][532 282][542 292][551 295][560 306]
                              [570 299][579 309][588 316][597 334][607 344][616 354][625 368][634 382][644 386][653 382][662 379][672 368][681 379][690 386]
                              [699 389][709 389][718 393][727 389][737 386][746 389][755 389][764 393][774 393][783 399][792 396][801 375][811 420][820 455]]]))

(def report-1 (Report.
                [(ReportItem. (generate-data-path test-data-1) "rep-item-1")
                 (ReportItem. (generate-data-path test-data-2) "rep-item-2")]))

(svg-quotes-helper (hiccup->svg (generate-report report-1)))

(defn select-values [map column-names]
  (reduce #(conj %1 (map %2)) [] column-names))

(defn filter-table
  "Filter the table by columns."
  [table column-names]
  (for [row table] 
    (select-keys row column-names)))

(defn group-table
  "Group table by a chosen column combination."
  [table column-names]
  (group-by #(select-keys % column-names) table))

(defn aggregate-table
  "Aggregates the table."
  [table aggregates]
  (for [[_ rows] table] 
    (merge-with-multiple-aggregates table aggregates)))

(defn merge-with-multiple-aggregates
  ""
  [table-section aggregates]
  (zipmap 
    (keys aggregates)
    (apply map #(eval (vals %&)) (conj table-section aggregates))))