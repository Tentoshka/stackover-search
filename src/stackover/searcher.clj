(ns stackover.searcher
  (:require
   [stackover.helpers :refer [some-in]]
   [org.httpkit.client :as http]
   [cheshire.core :refer [parse-string]]))

(def link "https://api.stackexchange.com/2.2/search?pagesize=100&order=desc&sort=creation&site=stackoverflow&tagged=")

(defmulti get-res (fn [tags]
                    (if (string? tags)
                      :string
                      :vector)))

(defn search-one [tag]
  (-> @(http/get (str link tag))
      :body
      (parse-string true)
      :items))

(defn search-many [tags]
  (map search-one tags))

(defn answered-count [tag col]
  (->>
   (for [c col]
     (when (some-in tag (:tags c))
       (:is_answered c)))
   (filter identity)
   count
   ))

(defn total-count [tag col]
  (->>
   (for [c col]
     (when (some-in tag (:tags c))
       true))
   (filter identity)
   count
   ))

(defn answer [tag search-res]
  {(keyword tag)
   {:total    (total-count tag search-res)
    :answered (answered-count tag search-res)}})

(defmethod get-res :string
  [tag]
  (let [search-res (search-one tag)]
    (answer tag search-res)))

(defmethod get-res :vector
  [tags]
  (let [search-res (->> tags
                        set
                        search-many
                        (apply concat)
                        set)]
    (for [tag tags]
      (answer tag search-res))))

(comment

  (get-res "clojure")

  (answered-count "clojure" (:items (search-one "clojure")))

  (total-count "clojure" (:items (search-one "java")))

  )
