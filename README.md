# semantic-ui

[Semantic UI][2] [Hoplon][1] dependency.

## Usage

This is how you should refer it as a `boot` dependency:

```clojure
(set-env!
	:project      'hoplon-semantic-test
	:version      "0.1.0-SNAPSHOT"
	:dependencies '[
		[tailrecursion/boot.task   "2.2.4"]
		[tailrecursion/hoplon      "5.10.24"]
		[link.exicon/semantic-ui "1.8.0-0"]]
	:out-path     "resources/public"
	:src-paths    #{"src"})
```

and that's it; just use the styles and their javascript functions.



## How to update

```bash
cp $SEMANTIC_UI_SRC/dist/semantic.css $HOPLON_SRC/vendor/semantic-ui/src/semantic-ui.inc.css
cp $SEMANTIC_UI_SRC/dist/semantic.js $HOPLON_SRC/vendor/semantic-ui/src/semantic-ui.inc.js
mkdir -p $HOPLON_SRC/vendor/semantic-ui/src/_hoplon/themes/default/
cp -R $SEMANTIC_UI_SRC/dist/themes/default/ $HOPLON_SRC/vendor/semantic-ui/src/_hoplon/themes/default/
grep '^\$.fn.' $SEMANTIC_UI_SRC/dist/semantic.js | \
	awk -F ' = ' 'BEGIN {print ";"}
		{ sub("^.", "jQuery", $1) }
		$2 ~ /func/    {print $1 " = function () {};"}
		$2 == "{"      {print $1 " = {};"}
	' > $HOPLON_SRC/vendor/semantic-ui/src/semantic-ui.ext.js
```

## License

MIT

[1]: http://hoplon.io
[2]: http://semantic-ui.com
