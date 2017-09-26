(ns clj-rest-server.middleware
  (:require [taoensso.timbre :refer [debug error]]))

(defn wrap-logging [handler]
  (fn [req]
    (try
      (debug "REQUEST" (select-keys req [:headers :uri :query-string :body :request-method :query-params]))
      (let [res (handler req)]
        (debug "RESPONSE" res)
        res)
    (catch Throwable t
      (error t)
      (throw t)))))