# Replicant Renders

The render function efficiently takes care of all hiccup -> virtual DOM -> real DOM business. In doing so, it:

* Manages the lifecycle of the elements in the hiccup
  * It even manages elements while they are monting and unmounting, giving you the opportunity to use CSS for transioning elements in and out
* Looks for life cycle hooks and calls your dispatch function when they are triggered
  * Hooks can be attached to any element in the hiccup
    * If the hook is data, it will cause your dispatch function to be called with information about the hook, plus the data
* Wires up event handlers
  * If the handler is a function, it is attached “as is”
  * If the handler is data, the event will cause your dispatch function to be called with information about the event, plus the data

\newpage