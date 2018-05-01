(ns todo.core
    (:require [reagent.core :as r] 
              [reagent-material-ui.core :as M]
              [todo.input :as i]
              [todo.list :as l]))

;; -------------------------
;; Session State
(def session (r/atom {}))

;; -------------------------
;; Views

(defn home-page []
  [:div {:class :app}
   [:header {:class :app-header} 
    [:p "TODO MVC - Reagent"]]
   [i/input-field session]
   [l/todo-list session]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [M/MuiThemeProvider 
             {:mui (M/getMuiTheme M/darkBaseTheme)} 
             [home-page]] 
            (.getElementById js/document "app")))

(defn init! []
  (mount-root))
