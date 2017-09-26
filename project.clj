(defproject clj-rest-server "0.1.0-SNAPSHOT"
  :description "Simple REST server in Clojure"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.3"]
                 [cheshire "5.8.0"]
                 [compojure "1.6.0"]
                 [ring/ring-json "0.4.0"]
                 [com.taoensso/timbre "4.10.0"]
                 [clj-http "3.7.0"]]
  :profiles {:test {:dependencies [[clj-http "3.7.0"]]}
             :repl {:main clj-rest-server.test-utils}}
  :main clj-rest-server.core)