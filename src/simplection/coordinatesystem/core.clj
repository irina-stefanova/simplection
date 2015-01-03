(ns simplection.coordinatesystem.core)

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
    {:x (format "%.2f" (+ center-x (* radius (Math/cos angle-radians))))
     :y (format "%.2f" (+ center-y (* radius (Math/sin angle-radians))))}))

(defn polar->cartesian
 "Converts a whole collection of polar coordinates to cartesian coordinates"
 [coll-polar-coordinates]
 (let [angle-step (/ 360 (count coll-polar-coordinates))]
   (map #(polar-point->cartesian-point 0 0 %1 (* angle-step %2)) coll-polar-coordinates (range))))