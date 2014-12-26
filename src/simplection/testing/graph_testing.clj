(ns simplection.testing.graph-testing
  #_(:require [simplection.datapath.straight :refer [->Straight]]))

(def test-data1 (->Straight [[[22 13][31 2][7 10][7 402][7 313]]
                           [[22 13][31 13][7 13][7 13][7 13]]
                           [[22 13][31 13][7 13][7 13][7 13]]
                           [[22 13][31 13][7 13][7 13][7 13]]]))

(def test-data2 (->Straight [[[22 13][31 2][7 10][7 402][7 313]]
                           [[22 13][31 13][7 13][7 13][7 13]]
                           [[22 13][31 13][7 13][7 13][7 13]]
                           [[22 13][31 13][7 13][7 13][7 13]]]))

(def graphaaa (simplection.core/svg-quotes-helper (simplection.core/hiccup-to-svg (simplection.core/geometry-to-svg (generate-data-path test-data)))))

(def report1 (Report.
               [(ReportItem. (generate-data-path test-data1) "rep-item-1")
                (ReportItem. (generate-data-path test-data2) "rep-item-2")]))

(simplection.testing.helpers/svg-quotes-helper (simplection.report/hiccup->svg (simplection.report/generate-report report1)))