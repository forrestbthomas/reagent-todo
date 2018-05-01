(ns todo.input
  (:require [reagent.core :as r]
            [reagent-material-ui.core :as M]))

(defn handle-input [session e]
  (swap! session assoc :todo-input (-> e .-target .-value)))

(defn handle-enter [session e]
  (when (= "Enter" (-> e .-key))
    (swap! session update :todo-list #(vec (conj % {:text (-> e .-target .-value)
                                           :checked nil})))
    (swap! session assoc :todo-input "")))

(defn input-field [session]
  [M/TextField {:on-change (fn [e] (handle-input session e))
                :on-key-press (fn [e] (handle-enter session e))
                :value (get @session :todo-input "")
                :floating-label-text "Enter TODO..."}])
