# Replicant

Replicant is one of those librares that makes you exclaim _“I can't believe it's not React!”_.

But there is no dependency on React in there. It pulls in zero dependencies! I copied this dependency map from Replicant's `deps.edn` file.

Before Replicant, Christian Johansen's wrote Dumdom, which was API compatible with Quiescent. But where Quiscent relied on React, Dumdom used snabbdom, a super tiny (and fast) virtual DOM library written in JavaScript.

Replicant is very similar to Dumdom, but even more data oriented, and it implements its own virtual DOM, completely in Clojure.

If you want to understand where Replicant comes from, you should watch Christian's talk _Stateless Datadriven UIs_ from JavaZone Oslo last year. Before he wrote Replicant.

\newpage

# Replicant 2

Like snabbdom and Dumdom, Replicant is a library, not a framework. It doesn't manage state, it doesn't even have components. It's a rather lean library, at that, with only four API functions, of which only one is needed in all Replicant apps. All Replicant does is render hiccup to the DOM.

Christan loves data. He made Replicant so that you can build apps where views are only about data, and where data travels in only one direction, from your model, to your views. There are no reactive atoms, and no subscriptions. Your views always render the same thing, given the same data.

Because views are just functions. Pure functions. And if you wire up your application carefully, your functions can return only data, and nothing but data.

\newpage

# Replicant 3

Christian also loves performance. So Replicant will transform your data to DOM in a very efficient manner. The Replicant code has disclaimers about some of it not being idiomatic Clojure or ClojureScript, for the sake of performance.

It's entirely possible to write a Reagent app that would also work with Replicant, with only a few changes. You just can't use reactive atoms, and of course no local state. If you can't live without local state, Replicant is not for you.

Without a framework you are on your own. Yours is the responsibility to architect your app. And you are free to do it the way that makes most sense to you.

I am a big fan of re-frame. Since Replicant is not a framework, it allows me to use it as a building block for my own tiny, re-frame like framework. It won't let me mimic the subscriptions part. But that's a feature, not a bug.

\newpage