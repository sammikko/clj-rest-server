(ns clj-rest-server.data
  (:require [taoensso.timbre :refer [debug]]))

(def store (atom {}))

(defn safe-inc [value]
  (if (nil? value) 1 (inc value)))

(defn next-sequence [resource]
  (-> (swap! store (fn [m] (update-in m [resource :sequence] safe-inc)))
      (get-in [resource :sequence])))

(defn create-resource [resource data]
  (let [id (next-sequence resource)
        data-with-id (assoc data "id" id)]
    (-> (swap! store (fn [m] (update-in m [resource :resources] assoc id data-with-id)))
        (get-in [resource :resources id]))))

(defn read-resource [resource id]
  (get-in @store [resource :resources id]))

(defn update-resource [resource id data]
  (let [data-with-id (assoc data "id" id)]
    (-> (swap! store (fn [m] (update-in m [resource :resources] assoc id data-with-id)))
        (get-in [resource :resources id]))))

(defn delete-resource [resource id]
  (swap! store (fn [m] (update-in m [resource :resources] dissoc id)))
  nil)

(defn read-resources [resource]
  (or (vals (get-in @store [resource :resources])) []))
