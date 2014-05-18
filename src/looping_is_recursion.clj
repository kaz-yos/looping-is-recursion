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


;; Exercise 4
;; Implement the function (find-first-index [predicate seq]) that returns the first index in seq for which predicate returns true, or nil if no such index exists.
;;
;; signature: pred, seq -> number
;; purpose: return index for the first element in seq that give true by pred
;; stub
(defn find-first-index [pred a-seq]
  -1)
;;
(defn find-first-index [pred a-seq]
  (loop [sq  a-seq
         acc 0]
    (cond
     ;; two stop conditions
     ;; no more elements
     (empty? sq)       nil
     ;; predicate returns true
     (pred (first sq)) acc
     ;; otherwise recur
     :else             (recur (rest sq) (inc acc)))))
;;
(ctest/is (= (find-first-index zero? [1 1 1 0 3 7 0 2])                    3))
(ctest/is (= (find-first-index zero? [1 1 3 7 2])                          nil))
(ctest/is (= (find-first-index (fn [n] (= n 6)) [:cat :dog :six :blorg 6]) 4))
(ctest/is (= (find-first-index nil? [])                                    nil))


;; Exercise 5
;; Implement the function (avg a-seq) that computes the average of a sequence.
;; Hint: You need to keep track of multiple things in the loop.
;;
;; signature: seq -> number
;; purpose: return the average over the sequence
;; stub
;; (defn avg [a-seq]
;;   -1)
;;
(defn avg [a-seq]
  (if (empty? a-seq)
    ;; Stop with nill if the sequence is empty to avoid error
    nil
    
    (loop [sq    a-seq
           sum   0
           denom 0]
      (if (empty? sq)
        (/ sum denom)
        (recur (rest sq) (+ sum (first sq)) (inc denom))))))
;;
(ctest/is (= (avg [])        nil))
(ctest/is (= (avg [1 2 3])   2))
(ctest/is (= (avg [0 0 0 4]) 1))
(ctest/is (= (avg [1 0 0 1]) 1/2))


(defn parity [a-seq]
  ":(")

(defn fast-fibo [n]
  ":(")

(defn cut-at-repetition [a-seq]
  [":("])

