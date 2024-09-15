(ns file-server
  (:require ["http" :as http]
            ["vscode" :as vscode]
            ["finalhandler" :as finalhandler]
            ["serve-static" :as serve-static]
            [joyride.core :as joyride]))

(defn ^:export start!
  "Starts a file-server on port `port`.
   Returns a Disposable-like object."
  [port]
  (let [serve (serve-static (-> vscode.workspace.workspaceFolders
                                first
                                .-uri
                                .-fsPath))
        server (http/createServer (fn on-request [req res]
                                    (serve req res (finalhandler req res))))]
    (.listen server port)
    #js {:dispose (fn [] (.close server))}))

(when (= (joyride/invoked-script) joyride/*file*)
  (println "Start the server with `(start!)`"))