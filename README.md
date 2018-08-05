Build
-----

```
mvn clean packge
```

Start
-----
```
java -jar smartxmlanalyzer-1.0-SNAPSHOT-jar-with-dependencies.jar <origin_file> <modified_file> <element_id>
```

Results
-------
Modified file: sample-1-evil-gemini.html

Element ID: make-everything-ok-button
```
html > body[1] > div[0] > div[1] > div[2] > div[0] > div > div[1] > a[1]
Elements have the same name: Make everything OK
The same attribute class with value btn btn-success
The same attribute title with value Make-Button
The same attribute onclick with value javascript:window.okDone(); return false;
```

Modified file: sample-2-container-and-clone.html

Element ID: make-everything-ok-button
```
html > body[1] > div[0] > div[1] > div[2] > div[0] > div > div[1] > div > a
Elements have the same name: Make everything OK
The same attribute href with value #ok
The same attribute title with value Make-Button
The same attribute rel with value next
```

Modified file: sample-3-the-escape.html 

Element ID: make-everything-ok-button
```
html > body[1] > div[0] > div[1] > div[2] > div[0] > div > div[2] > a
The same attribute class with value btn btn-success
The same attribute href with value #ok
The same attribute rel with value next
The same attribute onclick with value javascript:window.okDone(); return false;
```

Modified file: sample-4-the-mash.html

Element ID: make-everything-ok-button
```
html > body[1] > div[0] > div[1] > div[2] > div[0] > div > div[2] > a
The same attribute class with value btn btn-success
The same attribute href with value #ok
The same attribute title with value Make-Button
The same attribute rel with value next
```
