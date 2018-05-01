(ns ^:figwheel-no-load todo.dev
  (:require
    [todo.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
