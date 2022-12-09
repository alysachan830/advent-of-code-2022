(ns day4b
  (:require '[clojure.string :as str]))

(def total-pairs-with-fully-contain-other-sections
  #(reduce (fn [total pair]
             (let [all-section-nums (->> (str/split pair #",|-")
                                         (map read-string)
                                         (vec))
                   min-num (apply min all-section-nums)
                   i (.indexOf all-section-nums min-num)]

                ;; Find the min among those 4 numbers
                ;; That min number will only appear in 0 or 2 in all-section-nums
                ;; Then, find the partner of that min number
                ;; If that partner number is larger or equal to another pair's min number,
                ;; that means two pairs are overlapped

                (cond
                  (and (= 2 i)
                       (>= (get all-section-nums 3) (get all-section-nums 0))) (+ total 1)
                  (and (= 0 i)
                       (>= (get all-section-nums 1) (get all-section-nums 2))) (+ total 1)
                  :else total)
             )) 0 %))

(-> (slurp "../input/day4.txt")
    (str/split-lines)
    (total-pairs-with-fully-contain-other-sections))