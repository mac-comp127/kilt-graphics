# kilt-graphics

**A graphics and UI library for learning software development principles in Java**

[API docs](https://mac-comp127.github.io/kilt-graphics/) | [Example project](https://github.com/mac-comp127/welcome-app)

This library provides a simple scene graph, animation, and user event handling facilities. Its capabailities are limited compared to Java 2D and Swing/AWT; however, it has several design features that let beginning coders focus less on library headaches and more on underlying principles:

- Its streamlined API keeps code simple at the point of use.
- It supports graphics without extra song and dance in either a single-pass main method or a fully interactive app with an event loop.
- It follows modern API conventions and avoids many of the OO design mistakes that Swing and AWT make (e.g. deep class hierarchies, LSP violations, mutable points).
- It makes sensible use of modern Java features such as enums and lambdas.
- Its closure-based event API more closely resembles the event handling developers are likely to encounter in modern platforms.


## Usage

To add kilt-graphics to a Gradle-based project, add JitPack in your `repositories` section:

```gradle
repositories {
    ...
    maven { url 'https://jitpack.io' }  // ← add this
}
```

Then add `kilt-graphics` to your `dependencies`:

```gradle
dependencies {
    ...
    implementation group: 'com.github.mac-comp127', name: 'kilt-graphics', version: '1.7'  // ← add this
    ...
}
```

You can also use kilt-graphics in any other build system that supports Maven-style dependencies. Note that you need to add `https://jitpack.io` to the list of Maven repositories in order for it to find the kilt-graphics library.

### Bugs and feature requests

To request an enhancement or report a bug in kilt-graphics, file a [GitHub issue](https://github.com/mac-comp127/kilt-graphics/issues).


## Library development

See [CONTRIBUTING.md](CONTRIBUTING.md).
