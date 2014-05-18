(ns looping-is-recursion
  (:require [clojure.test :as ctest]))

;; Exercise 1
;; Write the function (power n k) that computes the mathematical expression nk.
;;
;; signature: number,number -> number
;; purpose: Return base^exp
;; stub:
;; (defn power [base exp]
;;   0)
;; 
(defn power
  ([base exp] (power base exp 1))
  ([base exp acc]
     (if (<= exp 0)
       acc
       (recur base (dec exp) (* acc base)))))
;;
;; testing by clojure.test test suit
(ctest/is (= (power 2 2)   4))
(ctest/is (= (power 5 3) 125))
(ctest/is (= (power 7 0)   1))
(ctest/is (= (power 0 10)  0))



;; Exercise 2
;; Compute the last element of a sequence.
;; signature: seq -> a
;; purpose: get the last value, if not available nil
;; stub
;; (defn last-element [a-seq]
;;   ":(")
;;
(defn last-element [a-seq]
  ;; if empty sequence or 1 more element before empty
  (if (or (empty? a-seq) (empty? (rest a-seq)))
    ;; return first element (empty or last element)
    (first a-seq)
    (recur (rest a-seq))))
;;
(ctest/is (= (last-element [])      nil))
(ctest/is (= (last-element [1 2 3]) 3))
(ctest/is (= (last-element [2 5])   5))


;; Exercise 3
;; Write the function (seq= a-seq b-seq) that compares two sequences for equality.
;; signature: seq,seq -> bool
;; purpose: compare two seqs elementwise
;; stub
;; (defn seq= [seq1 seq2]
;;   :-)
;;
(defn seq= [seq1 seq2]
  (cond
   ;; if both are empty, equal
   (and (empty? seq1) (empty? seq2))   true
   ;; if seq1 xor seq2 is empty, not equal
   (or  (empty? seq1) (empty? seq2))   false
   ;; If non-empty elements do not match, not equal
   (not (= (first seq1) (first seq2))) false
   ;; Otherwise recur
   :else                               (recur (rest seq1) (rest seq2))))
;;
(ctest/is (= (seq= [1 2 4] '(1 2 4)))  true)
(ctest/is (= (seq= [1 2 3] [1 2 3 4])) false)
(ctest/is (= (seq= [1 3 5] []))        false)


(defn find-first-index [pred a-seq]
  ":(")

(defn avg [a-seq]
  -1)

(defn parity [a-seq]
  ":(")

(defn fast-fibo [n]
  ":(")

(defn cut-at-repetition [a-seq]
  [":("])

