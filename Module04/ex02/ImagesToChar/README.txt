javac -d ./target src/java/fr/leet/printer/app/*.java src/java/fr/leet/printer/logic/*.java -cp lib/jcommander-1.82.jar


cp -R src/resources target/resources
jar cmvf src/manifest.txt target/images-to-chars-printer.jar  -C ./target . 


## The m option indicates that you want to merge information from an existing file into the manifest file of the JAR
## c indicate that you want to create a jar file 
## The f option indicates that you want the output to go to a file



## how to run it 
java -cp target/images-to-chars-printer.jar:lib/jcommander-1.82.jar  fr.leet.printer.app.Main  --white=RED --black=GREEN