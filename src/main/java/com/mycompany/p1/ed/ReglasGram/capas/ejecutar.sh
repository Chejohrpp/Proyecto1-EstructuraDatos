echo "STARTING JFLEX COMPILING"
java -jar ../jflex-full-1.8.2.jar lexerCapas.flex
echo "STARTING CUP COMPILING"
java -jar ../java-cup-11b.jar -parser ParserCapas -symbols symCapas parserCapas.cup
echo "PRESIONE ENTER PARA SALIR"
 read -p "$*"