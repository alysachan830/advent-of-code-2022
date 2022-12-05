(ns day2b
  (:require '[clojure.string :as s]))

(def win-combinations {:A 2 :B 3 :C 1})
(def lost-combinations {:A 3 :B 1 :C 2})
(def score-translation {:A 1 :B 2 :C 3})
(def result-scores {:X 0 :Y 3 :Z 6})

(def total-score-part-2
  #(reduce (fn [acc round]
            (let [[opponent round-result] (s/split round #"\s")]
              (cond->> (get result-scores (keyword round-result))
                       (= "Y" round-result) (+ acc (get score-translation (keyword opponent)))
                       (= "X" round-result) (+ acc (get lost-combinations (keyword opponent)))
                       (= "Z" round-result) (+ acc (get win-combinations (keyword opponent)))))) 0 %))


(-> (slurp "../input/day2.txt")
    (s/split-lines)
    (total-score-part-2))