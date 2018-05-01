(ns todo.list
  (:require [reagent.core :as r]
            [reagent-material-ui.core :as M]))
;; --------------------
;; Helpers
(def el r/as-element)

(defn toggle-checkbox! [session idx]
  (swap! session update-in [:todo-list idx :checked] not)
  (swap! session update-in [:todo-list idx :strike] not))

(defn remove-item! [session idx]
  (swap! session update-in [:todo-list] #(vec (concat (subvec % 0 idx)
                                                      (subvec % (inc idx))))))

;; --------------------
;; Views
(defn ListItems [session]
  (vec (cons :div 
             (map-indexed (fn [idx item]
                            [:div
                             [M/ListItem {:class (when (get-in @session [:todo-list idx :strike])
                                                   :strike)
                                          :primary-text (get item :text)
                                          :left-icon (el [M/Checkbox {:checked (get-in @session [:todo-list idx :checked])
                                                                      :on-check #(toggle-checkbox! session idx)}])
                                          :right-icon (el [M/RaisedButton {:label :X
                                                                           :on-click #(remove-item! session idx)}])}]
                             [M/Divider]])
                          (get @session :todo-list)))))

(defn todo-list [session]
  [M/Paper {:class :todo-list
            :zDepth 2
            :rounded false}
   [M/List
    (ListItems session)]])
