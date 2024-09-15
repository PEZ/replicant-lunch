;; == Keyboard shortcuts ==
;; Weird indent because of how comment block/selection works
    ;;{
    ;;    "key": "ctrl+alt+j s",
    ;;    "command": "joyride.runCode",
    ;;    "args": "(next-slide/activate!)"
    ;;},
    ;;{
    ;;    "key": "ctrl+alt+j ctrl+alt+s",
    ;;    "command": "joyride.runCode",
    ;;    "args": "(next-slide/deactivate!)"
    ;;},
    ;;{
    ;;    "key": "ctrl+alt+j ctrl+alt+m",
    ;;    "command": "markdown.showPreview",
    ;;},
    ;;{
    ;;    "key": "right",
    ;;    "command": "joyride.runCode",
    ;;    "args": "(next-slide/next! true)",
    ;;    "when": "next-slide:active && !inputFocus"
    ;;},
    ;;{
    ;;    "key": "left",
    ;;    "command": "joyride.runCode",
    ;;    "args": "(next-slide/next! false)",
    ;;    "when": "next-slide:active && !inputFocus"
    ;;},
    ;;{
    ;;    "key": "cmd+alt+ctrl+right",
    ;;    "command": "joyride.runCode",
    ;;    "args": "(next-slide/next! true)",
    ;;},
    ;;{
    ;;    "key": "cmd+alt+ctrl+left",
    ;;    "command": "joyride.runCode",
    ;;    "args": "(next-slide/next! false)"
    ;;},

(ns next-slide
  (:require ["vscode" :as vscode]
            [joyride.core :as joyride]
            [promesa.core :as p]
            [clojure.edn :as edn]
            [clojure.string :as string]))

(def !state (atom {:active? false
                   :active-slide 0}))

(defn ws-root []
  (if (not= js/undefined
            vscode/workspace.workspaceFolders)
    (.-uri (first vscode.workspace.workspaceFolders))
    (vscode/Uri.parse ".")))

(defn slides-list+ []
  (p/let [config-uri (vscode/Uri.joinPath (ws-root) "slides.edn")
          config-data (vscode/workspace.fs.readFile config-uri)
          config-text (-> (js/Buffer.from config-data) (.toString "utf-8"))
          config (edn/read-string config-text)]
    (:slides config)))

(defn next!
  ([]
   (next! true))
  ([forward?]
   (p/let [slides (slides-list+)
           next (if forward?
                  #(min (inc %) (dec (count slides)))
                  #(max (dec %) 0))]
     (swap! !state update :active-slide next)
     (vscode/commands.executeCommand "markdown.showPreview"
                                     (vscode/Uri.joinPath (ws-root)
                                                          (nth slides (:active-slide @!state)))))))

(defn deactivate! []
  (swap! !state assoc :active? false)
  (vscode/commands.executeCommand "setContext" "next-slide:active" false)
  (vscode/window.showInformationMessage
   (str "next-slide:" "deactivated")))

(defn activate! []
  (swap! !state assoc :active? true)
  (vscode/commands.executeCommand "setContext" "next-slide:active" true)
  (vscode/window.showInformationMessage
   (str "next-slide:" "activated")))

(when (= (joyride/invoked-script) joyride/*file*)
  (activate!))

(comment
  @!state
  (next!)
  (next! false)
  (activate!)
  (deactivate!)
  :rcf)