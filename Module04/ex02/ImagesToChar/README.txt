javac -d ./target src/java/fr/leet/printer/app/*.java src/java/fr/leet/printer/logic/*.java

jar cmvf src/manifest.txt target/images-to-chars-printer.jar  -C ./target . -C ./src  resources

## The m option indicates that you want to merge information from an existing file into the manifest file of the JAR
## c indicate that you want to create a jar file 
## The f option indicates that you want the output to go to a file