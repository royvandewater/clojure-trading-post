(ns clojure-trading-post.core-test
  (:require [clojure.test :refer :all]
            [clojure-trading-post.core :refer :all]))

(deftest test-access-token-from-file
  (testing "Retrieving the access token"
    (is (= {} (access-token-from-file "./credentials.json")))))

(deftest test-create-buy-order
  (testing "Buy a stock"
    (is (= {} (create-buy-order access-token "GOOG" 1)))))

(deftest test-create-sell-order
  (testing "Sell a stock"
    (is (= {} (create-sell-order access-token "CTXS" 5)))))

(deftest test-get-user
  (testing "Retrieving a user"
    (is (= {} (get-user access-token)))))
