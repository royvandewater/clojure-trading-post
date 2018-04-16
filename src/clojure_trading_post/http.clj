(ns clojure-trading-post.http
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.string :as string]))

(declare build-url get-for-response ensure-pathname-formatting get)

(defn get
  "Do an HTTP GET request and return the parsed body"
  [oauth-token pathname]
  (json/read-str
    (:body (get-for-response oauth-token pathname))
    :key-fn keyword))

; Private methods

(defn- build-url
  "Create a trading-post request url given a pathname"
  [pathname]
  (str "https://trading-post.club" (ensure-pathname-formatting pathname)))

(defn- ensure-pathname-formatting
  "Ensures that the pathname has both a leading and trailing slash"
  [pathname]
  (cond
    (not (string/starts-with? pathname "/")) (ensure-pathname-formatting (str "/" pathname))
    (not (string/ends-with? pathname "/"))  (ensure-pathname-formatting (str pathname "/"))
    :else pathname))

(defn- get-for-response
  "Do an HTTP GET request and return a response object"
  [oauth-token pathname]
  (client/get (build-url pathname) {:oauth-token oauth-token}))
