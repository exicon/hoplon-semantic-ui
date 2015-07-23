# semantic-ui

[Semantic UI][2] [Hoplon][1] dependency.

## Usage

This is how you should refer it as a [boot](http://boot-clj.com/) dependency:

using Hoplon5
```clojure
(set-env!
	:project 'hoplon-semantic-test
	:version "0.1.0-SNAPSHOT"
	:dependencies '[
		[tailrecursion/boot.task "2.2.4"]
		[tailrecursion/hoplon "5.10.24"]
		[exicon/hoplon5-semantic-ui "1.10.2-SNAPSHOT"]]
	:out-path "resources/public"
	:src-paths #{"src"})
```

using Hoplon6
```clojure
(set-env!
  :project 'hoplon-semantic-test
  :version "0.1.0-SNAPSHOT"
  :dependencies '[
    [tailrecursion/boot-hoplon "0.1.0-SNAPSHOT"]
    [tailrecursion/hoplon "6.0.0-SNAPSHOT"]
    [tailrecursion/javelin "3.6.3"]
    [adzerk/boot-cljs "0.0-3269-2"]
    [cljsjs/boot-cljsjs "0.4.8" :scope "test"]
    [exicon/semantic-ui "2.0.6-SNAPSHOT"]]
  :exclusions '[[com.keminglabs/cljx]]
  :source-paths #{"src"}
  :resource-paths #{"assets"})
```

and that's it; just use the styles and their javascript functions.

## Test & Release

```
boot package build-jar install          # for Hoplon6 (local repo)
boot package build-jar push-snapshot    # for Hoplon6

boot package5 build-jar install
boot package5 build-jar push-snapshot
```

There is a `package` task too for hoplon6 but it doesn't work at the moment.

## How to update

```bash
grep '^\$.fn.' Semantic-UI-*/dist/semantic.js | \
  awk -F ' = ' 'BEGIN {print ";"}
    { sub("^.", "jQuery", $1) }
    $2 ~ /func/ {print $1 " = function () {};"}
    $2 == "{" {print $1 " = {};"}
  ' > resources/semantic-ui.ext.js
```

## License

MIT

[1]: http://hoplon.io
[2]: http://semantic-ui.com
