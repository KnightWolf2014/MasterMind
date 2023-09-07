javac --release 11 -cp . domini/*.java
javac --release 11 -cp . domini/controladors/*.java
javac --release 11 -cp . domini/model/*.java
javac --release 11 -cp ..\lib\junit-4.12.jar;. domini/tests/*.java
javac --release 11 -cp ..\lib\junit-4.12.jar;. domini/stubs/*.java
javac --release 11 -cp . persistencia/controladors/*.java
javac --release 11 -cp . persistencia/model/*.java
javac --release 11 -cp ..\lib\junit-4.12.jar;. persistencia/tests/*.java
javac --release 11 -cp . presentacio/controladors/*.java
javac --release 11 -cp . presentacio/utils/*.java
javac --release 11 -cp . presentacio/vistes/*.java

move domini\*.class ..\EXE\CLASS\domini
move domini\controladors\*.class ..\EXE\CLASS\domini\controladors
move domini\exceptions\*.class ..\EXE\CLASS\domini\exceptions
move domini\model\*.class ..\EXE\CLASS\domini\model
move domini\stubs\*.class ..\EXE\CLASS\domini\stubs
move domini\tests\*.class ..\EXE\CLASS\domini\tests

move persistencia\controladors\*.class ..\EXE\CLASS\persistencia\controladors
move persistencia\model\*.class ..\EXE\CLASS\persistencia\model
move persistencia\tests\*.class ..\EXE\CLASS\persistencia\tests

move presentacio\controladors\*.class ..\EXE\CLASS\presentacio\controladors
move presentacio\vistes\*.class ..\EXE\CLASS\presentacio\vistes
move presentacio\utils\*.class ..\EXE\CLASS\presentacio\utils