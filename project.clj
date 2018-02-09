(defproject starter "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.8.0-alpha2"]
                 [re-frame "0.10.4"]
                 [ns-tracker "0.3.1"]
                 [day8.re-frame/http-fx "0.1.5"]
                 [cljs-ajax "0.7.3"]

                 [io.pedestal/pedestal.service "0.5.3"]
                 [io.pedestal/pedestal.service-tools "0.5.3"]
                 [io.pedestal/pedestal.jetty "0.5.3"]
                 [com.walmartlabs/lacinia-pedestal "0.6.0"]

                 [yogthos/config "1.1"]
                 [bidi "2.1.3"]
                 [cheshire "5.8.0"]
                 [hiccup "1.0.5"]
                 [ring "1.6.3"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :resource-paths ["config" "resources"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"
                                    "test/js"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :garden {:builds [{:id           "app"
                     :source-paths ["src/clj"]
                     :stylesheet   starter.css/app
                     :compiler     {:output-to     "resources/public/css/app.css"
                                    :pretty-print? true}}]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.9"]
                   [cljsbuild "1.1.7"]]

    :plugins      [[lein-figwheel "0.5.14"]
                   [lein-doo "0.1.8"]]

    :source-paths ["dev"]

    :aliases      {"run-dev" ["trampoline" "run" "-m" "dev"]}}

   :uberjar
   {:prep-tasks   [["cljsbuild" "once" "min"]
                   "compile"]
    :uberjar-name "starter.jar"}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "starter.core/mount-root"}
     :compiler     {:main                 starter.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :jar          true
     :compiler     {:main            starter.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          starter.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"
                    :optimizations :none}}]}

  :main starter.server

  :aot [starter.server])
