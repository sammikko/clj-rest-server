(ns clj-rest-server.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [response not-found]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-params wrap-json-body]]
            [taoensso.timbre :refer [debug]]
            [clj-rest-server.data :as data]
            [clj-rest-server.util :refer [parse-long]]
            [clj-rest-server.middleware :refer [wrap-logging]]))


(defn not-found-response []
  (not-found "Not Found"))

(defn resource-response [body]
  (if body (response body) (not-found-response)))

(defroutes rest-handler
  (POST "/:resource" [resource :as request]
    (response (data/create-resource resource (:body request))))
  (GET "/:resource/:id" [resource id]
    (resource-response (data/read-resource resource (parse-long id))))
  (PUT "/:resource/:id" [resource id :as request]
    (response (data/update-resource resource (parse-long id) (:body request))))
  (DELETE "/:resource/:id" [resource id]
    {:body (data/delete-resource resource (parse-long id))
     :status 202})
  (GET "/:resource" [resource]
    (response (data/read-resources resource)))
  (not-found-response))

(def app
  (-> rest-handler
      wrap-keyword-params
      wrap-json-body
      wrap-json-response
      wrap-logging))
