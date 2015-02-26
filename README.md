# semantic-ui

[Semantic UI][2] [Hoplon][1] dependency.

## Usage

This is how you should refer it as a `boot` dependency:

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

and that's it; just use the styles and their javascript functions.

## Test & Release

```
boot2 package5 build-jar install
boot2 package5 build-jar push-snapshot
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
