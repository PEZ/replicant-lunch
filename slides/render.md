<div class="slide">

# Replicant Renders
The `render` function efficiently takes care of all hiccup -> virtual DOM -> real DOM business. In doing so, it:

<div style="display: flex; flex-direction: row;">
<div style="display: flex; flex-direction: column; flex: 1;">

* Manages the lifecycle of the elements in the hiccup
  * It even manages elements while they are monting and unmounting
* Looks for life cycle hooks (e.g.<br>`:replicant/on-mount`) 
  and calls your dispatch function when they are triggered
  * Hooks can be attached to any element in the hiccup
* Wires up event handlers
  * If the handler is a function, it is attached “as is”
  * If the handler is data, the event will cause your dispatch function to be called with information about the event, plus the data
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
```
</div>

</div>

</div>
