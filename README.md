# MarsRovers
Fa�a o download do reposit�rio

Na raiz do projeto, crie um diret�rio onde ficar� os arquivos .class gerados (chamarei este diret�rio de bin)

Agora, usando o prompt de comando, execute o seguinte comando:
	
	javac -d bin -sourcepath src src/com/luiz/main/Main.java
	javac -d bin -sourcepath src -cp lib/hamcrest-core-1.3.jar;lib/junit-4.12.jar src/com/luiz/test/AllTests.java

Ap�s compilar, mova o arquivo "data" que se encontra na raiz do projeto para o diret�rio /bin/com/luiz/main
Para executar o programa, digite o seguinte comando:

	java -cp bin com.luiz.main.Main
	
Para rodar os testes usando JUnit, execute o seguinte comando:
	
	java -cp bin;lib/hamcrest-core-1.3.jar;lib/junit-4.12.jar org.junit.runner.JUnitCore com.luiz.test.AllTests