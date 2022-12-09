(ns day4a
  (:require '[clojure.string :as str]))

(def total-pairs-with-fully-contain-other-sections
  #(reduce (fn [total pair]
             (let [all-section-nums (->> (str/split pair #",|-")
                                         (map read-string))
                   pair-section (split-at 2 all-section-nums)
                   max-num (apply max all-section-nums)
                   min-num (apply min all-section-nums)]
               (if (some (fn [p] (= (vec p) (conj [] min-num max-num))) pair-section)
                 (+ total 1)
                 total))
             ) 0 %))

(-> (slurp "../input/day4.txt")
    (str/split-lines)
    (total-pairs-with-fully-contain-other-sections))