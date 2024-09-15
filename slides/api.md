<div class="slide">

# API

“_Replicant is **a rendering library**. That's it. There's no state management, there's no async rendering, there's no networking utilities. There's just a single function that renders and rerenders your hiccup to the DOM in an efficient manner._”

<div style="display: flex; flex-direction: row;">
<div style="display: flex; flex-direction: column; flex: 1;">

* `(replicant.dom/render el hiccup)`<br> 
* `(replicant.dom/unmount el)`<br>
* `(replicant.dom/set-dispatch! f)`<br>
  Register your dispatch function
  * `f` will be called for all non-function events and hooks
  * `f` will receive two arguments:
    1. Trigger details (such as the JS event)
    2. The data from the event handler attribute
* `(replicant.string/render hiccup & [{:keys [indent]}])`

</div>

<div class="column" style="flex: 1; max-height: 65svh; font-size: 95%">

```clojure
(defn banner-view 
  [{:ui/keys [banner-text]}]
  [:div#banner
   [:p banner-text]
   [:button {:on {:click #(js/alert %)}}
    "Dismiss"]])

(comment
  (r/render
   (js/document.getElementById "app")
   (banner-view {:ui/banner-text
                 "An annoying banner"}))
  :rcf)

(defn banner-view
  [{:ui/keys [banner-text]}]
  [:div#banner
   [:p banner-text]
   [:button
    {:on
     {:click
      [[:db/dissoc :ui/banner-text]]}}
    "Dismiss"]])
```
</div>

</div>

</div>
