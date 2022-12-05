(ns day1a
    (:require '[clojure.string :as s]))

(def find-largest-calories
  #(reduce (fn [calories-counters calories]
             (prn calories-counters)
            (let [{largest-calories :largest-calories
                   current-calories :current-calories} calories-counters]
              (prn calories)
              (if (s/blank? calories)
                (if (> current-calories largest-calories)
                  (assoc calories-counters :largest-calories current-calories :current-calories 0)
                  (assoc calories-counters :current-calories 0))
                (assoc calories-counters :current-calories (+ current-calories (read-string calories))))))
           {:largest-calories 0 :current-calories 0} %))

(-> (slurp "../input/day1a.txt")
    (s/split-lines)
    (conj "")
    (find-largest-calories)
    (:largest-calories))