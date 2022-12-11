(ns day5a
  (:require '[clojure.string :as str]))

(defn init-stacks [s]
  (->> (reverse s)
       (reduce (fn [all-stacks row]
                 (let [stack (reduce-kv (fn [row->column idx crate]
                                          (if (str/blank? crate)
                                            row->column
                                            (assoc row->column
                                                   (+ 1 idx)
                                                   [(subs crate 1 2)]))) {} row)]
                   (merge-with into all-stacks stack)))
               {})))

;; init-stacks data structure is a map:
;; {1 ["A" "B" "C"], 2 ["S" "X" "T"], 3 ["E" "Z"]...}

;; instructions data structure is a 2-dimensional list:
;; ((" 2 " " 3 " " 1 ") (" 6 " " 1 " " 5 ") ...)

(let [init-stacks (->> (slurp "../input/day5-stacks.txt")
                       (str/split-lines)
                       (map #(str/split % #"    | "))
                       (init-stacks))
      instructions (->> (slurp "../input/day5-move-instructions.txt")
                        (str/split-lines)
                        (map #(re-seq #"\s\d+\s|\s\d" %)))]

  (->> (reduce (fn [all-stacks instruction]
                 (let [[steps start-stack-id destination-stack-id] (vec (map read-string instruction))
                       start-stack                             (get all-stacks start-stack-id)
                       destination-stack                       (get all-stacks destination-stack-id)
                       updated-destination-stack               (->> (take-last steps start-stack)
                                                                    (reverse)
                                                                    (concat destination-stack)
                                                                    (vec))
                       updated-start-stack                     (-> (take (- (count start-stack) steps) start-stack)
                                                                   (vec))]
                   (assoc all-stacks start-stack-id updated-start-stack
                          destination-stack-id updated-destination-stack)))
               init-stacks instructions)
       (reduce-kv (fn [result-str _ stack]
                    (str result-str (last stack)))
                  "")))