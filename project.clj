(defproject xchange "0.1.0-SNAPSHOT"
  :description "Social platform for video games exchange"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ "1.1.0"]
                 [bouncer "1.0.1"]
                 [org.clojure/java.jdbc "0.7.3"]
                 [org.postgresql/postgresql "42.1.4"]
                 [migratus "1.0.1"]]
  :plugins [[lein-environ "1.1.0"]]
  :main ^:skip-aot xchange.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :aliases {"m:create"   ["run" "-m" "xchange.utils.migrations/create"]
            "m:migrate"  ["run" "-m" "xchange.utils.migrations/migrate"]
            "m:rollback" ["run" "-m" "xchange.utils.migrations/rollback"]})
