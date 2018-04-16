(ns clojure-trading-post.credentials
  (:import java.util.Base64)
  (:require [cheshire.core :as cheshire]
            [clj-http.client :as client]
            [clj-time.core :as time]
            [clj-time.coerce :as time-coerce]
            [clojure.data.codec.base64 :as base64]
            [clojure.string :as string]))

(declare
  access-token-expiration
  base64->string
  now
  read-file-as-json
  refresh-access-token
  use-refresh-token
  use-refresh-token-for-response
  valid-access-token?)

(defn access-token-from-file
  "Get access-token from a credentials.json file. A credentials.json must have at least a refresh token."
  [credentials-file-path]
  (let [file-contents (read-file-as-json credentials-file-path)
        {access-token :access_token, refresh-token :refresh_token} file-contents]
    (if (valid-access-token? access-token)
      access-token
      (refresh-access-token credentials-file-path refresh-token))))

(defn- access-token-expiration
  "Return the expiration datetime for an access-token"
  [access-token]
  (let [payload-b64 (second (string/split access-token #"\."))
        payload (cheshire/parse-string (base64->string payload-b64) true)]
    (:exp payload)))

(defn- base64->string
  "Convert a base64 encoded string to a regular string"
  [to-decode]
  (String. (.decode (Base64/getDecoder) to-decode)))

(defn- now
  "Return the current UTC time as a long"
  []
  (time-coerce/to-long (time/now)))

(defn- read-file-as-json
  "Reads the file and returns a map with the contents parsed from JSON"
  [file-path]
  (cheshire/parse-string (slurp file-path) true))

(defn- refresh-access-token
  "Exchange the refresh token for a new access token and refresh token and write them to the credentials.json file"
  [credentials-file-path refresh-token]
  (let [{access-token :access_token} (use-refresh-token refresh-token)]
    (spit credentials-file-path
      (cheshire/generate-string
        {:access_token access-token, :refresh_token refresh-token}
        {:pretty true}))
    access-token))

(defn- use-refresh-token
  "Exchange the refresh token for a new access token and refresh token"
  [refresh-token]
  (cheshire/parse-string (:body (use-refresh-token-for-response refresh-token)) true))

(defn- use-refresh-token-for-response
  "Exchange the refresh token for a new access token and refresh token and return the HTTP response"
  [refresh-token]
  (client/post
    "https://trading-post.club/tokens"
    {:form-params {:grant_type "refresh_token", :refresh_token refresh-token}
     :content-type :json}))

(defn- valid-access-token?
  "returns true if the access-token is non-nil and is not expired"
  [access-token]
  (and
    (not (empty? access-token))
    (> (now) (access-token-expiration access-token))))
