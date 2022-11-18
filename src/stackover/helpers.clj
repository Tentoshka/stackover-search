(ns stackover.helpers)

(defn some-in [x coll]
  (some #(= x %) coll))
