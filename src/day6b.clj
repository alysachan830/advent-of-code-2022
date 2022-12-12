;; The only difference between day6a and day6b:
;; changing 4 to 14

(ns day6b)

(def input-string
  (slurp "../input/day6.txt"))

(loop [start 0
       end 14]
  (if (= 14 (-> (subs input-string start end)
                (set)
                (count)))
    end
    (recur (inc start)
           (inc end))))