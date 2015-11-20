/* =========================== Rede Social +Pop ================================= #
 *                                                                                *
 * Projeto obrigatorio para cumprimento de nota da disciplina Programação II      * 	  
 * e Laboratorio de Programacao II.                                               *
 *                                                                                *
 * Departamento de Informatica e Engenharia Eletrica                              *
 * Curso Ciência da Computação (UFCG - 2015.1)                                    *
 * Laboratorio de Programação II                                                  *
 *                                                                                *
 * Discentes envolvidos:                                                          *
 *          Italo Batista                                                         *
 *          Jose Manoel Ferreira                                                  *
 *          Kerilin Chang.                                                        *
 *                                                                                *
 * Orientador:                                                                    *
 *          Francisco Neto.                                                       *
 *                                                                                *
 * ============================================================================== #
 */

/**
 * Classe destinada a execucao dos teste de aceitacao.
 */
package execucao.easy;

import easyaccept.EasyAccept;

/**
 * Classe <code>Main</code> destinada ao execucao do testes de aceitacao atraves da ferramenta EasyAccept.
 * @author josemsf
 *
 */
public class Main {
	
	public static void main(String[] args) {
		args = new String[] {"logica.Facade", "lib/ScriptsTeste/usecase_1.txt", "lib/ScriptsTeste/usecase_2.txt", "lib/ScriptsTeste/usecase_3.txt",
	             "lib/ScriptsTeste/usecase_4.txt", "lib/ScriptsTeste/usecase_5.txt","lib/ScriptsTeste/usecase_6.txt", "lib/ScriptsTeste/usecase_7.txt", "lib/NossosTestes/usecase_8.txt"};
		EasyAccept.main(args);
	}

}
