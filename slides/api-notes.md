# API

There are four functions in the Replicant API:

1. You can render hiccup to a DOM element
   * For some simple applications, `render` is the only function you need to use.
2. You can unmount what you have rendered to a DOM element
   * Mostly useful when you have rendered something to 3rd party libraries.
3. If you use data oriented event handlers – you really should – you need to register a dispatch function
4. You can render hiccup to a string

\newpage