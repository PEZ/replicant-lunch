<div class="slide">

# Replicant Renders
hiccup -> virtual DOM -> real DOM, considering:

<div style="display: flex; flex-direction: row;">
<div style="display: flex; flex-direction: column; flex: 1;">

* Element lifecycle management
  * Including `:replicant/mounting` and `:replicant/unmounting`
  * Hooks can be functions or data
    * Data oriented hooks become calls to your dispatch function
  * e.g. `:replicant/on-mount`
  * Any element, not just the “component”
* Event handlers can be functions or data
  * Data oriented handlers become calls to your dispatch function
</div>

<div class="column" style="flex: 1; max-height: 75svh; font-size: 80%">

```clojure
(defn banner-view [{:ui/keys [banner-text]}]
  [:div#banner
   {:style {:top 0
            :transition "top 0.25s"}
    :replicant/mounting {:style
                         {:top "-100px"}}
    :replicant/unmounting {:style
                           {:top "-100px"}}}
   [:p banner-text]
   [:button
    {:on {:click [[:db/dissoc
                   :ui/banner-text]]}}
    "Dismiss"]])
    
(defn- main-view [state]
  [:div {:style {:position "relative"}}
   (when (:ui/banner-text state)
     (banner-view state))
   [:h1 "A tiny (and silly) Replicant example"]
   (edit-view state)
   (display-view state)])
```
</div>

</div>

</div>
