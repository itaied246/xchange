(defproject xchange "app"
  :description "Social platform for video games exchange"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [environ "1.1.0"]
                 [org.clojure/java.jdbc "0.7.3"]
                 [org.postgresql/postgresql "42.1.4"]
                 [migratus "1.0.1"]
                 [com.walmartlabs/lacinia-pedestal "0.5.0"]
                 [io.aviso/logging "0.2.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [com.gfredericks/test.chuck "0.2.8"]
                 [honeysql "0.9.1"]]
  :plugins [[lein-environ "1.1.0"]]
  :main ^:skip-aot xchange.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :test-selectors {:unit (complement :it)}
  :aliases {"mt:create"  ["run" "-m" "xchange.utils.test-data/create"]
            "m:create"   ["run" "-m" "xchange.utils.migrations/create"]
            "m:migrate"  ["run" "-m" "xchange.utils.migrations/migrate"]
            "m:rollback" ["run" "-m" "xchange.utils.migrations/rollback"]
            "m:reset"    ["run" "-m" "xchange.utils.migrations/reset"]
            "run:dev"    ["run" "with-profile" "dev"]}
  :monkeypatch-clojure-test false)
