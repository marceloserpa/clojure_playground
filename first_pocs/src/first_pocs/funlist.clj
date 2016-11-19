(ns first-pocs.funlist)

(defn sum-all [elements] (reduce +  elements) )

(println (sum-all [1, 3, 4, 6]))