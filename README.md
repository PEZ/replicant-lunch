# Clojure Lunch Stockholm #4 - feat. Replicant

Wednesday, September 18 2024

You find the presentation slides in the [slides](slides/) directory. This is the opening slide:
* [slides/hello-one.md](slides/hello.md)

Use the VS Code command **Markdown: Open Preview** to make the file render as a slide. When run as a slide show (using [next-slide.cljs](.joyride/src/next_slide.cljs)) you can use arrow keys to navigate the deck while in Markdown preview mode.

To run the presentation as a slide show you need to:

1. Install the Joyride extension.
2. Reload the VS Code window: **Developer: Reload Window**

The next-slide.cljs script will automatically activate, when you reload the VS Code window with Joyride installed.

## next-slide.cljs

To navigate the slides using the arrow keys you need to configure keyboard shortcuts. 

Check [.joyride/src/next_slide.cljs](.joyride/src/next_slide.cljs) for the keyboard shortcuts. You can select the line commmented shortcuts block and toggle comments, paste the results in your `keybindings.json` file. (There's a VS Code command for easily finding it.) Don't forget to toggle comments again, or the script will be broken (killed by JSON 😄).

The commented block also has shortcuts for toggling between preview and source for the slides. (Well, any markdown file, actually.)

The project has VS Code settings that zoom the VS Code UI, and the editor font sizes to suit my laptop and tastes. You might need to tweak this a bit to get the slides to fit your screen. See also [style.css](./style.css).

## next-slide-notes.cljs

There's also the script [.joyride/src/next_slide_notes.cljs](.joyride/src/next_slide_notes.cljs) which lets you jump between the slide markdown files and the corresponding slide notes markdown files. (Doesn't work from Preview mode, nb.)

There are also a command for generating a command line which let's you print the notes as a PDF. You will need Pandoc and LaTeX for the command line to work.
