(ns day3a
    (:require '[clojure.string :as str]
              '[clojure.set :as s]))

(def total-priorities-types
    #(reduce (fn [acc item]
                (let [length-of-item (/ (count item) 2)
                      compartment-a (subs item 0 length-of-item)
                      compartment-b (subs item length-of-item)
                      shared-item-priority  (-> (s/intersection (set compartment-a) (set compartment-b))
                                       (first)
                                       (int))]
                    (if (<= shared-item-priority 90)
                        (+ acc (- shared-item-priority 38))
                        (+ acc (- shared-item-priority 96)))
                    )) 0 %))

(-> (slurp "../input/day3.txt")
    (str/split-lines)
    (total-priorities-types))