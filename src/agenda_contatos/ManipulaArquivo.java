package agenda_contatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipulaArquivo {
	Scanner sc = new Scanner(System.in);
	File novoArquivo;
	
	public File selecionarArquivo() {
		File arquivo = new File("D:/Documentos/Teste/agenda.txt");
		try {
			//verifica se o arquivo ou diretório existe
			if(!arquivo.exists()) {
				//cria um arquivo (vazio)
				arquivo.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arquivo;
	}
	
	public void cadastrarNovoContato(Contato contato) {

		String arquivo = contato.getNome() + ";" + contato.getTelefone() + ";" + contato.getEmail();
		// construtor que recebe também como argumento se o conteúdo será acrescentado
		// ao invés de ser substituído (append)
		try {
			FileWriter fw = new FileWriter(selecionarArquivo(), true);
			// construtor recebe como argumento o objeto do tipo FileWriter
			BufferedWriter bw = new BufferedWriter(fw);
			// escreve o conteúdo no arquivo
			bw.write(arquivo);

			// quebra de linha
			bw.newLine();
			// fecha os recursos
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void lerContatos() {
		String linha = null;
		try {
			//construtor que recebe o objeto do tipo arquivo
			FileReader fr = new FileReader( selecionarArquivo() );
			//construtor que recebe o objeto do tipo FileReader
			BufferedReader br = new BufferedReader( fr );
			//equanto houver mais linhas
			while ((linha = br.readLine()) != null) {
               
				System.out.println(linha);				
                
            }
			 br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void alterarContatos() {
		ArrayList<Contato> lista= carregarArrayContatos();
		System.out.println("O que você deseja alterar?");
		System.out.println("1-Nome");
		System.out.println("2-Telefone");
		System.out.println("3-Email");
		int i = 0;
		int op = sc.nextInt();
		switch (op) {
		case 1:
			
			System.out.println("Qual o nome anterior?");
			String nomeAntigo = sc.next();
			System.out.println("Qual o novo nome?");
			String novoNome = sc.next();
			while(i!=lista.size()) {
				if(lista.get(i).getNome().equals(nomeAntigo)) {
					
					lista.get(i).setNome(novoNome);			
					reescreverArquivo(lista);
					break;
				}
				i++;
			}
			
			break;
			
		case 2:
			System.out.println("Qual o telefone anterior?");
			String telAntigo = sc.next();
			System.out.println("Qual o novo telefone?");
			String novoTel = sc.next();
			while(i!=lista.size()) {
				if(lista.get(i).getTelefone().equals(telAntigo)) {
					lista.get(i).setTelefone(novoTel);			
					reescreverArquivo(lista);
					break;
				}
				i++;
			}
			break;
			
		case 3: 
			System.out.println("Qual o email anterior?");
			String emailAntigo = sc.next();
			System.out.println("Qual o novo email?");
			String novoEmail = sc.next();
			while(i!=lista.size()) {
				if(lista.get(i).getEmail().equals(emailAntigo)) {
					lista.get(i).setEmail(novoEmail);			
					reescreverArquivo(lista);
					break;
				}
				i++;
			}
			break;
		default:
			break;
		}
	}
	
	public ArrayList<Contato> carregarArrayContatos() {
		String linha = null;
		ArrayList<Contato> listaContatos = new ArrayList<>();
		try {
			//construtor que recebe o objeto do tipo arquivo
			FileReader fr = new FileReader( selecionarArquivo() );
			//construtor que recebe o objeto do tipo FileReader
			BufferedReader br = new BufferedReader( fr );
			//equanto houver mais linhas
			Contato contato = new Contato();
			while ((linha = br.readLine()) != null) {
               
				String[] vetorContato = linha.split(";");
                //Contato e = new Contato(vetorContato[0],vetorContato[1], vetorContato[2]);
                contato.setNome(vetorContato[0]);
                contato.setTelefone(vetorContato[1]);
				contato.setEmail(vetorContato[2]);
				
				listaContatos.add(contato);       
            }
			 br.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaContatos;
	}
	
	public void reescreverArquivo(ArrayList<Contato> listaContato) {
		File arquivo = selecionarArquivo();
		arquivo.delete();
		String arq;
		try {
		FileWriter fw = new FileWriter(arquivo);
		BufferedWriter bw = new BufferedWriter(fw);
		int i=0;
		while(i<=listaContato.size()-1) {
			arq = listaContato.get(i).getNome() + ";" 
					+ listaContato.get(i).getTelefone() 
					+ ";" + listaContato.get(i).getEmail();
			bw.write(arq);
			bw.newLine();
			i++;
		}
		bw.close();
		fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public void excluirContato(String nome) {
		ArrayList<Contato> lista= carregarArrayContatos();
		
		int i = 0;
	
			while(i!=lista.size()) {
				if(lista.get(i).getNome().equals(nome)) {
					lista.remove(i);			
					reescreverArquivo(lista);
					break;
				}
				i++;
			}
	}
		
	}
	
