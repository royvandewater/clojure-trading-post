(ns clojure-trading-post.core
  (:require [clojure-trading-post.credentials :as credentials]
            [clojure-trading-post.http :as http]))

(defn access-token-from-file
  "Get access-token from a credentials.json file. A credentials.json must have at least a refresh token."
  [credentials-file-path]
  (credentials/access-token-from-file credentials-file-path))

(defn create-buy-order
  "Create a buy order"
  [access-token ticker quantity]
  (http/post access-token "/profile/buy-orders" {:ticker ticker :quantity quantity}))

(defn create-sell-order
  "Create a sell order"
  [access-token ticker quantity]
  (http/post access-token "/profile/sell-orders" {:ticker ticker :quantity quantity}))

(defn get-user
  "Retrieve the user record"
  [access-token]
  (http/get access-token "/profile/"))
