(ns clj-rest-server.core
  (:require [aleph.http :as http]
            [aleph.netty :refer [wait-for-close]]
            [cheshire.core :refer [generate-string]]
            [taoensso.timbre :refer [debug]]
            [clj-rest-server.util :refer [parse-long]]
            [clj-rest-server.routes :as routes]
            [clj-rest-server.data :as data]))

(defn start-server [port]
  (debug "Listening port" port)
  (http/start-server #'routes/app {:port port}))

(defn- get-port [args]
  (if-let [port-arg (first args)]
    (parse-long port-arg)))

(defn -main [& args]
  (let [port (or (get-port args) 8080)]
    (wait-for-close
      (start-server port))))
