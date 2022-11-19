(defproject stackover "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]

                 [ring/ring-core "1.8.1"]
                 [ring/ring-jetty-adapter "1.8.1"]

                 [compojure "1.6.2"]

                 [http-kit "2.6.0"]

                 [cheshire "5.11.0"]

                 [org.clj-commons/claypoole "1.2.2"]]
  :repl-options {:init-ns stackover.core}
  :main stackover.core)
