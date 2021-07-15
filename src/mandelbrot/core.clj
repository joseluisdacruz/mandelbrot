(ns mandelbrot.core
  (:gen-class))

;; constants
(def n 20)
(def in-x -2.0)
(def fin-x 1)
(def in-y 1)
(def fin-y -1)
(def n-cols 80)
(def n-rows 24)

(defn next-fn [a b]
  (fn [[x y]]
    (let [next-x (+ (- (* x x) (* y y)) a)
          next-y (+ (* 2 x y) b)]
      [next-x next-y])))

(defn gen-iteration-seq [a b]
  (iterate (next-fn a b) [0 0]))

#_(gen-iteration-seq -0.5 0.0)

(defn check [[x y]]
  (> (+ (* x x) (* y y)) 4))

(defn mandel? [[a b]]
  (->
   (for [c (map check (take n (gen-iteration-seq a b)))
         :while (not c)] c)
   count
   (= n)))

#_(mandel? [-0.5 0]) ;; true

(defn gen-coordinates []
  (for [y (range in-y fin-y (/ (- fin-y in-y) n-rows))
        x (range in-x fin-x (/ (- fin-x in-x) n-cols))]
    [x y]))

(defn gen-char-sequence []
  (->>
   (gen-coordinates)
   (map mandel?)
   (map #(if % \* \space))))


(defn print-mandel []
  (doseq [row (partition n-cols (gen-char-sequence))]
    (println (apply str row))))

#_(print-mandel)


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (print-mandel))
