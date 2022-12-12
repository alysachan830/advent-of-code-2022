(ns day6a)

(def input-string
  (slurp "../input/day6.txt"))

(loop [start 0
       end 4]
  (if (= 4 (-> (subs input-string start end)
               (set)
               (count)))
    end
    (recur (inc start)
           (inc end))))