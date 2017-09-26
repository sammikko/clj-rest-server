(ns clj-rest-server.util)

(defn parse-long [s]
  (try
    (Long/parseLong s)
  (catch NumberFormatException nfe
    (throw (IllegalArgumentException. "id must be a long value")))))
