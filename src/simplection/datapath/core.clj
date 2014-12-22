(ns simplection.datapath.core)


(defprotocol PDataPathGenerator
  (generate-data-path [this]))