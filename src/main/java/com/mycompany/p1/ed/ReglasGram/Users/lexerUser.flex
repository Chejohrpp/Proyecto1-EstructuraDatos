/*codigo de usuario*/
package com.mycompany.p1.ed.ReglasGram.Users;
import com.mycompany.p1.ed.ReglasGram.Token;
import java_cup.runtime.*;
import static com.mycompany.p1.ed.ReglasGram.Users.symUser.*;
import java.util.ArrayList;
import java.util.List;

%%
/*configuracion*/
%class LexerUser
%public
%unicode
%line
%column
%cup
decimal=[1-9][0-9]*[.]{entero}| [0][.]{entero}
entero=[0-9]+
number = ({entero}|{decimal})
letra = [a-zA-Z]
string = ({letra}|{number}|[_]|[-]|[\#])+
lineTerminator = \r|\n|\r\n
whiteSpace     = {lineTerminator} | [ \t\f]

%{
     List<String> errores = new ArrayList<String>();

     private void estructuraError(String charError, int linea, int columna){
     	String elError = "Error: No se reconoce el caracter: " + charError + " -> {Linea: "+ linea +", Columna: "+ columna +" }" ;
     	errores.add(elError);
     }
     public List getErroresLexicos(){
     	return errores;
     }
%}

%eofval{
  return new java_cup.runtime.Symbol(symUser.EOF);
%eofval}


%%
/*reglas*/	

/*palabras clave*/

<YYINITIAL>{
	
	/*cadenas*/
	{number}							{return new Symbol(NUMERO,yyline+1,yycolumn+1,new Token(yytext(),yyline+1,yycolumn+1 ));}
	{string}							{return new Symbol(STRING,yyline+1,yycolumn+1,new Token(yytext(),yyline+1,yycolumn+1 ));}
	

	/*SIMBOLOS*/
	":"									{return new Symbol(DOS_PUNTOS,yyline+1,yycolumn+1,new Token(yytext(),yyline+1,yycolumn+1 ));}
	";"									{return new Symbol(SEMI,yyline+1,yycolumn+1,new Token(yytext(),yyline+1,yycolumn+1 ));}
	","									{return new Symbol(COMA,yyline+1,yycolumn+1,new Token(yytext(),yyline+1,yycolumn+1 ));}

	/* espacios en blanco */
	{whiteSpace}                   		{ /* ignorar */ }
}

[^]										{estructuraError(yytext(),yyline+1,yycolumn+1);}