(ns day3b
  (:require '[clojure.string :as str]
            '[clojure.set :as s]))

(defn priority-score [character]
  (if (<= character 90)
    (- character 38)
    (- character 96)))

(def group-priorities-types
  #(reduce (fn [counter item]
             (let [{rucksacks :rucksacks
                    total :total} counter]
               (if (< (count rucksacks) 2)
                 (assoc counter :rucksacks (conj rucksacks (set item)))
                 (let [[r1 r2] rucksacks]
                   (->> (s/intersection r1 r2 (set item))
                        (first)
                        (int)
                        (priority-score)
                        (+ total)
                        (assoc counter :rucksacks [] :total)))))) {:rucksacks [] :total 0} %))

(-> (slurp "../input/day3.txt")
    (str/split-lines)
    (group-priorities-types)
    (:total))