(set-env!
	:resource-paths #{"resources"}
	:dependencies '[
		[adzerk/bootlaces "0.1.9" :scope "test"]
		[cljsjs/boot-cljsjs "0.4.6" :scope "test"]
		[cljsjs/jquery "1.8.2-2"]])

(require
	'[adzerk.bootlaces :refer :all]
	'[cljsjs.boot-cljsjs.packaging :refer :all])

(def +ver+ "1.10.2")
(def +version+ (str +ver+ "-SNAPSHOT"))
(bootlaces! +version+)

(task-options!
	pom { :project 'exicon/hoplon5-semantic-ui
		:version +version+
		:description "Semantic is a UI component framework based around useful principles from natural language."
		:url "http://semantic-ui.com"
		:license {"MIT" "http://opensource.org/licenses/MIT"}
		:scm {:url "https://github.com/exicon/hoplon-semantic-ui"}})

(deftask package []
	(comp
		(download
			:url (str "https://github.com/Semantic-Org/Semantic-UI/archive/" +ver+ ".zip")
			:unzip true)
		(sift :move {
			#"^Semantic-UI-.*/dist/semantic.css" "semantic-ui.inc.css"
			#"^Semantic-UI-.*/dist/semantic.js" "semantic-ui.inc.js"
			#"^Semantic-UI-.*/dist/themes/" "_hoplon/themes/"
			})
		(sift :include #{#"_hoplon/" #"semantic-ui"})))
