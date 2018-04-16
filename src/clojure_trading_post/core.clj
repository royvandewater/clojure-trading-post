(ns clojure-trading-post.core
  (:require [clojure-trading-post.http :as http]))

(defn get-user
  "Retrieve the user record"
  [access-token]
  (http/get access-token "/profile"))
