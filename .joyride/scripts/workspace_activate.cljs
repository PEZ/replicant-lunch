(ns workspace-activate
  (:require [joyride.core :as joyride]
            ["vscode" :as vscode]
            [promesa.core :as p]
            [next-slide]
            [next-slide-notes]
            [showtime]
            [file-server]))

(defonce !db (atom {:disposables []}))

;; To make the activation script re-runnable we dispose of
;; event handlers and such that we might have registered
;; in previous runs.
(defn- clear-disposables! []
  (run! (fn [disposable]
          (.dispose disposable))
        (:disposables @!db))
  (swap! !db assoc :disposables []))

;; Pushing the disposables on the extension context's
;; subscriptions will make VS Code dispose of them when the
;; Joyride extension is deactivated.
(defn- push-disposable [disposable]
  (swap! !db update :disposables conj disposable)
  (-> (joyride/extension-context)
      .-subscriptions
      (.push disposable)))

(defn ^:export evaluate-clipboard+ []
  (p/let [clipboard-text (vscode/env.clipboard.readText)]
    (when (not-empty clipboard-text)
      (vscode/commands.executeCommand "joyride.runCode" clipboard-text))))

(defn- add-joy-run-item! []
  (let [item (vscode/window.createStatusBarItem
              vscode/StatusBarAlignment.Left
              -1000)]
    (set! (.-text item) "J! $(play)")
    (set! (.-command item)
          (clj->js
           {:command "joyride.runCode"
            :arguments ["(workspace-activate/evaluate-clipboard+)"]}))
    (.show item)
    item))

(defn- init-vic-item! [port]
  (let [item (vscode/window.createStatusBarItem
              vscode/StatusBarAlignment.Left
              -1000)]
    (set! (.-text item) "vic-20")
    (set! (.-command item)
          (clj->js
           {:command "simpleBrowser.show"
            :arguments [(str "http://localhost:" port
                             "/slides/interactive-programming/js-vic-20.html")]}))
    (.show item)
    item))

(defn- my-main []
  (println "Hello World, from my-main workspace_activate.cljs script")
  (clear-disposables!)
  (push-disposable (showtime/init!))
  (next-slide/activate!)
  (let [port 6789]
    (file-server/start! port)
    (push-disposable (init-vic-item! port)))
  (push-disposable (add-joy-run-item!)))

(when (= (joyride/invoked-script) joyride/*file*)
  (my-main))

(comment
  @!db
  :rcf)