echo "STARTING JFLEX COMPILING"
java -jar ../jflex-full-1.8.2.jar lexerImg.flex
echo "STARTING CUP COMPILING"
java -jar ../java-cup-11b.jar -parser ParserImg -symbols symImg parserImg.cup
echo "PRESIONE ENTER PARA SALIR"
 read -p "$*"