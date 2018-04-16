(ns clojure-trading-post.core
  (:require [clj-http.client :as client]))

(defn get-user
  "Retrieve the user record"
  [access-token]
  (println
    (client/get "https://trading-post.club/profile" {:oauth-token access-token})))
