(ns clj-rest-server.test-utils
  (:require [clj-http.client :as client]
            [cheshire.core :refer [generate-string]]
            [taoensso.timbre :refer [debug]]))

(def base-options {:throw-exceptions false})

(defn rest-request [request-fn url & opts]
  (let [options (merge base-options (first opts))
        {:keys [status] :as response} (select-keys (request-fn url options) [:status :body])]
    (case status
      200 (update response :body cheshire.core/parse-string)
      response)))

(defn rest-get [url]
  (rest-request client/get url))

(defn rest-post [url body]
  (rest-request client/post url {:body (generate-string body)
                                 :content-type :json}))

(defn rest-put [url body]
  (rest-request client/put url {:body (generate-string body)
                                :content-type :json}))

(defn rest-delete [url]
  (rest-request client/delete url))
