(ns mandelbrot.core-test
  (:require [clojure.test :refer :all]
            [mandelbrot.core :refer :all]))

(deftest mandelbrot test
  (testing "is part of the Mandelbrot set"
    (is (mandel? [-0.5 0])))
  (testing "is not part of the Mandelbrot set"
    (is (not (mandel? [0.5 0])))))
