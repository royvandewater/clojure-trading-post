(ns clojure-trading-post.core-test
  (:require [clojure.test :refer :all]
            [clojure-trading-post.core :refer :all]))

(def access-token "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik1UZ3dPVVkyUkRNd1JqZEJOVFkyTlVFNVJVVTFRME5FTUVJMk1UWXhSVEl5UXpsRVJEWXlRdyJ9.eyJpc3MiOiJodHRwczovL3RyYWRpbmctcG9zdC5hdXRoMC5jb20vIiwic3ViIjoiZ2l0aHVifDcyMDE2IiwiYXVkIjpbImh0dHBzOi8vdHJhZGluZy1wb3N0LmNsdWIiLCJodHRwczovL3RyYWRpbmctcG9zdC5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNTIzOTA3NTU0LCJleHAiOjE1MjM5OTM5NTQsImF6cCI6InFIdUhidnlGbjBMUlh4Rk1pQ0hLcE1TSUVWQ2NoWU5kIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBvZmZsaW5lX2FjY2VzcyJ9.BHp2qtPcGvacEl-4V3dTbM1OkoXBls4ZFLzZFzoS2r99G5tx2zI3KX8ixOvWDs0zqw7IZZTN-JUiUD-kAnkF9lNbeuQYpIAfXGhzx0FKc2uyOWUQpZbNGeo7qz9e161_vPEmJ4nPb_z9zj_wKdL6rOZULMijC7svPqRtG6dNgCWf14ylILBQhdLMOCa2LMBPCxrJlWtS5jexAgrUKLUCot7ALmP_DeaPpuP3HZ1W3siRPCK_ZTiWVQh45MTM3cyUcR_hPd-K4UvhRM_3MJTk-LHMKYHjbUuZD9ZdmLpCde5SFDaDocwZ5RKhJm-LYfWpfKE8EVDFDaMYjRSBoUl9og")

(deftest test-create-buy-order
  (testing "Buy a stock"
    (is (= {} (create-buy-order access-token "GOOG" 1)))))

(deftest test-create-sell-order
  (testing "Sell a stock"
    (is (= {} (create-sell-order access-token "CTXS" 5)))))

(deftest test-get-user
  (testing "Retrieving a user"
    (is (= {} (get-user access-token)))))
