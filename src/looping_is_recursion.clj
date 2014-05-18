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

;; Exercise 6
;; Write the function (parity a-seq) that takes in a sequence and returns a set of those elements that occur an odd number of times in the sequence.
;; Hint: Recall the fuction (toggle set elem) from Structured data
;; (toggle #{1 2 3} 1) ;=> #{2 3}
;; (toggle #{2 3} 1) ;=> #{1 2 3}
;;
;; sig: seq -> set
;; purpose: return elements that occur an odd number of times
;; stub:
;; (defn parity [a-seq]
;;   #{:-});; ;; 
;;
(defn parity [a-seq]
  ;; define toggle function locally
  (let [toggle (fn [a-set elem]
                 (if (contains? a-set elem)
                   (disj a-set elem)
                   (conj a-set elem)))]
    ;; loop with set-type accumulator
    (loop [sq  a-seq
           acc #{}]
      
      (if (empty? sq)
        ;; end if no element left
        acc
        ;; recur toggling the current element in/out the acc
        (recur (rest sq) (toggle acc (first sq)))))))

;;
(ctest/is (= (parity [:a :b :c])            #{:a :b :c}))
(ctest/is (= (parity [:a :b :c :a])         #{:b :c}))
(ctest/is (= (parity [1 1 2 1 2 3 1 2 3 4]) #{2 4}))


;; Exercise 7
;; Write the function (fast-fibo n) that computes the nth fibonacci number using loop and recur. Do not use recursion.
;; Hint: to avoid recursion, you need to keep track of Fn−1 and Fn in the loop.
;;
;; sig: number -> number
;; purpose return nth fibonacci number
;; stub:
;; (defn fast-fibo [n]
;;   -1)
;;
(defn fast-fibo [n]
  (cond
   ;; Return value directly for those
   (<= n 0) 0
   (=  n 1) 1
   (=  n 2) 1
   
   ;; Otherwise recur
   :else (loop [Fn-2 1
                Fn-1 1
                Fn   2
                i    3] ; First step is at 3
           ;; Check iteration reached the target n
           (if (= i n)
             ;; if true, return the current fibonacci
             Fn
             ;; Otherwise, move one step
             (recur Fn-1 Fn (+ Fn-1 Fn) (inc i))))))

;;
(ctest/is (= (fast-fibo 0) 0))
(ctest/is (= (fast-fibo 1) 1))
(ctest/is (= (fast-fibo 2) 1))
(ctest/is (= (fast-fibo 3) 2))
(ctest/is (= (fast-fibo 4) 3))
(ctest/is (= (fast-fibo 5) 5))
(ctest/is (= (fast-fibo 6) 8))


;; Exercise 8
;; Write the function (cut-at-repetition a-seq) that takes in a sequence and returns elements from the sequence up to the first repetition.
;; Hint: Remember that conjing onto a vector appends the element.
;; Hint: A set might be helpful
;;
;; sig: seq -> seq
;; purpose: end at first repetition
;; stub:
;; (defn cut-at-repetition [a-seq]
;;   [":("])
;;
(defn cut-at-repetition [a-seq]
  (loop [sq            a-seq
         acc           []
         rep-check-set #{}]
    ;;
    (let [fst-elt (first sq)]
      ;;
      (cond
       ;; stop conditions
       ;; Nothing left
       (empty? sq)                       acc
       ;; Duplication detected
       (contains? rep-check-set fst-elt) acc
       ;; Otherwise
       :else                             (recur (rest sq) (conj acc fst-elt) (conj rep-check-set fst-elt))))))
;;
(ctest/is (= (cut-at-repetition [1 1 1 1 1]) [1]))
(ctest/is (= (cut-at-repetition [:cat :dog :house :milk 1 :cat :dog]) [:cat :dog :house :milk 1]))
(ctest/is (= (cut-at-repetition [0 1 2 3 4 5]) [0 1 2 3 4 5]))

