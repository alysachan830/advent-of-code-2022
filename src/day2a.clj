(ns day2a
  (:require '[clojure.string :as s]))

(def score {:A 1 :B 2 :C 3 :X 1 :Y 2 :Z 3})
(def win-combinations #{"C X" "A Y" "B Z"})
(def draw-combinations #{"A X" "B Y" "C Z"})

(def total-score
  #(reduce (fn [acc round-result]
             (cond->> (->> (subs round-result 2)
                           (keyword)
                           (get score)
                           (+ acc))
                      (contains? win-combinations round-result)(+ 6)
                      (contains? draw-combinations round-result) (+ 3))) 0 %))


(-> (slurp "../input/day2.txt")
    (s/split-lines)
    (total-score))