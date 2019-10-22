package agenda_contatos;

import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		ManipulaArquivo manArquivo = new ManipulaArquivo();
		Scanner entrada = new Scanner(System.in);
		int op = 0;
		
		while(op!=5) {
		System.out.println("******************************");	
		System.out.println("Agenda de Contatos");
		System.out.println("1 - Cadastrar um novo contato");
		System.out.println("2 - Listar contatos");
		System.out.println("3 - Alterar um contato");
		System.out.println("4 - Excluir um contato");
		System.out.println("5 - Sair");
		System.out.println("*******************************");
		op = entrada.nextInt();
		switch (op) {
		case 1:
			Contato e = new Contato();
			System.out.println("Qual o nome do contato?");
			e.setNome(entrada.next());
			System.out.println("Qual o telefone do contato?");
			e.setTelefone(entrada.next());
			System.out.println("Qual o email do contato?");
			e.setEmail(entrada.next());
			manArquivo.cadastrarNovoContato(e);
			break;
		case 2:
			manArquivo.lerContatos();
			System.out.println("");
			break;
		case 3:
			manArquivo.alterarContatos();
			break;
		case 4:
			System.out.println("Qual o nome do contato deseja excluir?");
			String nome = entrada.next();
			manArquivo.excluirContato(nome);
			break;
		case 5:
			System.out.println("Agenda Encerrada!!!");
			break;
		default:
			break;
		}
		}
	}
}
