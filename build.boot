(set-env!
  :resource-paths #{"resources"}
  :dependencies '[
                  [adzerk/bootlaces "0.1.9" :scope "test"]
                  [cljsjs/boot-cljsjs "0.4.6" :scope "test"]
                  [cljsjs/jquery "1.8.2-2"]])

(require
  '[adzerk.bootlaces :refer :all]
  '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +ver+ "2.0.6")
(def +version+ (str +ver+ "-SNAPSHOT"))
(bootlaces! +version+)

(task-options!
  pom { :project 'exicon/semantic-ui
       :version +version+
       :description (str "Semantic is a UI component framework"
                         " based around useful principles from natural language.")
       :url "http://semantic-ui.com"
       :license {"MIT" "http://opensource.org/licenses/MIT"}
       :scm {:url "https://github.com/exicon/hoplon-semantic-ui"}})

(deftask download-semantic-ui []
  (download
    :url (str "https://github.com/Semantic-Org/Semantic-UI/archive/" +ver+ ".zip")
    :unzip true))

(deftask download-tablesort []
  (download
    :url (str "http://semantic-ui.com/javascript/library/tablesort.js")))

(deftask download-tablesort-min []
  (download
    :url (str "http://semantic-ui.com/javascript/library/tablesort.min.js")))

(deftask package5 []
  (task-options! pom { :project 'exicon/hoplon5-semantic-ui })
  (comp
    (download-semantic-ui)
    (sift :move
          {
           #"^Semantic-UI-.*/dist/semantic.css" "semantic-ui.inc.css"
           #"^Semantic-UI-.*/dist/semantic.js" "semantic-ui.inc.js"
           #"^Semantic-UI-.*/dist/themes/" "_hoplon/themes/"
           })
    (sift :include #{#"_hoplon/" #"semantic-ui"})))

(deftask package []
  (comp
    (download-semantic-ui)
    (download-tablesort)
    (download-tablesort-min)
    (sift :move
          {
           #"^Semantic-UI-.*/dist/semantic.css" "cljsjs/development/semantic-ui.inc.css"
           #"^Semantic-UI-.*/dist/semantic.js" "cljsjs/development/semantic-ui.inc.js"
           #"^Semantic-UI-.*/dist/semantic.min.css" "cljsjs/production/semantic-ui.min.inc.css"
           #"^Semantic-UI-.*/dist/semantic.min.js" "cljsjs/production/semantic-ui.min.inc.js"
           #"^Semantic-UI-.*/dist/themes/" "cljsjs/common/themes/"
           #"semantic-ui.ext.js" "cljsjs/common/semantic-ui.ext.js"
           #"tablesort.js" "cljsjs/development/tablesort.inc.js"
           #"tablesort.min.js" "cljsjs/production/tablesort.min.inc.js"
           })
    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "exicon.semantic-ui"
               :requires ["cljsjs.jquery"])))
