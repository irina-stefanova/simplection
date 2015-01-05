(ns simplection.canvasgraph.coordinate-systems)

(defprotocol PCoordinateSystem
  (generate-coordinates [this]))

;;; coll-data should look like {:x ['(Category. [A])] :y ['(Numeric. [B]) '(Numeric. [C])]} - consider orientation
;;; coll-data-mappings are used to define [x y] for each point; if there exist columns [A B C] for the above the mappings can be [A B] [A C]
;;; size is the total size available for the coordinate system
(defrecord Cartesian[coll-data coll-data-mappings size])

(extend-protocol PCoordinateSystem
  Cartesian
  (generate-coordinates [{categories :coll coordinates-range :coordinates-range}] 
    ))

(defrecord Polar[coll coordinates-range])

(extend-protocol PCoordinateSystem
  Polar
  (generate-coordinates [{values :coll coordinates-range :coordinates-range}] 
    ))

(defn normalize-angle
  "Normalizes angles larger than 360 degrees."
  [angle-degrees]
  (rem (+ 360 (rem angle-degrees 360)) 360 ))

(defn angle-degrees->angle-radians
  "Convert angles in degrees to angles in radians"
  [angle-degrees]
  (/ (* Math/PI (- (normalize-angle angle-degrees) 90)) 180))

(defn polar-point->cartesian-point
  "Converts polar coordinates in cartesian coordinates"
  [center-x center-y radius angle-degrees]
  (let[angle-radians (angle-degrees->angle-radians angle-degrees)]
    {:x (+ center-x (* radius (Math/cos angle-radians)))
     :y (+ center-y (* radius (Math/sin angle-radians)))}))

(defn polar->cartesian
 "Converts a whole collection to polar coordinates to cartesian coordinates"
 [coll-polar-coordinates]
 (let [angle-step (/ 360 (count coll-polar-coordinates))]
   (map #(polar-point->cartesian-point 0 0 %1 (* angle-step %2)) coll-polar-coordinates (range))))