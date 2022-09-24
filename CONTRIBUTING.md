# Development

## Regression tests

This project includes a regression test suite. The tests don't cover any of the animation, event handling, or other interactive and time-based behaviors, but do cover graphics and basic `CanvasWindow` behavior fairly comprehensively.

To run the tests from the command line:

    ./gradlew test

You can also choose the “Run all tests” option in VS Code or IntelliJ.

### Rendering tests

The test suite includes a custom test harness for graphical output, which you will see as the `@RenderingTest` annotation in tests. It works by comparing a test’s `GraphicsObject` output to a reference image:

- Each test looks for a file named `test/imageComparisons/<TestSuiteName>/<testname>-<variant>.expected.png`.
- The test produces a corresponding `.actual.png` file, and compares it with the expected image.
- If the two differ at all, the test produces a `.(delta).png` image showing the differing pixels.
- A rendering test fails if the sum of squares difference between the expected and actual images exceeds a certain threshold, which individual tests may override. This is necessary because graphics render slightly differently on different platforms.
- Only the expected images get committed; the test directory’s `.gitignore` ignores the actual and delta images.

#### Adding and updating

If an expected image file does not exist, the test **assumes the current output is correct** and creates a new `.expected.png` file.

If you add a new test, you thus should run the tests once to create the expected image file, then run them again to make sure the output is consistent.

If you change the library or tests so that an existing expected image is wrong, delete the `.expected.png` file and then re-run the tests to create a new one.

### Continuous Integration

If you create a pull request against `main` or commit directly to it, GitHub actions will run the tests on VMs with multiple OSes. Thus an advantage of working by PR is that you can check your work on both macOS and Windows before merging.

If you get a CI test failure:

- Click your commit on [the project’s GitHub actions page](https://github.com/mac-comp127/kilt-graphics/actions).
- Find the failing build (e.g. macos-latest) in the list of builds on the left.
- GitHub actions don’t show Gradle output properly in the web UI. Click the three dots in the upper right of the log area, and choose **View raw logs** to find the test failure.
- If an image comparison failed, you can download the image files from the **Artifacts** drop-down.
- It is the curse of all CI systems that test runs sometimes generate spurious failures. If the logs show the tests were cancelled for no apparent reason, or if you download the image comparisons and an image is mysteriously blurry, try re-running the CI job.


## Release process

1. Determine a new version number. This project uses [semantic versioning](https://semver.org).

    Throughout this section, replace `🦄.🐉.🦋` with your new version number.
1. Ensure that CI is passing on GitHub for the most recent commit on the main branch. (Look for the green check mark next to the latest commit near the top of the [project’s GitHub  page](https://github.com/mac-comp127/kilt-graphics).)
1. Ensure that you are on the main branch locally, and don’t have uncommitted changes:

        git checkout main
        git status

1. Change the line near the end of the README that references the current version number:

        implementation group: 'com.github.mac-comp127', name: 'kilt-graphics', version: '🦄.🐉.🦋'  // ← update this

1. Prepare the docs:

        ./gradlew clean test assemble -i -Pversion=🦄.🐉.🦋

1. This will alter the contents of the `docs` directory. Create a new commit with those changes (which will update the [public javadocs](https://mac-comp127.github.io/kilt-graphics/)), and tag that commit with your release number:

        git add docs README.md
        git commit -m 'Version 🦄.🐉.🦋'
        git tag 🦄.🐉.🦋

1. Everything look good locally? Push your new tag:

        git push && git push --tags

1. Wait for the new release’s [CI job](https://github.com/mac-comp127/kilt-graphics/actions) to finish (~4 min), and double-check that it succeeded.
1. Look for your new release [in JitPack](https://jitpack.io/#mac-comp127/kilt-graphics/), and wait for it to build. (Look for the status icon in the “Log” column.) Make sure the build finishes successfully. Note: JitPack **will not rebuild** an existing version number under any circumstances. If something goes wrong with the JitPack build, you will need to cut a release with a new patch number.
1. Select your new release on the [project’s tags page](https://github.com/mac-comp127/kilt-graphics/tags) and add release notes by clicking the larger three-dot icon in the far right of your tag’s row (not the smaller three-dot icon right next to the release (mystery meat UI = 😠)) and selecting **Create release**.
1. Specify your new version number in a client project’s dependencies — either your own project or the [127 welcome app](https://github.com/mac-comp127/welcome-app/) — and try your new changes!
