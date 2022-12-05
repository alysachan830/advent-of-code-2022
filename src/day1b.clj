(ns day1b
  (:require '[clojure.string :as s]))

(def find-top-3
  (comp #(reduce + %)
        #(get % :top3)
        #(reduce (fn [m n]
                   (if (s/blank? n)
                     (->> (conj (:top3 m) (:acc m))
                          (sort)
                          (reverse)
                          (take 3)
                          (assoc m :acc 0 :top3))
                     (->> (read-string n)
                          (+ (:acc m))
                          (assoc m :acc)))) {:top3 [0 0 0] :acc 0} %)))


(-> (slurp "../input/day1a.txt")
    (s/split-lines)
    (conj "")
    (find-top-3))