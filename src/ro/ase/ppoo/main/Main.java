package ro.ase.ppoo.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ro.ase.ppoo.exceptions.PINCodeException;
import ro.ase.ppoo.exceptions.SumaIncorectaException;
import ro.ase.ppoo.exceptions.TastaInvalidaException;
import ro.ase.ppoo.models.Account;
import ro.ase.ppoo.models.Card;
import ro.ase.ppoo.models.Client;
import ro.ase.ppoo.models.Transaction;
import ro.ase.ppoo.models.TransactionType;

public class Main {
	
	public static List<Client> lista=new ArrayList<Client>();
	
	public static void backOrExit() {
		System.out.println("Apasati 0 pentru a reveni la meniul principal");
		System.out.println("Apasati x pentru iesire");
		

		
	}
	
	public static void verificaCodPin(String codPin) {
		if(!onlyDigits(codPin, codPin.length()))
			 throw new PINCodeException("Codul PIN contine doar cifre!");
		 if(codPin.length()<4 || codPin.length()>4)
				throw new PINCodeException("Codul PIN este format din 4 cifre!");
		
	}
	public static Client schimbareCodPin(Client c) {
		System.out.println("linia 26 " +c);
		System.out.println("Te rugam sa intoduci noul cod PIN");
		try {
			Scanner scanner = new Scanner(System.in);
			String newPIN=scanner.nextLine();
				verificaCodPin(newPIN);

			for(Client cl : lista){
				if(cl.getCnp()==c.getCnp()) {
					cl.getCard().setPinCode(Integer.parseInt(newPIN));
				}
			}	
			
		}
		catch(PINCodeException ex) {
			System.out.println(ex.getMessage());
		}
		
		return c;
	}
	
	public static List<Client> getListaClienti() {
		
		List<Client> clientList=new ArrayList<Client>();
			clientList=	readBinaryFile();
		System.out.println(clientList);
		
		return clientList;
	}
	
	public static void interogareSold(Client c) {
		
		for (String key : c.getAccounts().keySet()) {
			System.out.println("Soldul curent pentru contul "+c.getAccounts().get(key).getIban()+ " este :"+c.getAccounts().get(key).getCurrentBalance());
		}
		
	}
	
	
	
	public static Client depunereNumerar(Client c) {
		
		
		System.out.println( "Selecteaza contul pentru care vrei sa efectuezi tranzactia apasand tasta corespunzatoare: ");
		for (String key : c.getAccounts().keySet()) {
			System.out.println(key+" > "+c.getAccounts().get(key).getIban());
		}
		try {
			Scanner scanner = new Scanner(System.in);
			String contSelectat=scanner.nextLine();
			if(!onlyDigits(contSelectat, contSelectat.length()))
				throw new TastaInvalidaException("Te rugam sa selectezi o cifra asociata unui cont!");
			if(Integer.parseInt(contSelectat) > c.getAccounts().size() || Integer.parseInt(contSelectat) <= 0)
				throw new TastaInvalidaException("Tasta apasata nu corespunde unui cont!");
			
			for (String key :  c.getAccounts().keySet()) {
				if(key.equals(contSelectat)) {
					System.out.println("Tasteaza suma pe care vrei sa o depui:");
					String sumaDeDepus=scanner.nextLine();
					if(!onlyDigits(sumaDeDepus, sumaDeDepus.length()))
						throw new SumaIncorectaException("Suma invalida!");
					int oldBalance=c.getAccounts().get(key).getCurrentBalance();
					if(Integer.parseInt(sumaDeDepus)<0) {
						throw new SumaIncorectaException("Suma trebuie sa fie mai mare decat 0!");
					}
					
					for(Client cl : lista){
						if(cl.getCnp()==c.getCnp()) {
							c.getAccounts().get(key).setCurrentBalance(oldBalance+Integer.parseInt(sumaDeDepus)); //????
						}
					}	
				Transaction transaction=new Transaction(c.getTransactions().size()+1, Integer.parseInt(sumaDeDepus), TransactionType.DEPOSIT);
				c.getTransactions().add(transaction);
					
					
				}
			}
		}
		catch(TastaInvalidaException ex) {
			System.out.println(ex.getMessage());
		}
		catch(SumaIncorectaException ex) {
			System.out.println(ex.getMessage());
		}
		
		//System.out.println(c);
		return c;
		
		
		
		
	}
	
	
	
	public static Client retragereNumerar(Client c) {
		
		
		System.out.println( "Selecteaza contul pentru care vrei sa efectuezi tranzactia apasand tasta corespunzatoare: ");
		//int counter=0;
		for (String key : c.getAccounts().keySet()) {
			//counter++;
			System.out.println(key+" > "+c.getAccounts().get(key).getIban());
		}
		try {
			Scanner scanner = new Scanner(System.in);
			String contSelectat=scanner.nextLine();
			if(!onlyDigits(contSelectat, contSelectat.length()))
				throw new TastaInvalidaException("Te rugam sa selectezi o cifra asociata unui cont!");
			System.out.println(c.getAccounts().size());
			if(Integer.parseInt(contSelectat) > c.getAccounts().size() || Integer.parseInt(contSelectat) <= 0)
				throw new TastaInvalidaException("Tasta apasata nu corespunde unui cont!");
			for (String key :  c.getAccounts().keySet()) {
				if(key.equals(contSelectat)) {
					System.out.println("Tasteaza suma pe care vrei sa o retragi");
					String sumaDeRetras=scanner.nextLine();
					if(!onlyDigits(sumaDeRetras, sumaDeRetras.length()))
						throw new SumaIncorectaException("Suma invalida!");
					int oldBalance=c.getAccounts().get(key).getCurrentBalance();
					if(Integer.parseInt(sumaDeRetras) >oldBalance)
						throw new SumaIncorectaException("Sold insuficient!");
					if(Integer.parseInt(sumaDeRetras)<0) {
						throw new SumaIncorectaException("Suma trebuie sa fie mai mare decat 0!");
					}
					for(Client cl : lista){
						if(cl.getCnp()==c.getCnp()) {
							c.getAccounts().get(key).setCurrentBalance(oldBalance-Integer.parseInt(sumaDeRetras)); //????
						}
					}	
				Transaction transaction=new Transaction(c.getTransactions().size()+1, Integer.parseInt(sumaDeRetras), TransactionType.WITHDROWAL);
				c.getTransactions().add(transaction);
					
					
				}
			}
		}
		catch(TastaInvalidaException ex) {
			System.out.println(ex.getMessage());
		}
		catch(SumaIncorectaException ex) {
			System.out.println(ex.getMessage());
		}
		
		//System.out.println(c);
		return c;
		
		
		
		
	}
	
	
	//SCRIERE IN FISIER TEXT
	public static void emitereExtras(Client c) {
		ObjectOutputStream os = null;
        try {
			os = new ObjectOutputStream(new FileOutputStream("Extras.txt"));
			for (Transaction t : c.getTransactions()) {
				System.out.println("TRANZACTIILE TALE:");
				System.out.println(t.getMoneyAmount()+"------>"+ t.getType());
				System.out.println("__________________________________________________");
				os.writeObject(t);
			}
             
            System.out.println("Extrasul tau a fost creat in fisierul Extras.txt");

		} catch (IOException e) {
			e.printStackTrace();
		}
        finally 
        {
             if (os != null)
                try 
             	{
                     os.close();
                }
                catch (IOException e) 
                { 
                	e.printStackTrace();
                }
        }

	}
	
	
	//SERIALIZARE BINAR
	public static void writeInBinaryFile(List<Client> clientsList1) {
		
		
		
		ObjectOutputStream os = null;
        try {
			os = new ObjectOutputStream(new FileOutputStream("clients.bin"));
            os.writeObject(clientsList1);

		} catch (IOException e) {
			e.printStackTrace();
		}
        finally 
        {
             if (os != null)
                try 
             	{
                     os.close();
                }
                catch (IOException e) 
                { 
                	e.printStackTrace();
                }
        }

	}
	
	//DESERIALIZARE BINAR
	public static List<Client> readBinaryFile() {
	     ObjectInputStream is = null;
	    // Client c=new Client();
	     List<Client> readClientsList=new ArrayList<Client>();
	        try {
	                is = new ObjectInputStream(new FileInputStream("clients.bin"));
	                readClientsList = (List<Client>)is.readObject();
	               // System.out.println("Deserialized: " + c);
	                return readClientsList;
	        }
	        catch (IOException e) 
	        {
	                e.printStackTrace();
	        }
	        catch (ClassNotFoundException e) 
	        {
	                e.printStackTrace();
	        }
	        finally 
	        {
	            if (is != null)
	                try 
	            	{
	                     is.close();
	                }
	                catch (IOException e) 
	                { 
	                	e.printStackTrace();
	                }
	           
	        }
	        return readClientsList;
	       
	       
	}
	
	public static void recognizeOption(char inputString, Client client) {
		switch (inputString) {
		case '0':
			printMenu(); 
			
			break;
		case '1':
			System.out.println("Vrei sa retragi bani din contul tau ");
			retragereNumerar(client);
			System.out.println("Apasati 0 pentru a reveni la meniul principal");
			System.out.println("Apasati x pentru iesire");
			break;
		case '2':
			System.out.println("Ai ales operatia interogare sold");
			interogareSold(client);
			System.out.println("Apasati 0 pentru a reveni la meniul principal");
			System.out.println("Apasati x pentru iesire");

			break;
		case '3':
			System.out.println("Ai ales operatia extras de cont");
			emitereExtras(client);
			System.out.println("Apasati 0 pentru a reveni la meniul principal");
			System.out.println("Apasati x pentru iesire");

			break;
		case '4':
			System.out.println("Ai ales operatia schimbare cod PIN");
			schimbareCodPin(client);
			
			System.out.println("Apasati 0 pentru a reveni la meniul principal");
			System.out.println("Apasati x pentru iesire");

			break;
		case '5':
			System.out.println("Ai ales operatia Depunere numerar");
			depunereNumerar(client);
			System.out.println("Apasati 0 pentru a reveni la meniul principal");
			System.out.println("Apasati x pentru iesire");

			break;
		case 'x':
			System.out.println(lista);
			writeInBinaryFile(lista);
			System.out.println("Multumim ca ai apelat la noi!");
			break;
	
		default:
			System.out.println("Comada gresita! ");
			
			System.out.println("Apasati 0 pentru a reveni la meniul principal");
			
			System.out.println("Apasati x pentru iesire");

			break;
		}
	}
	
	public static void printMenu() {
		
		 System.out.println("Alege operatia dorita si apasa tasta corespunzatoare");
		 
		 System.out.println("1 > Retragere numerar"); 
		 System.out.println("2 > Interogare sold"); 
		 System.out.println("3 > Solicitare extras de cont"); //NU SCRIE IN FISIER TEXT, CI IN BINAR , LOGICA ALEGERE CONT!!!
		 System.out.println("4 > Schimbare cod PIN"); 
		 System.out.println("5 > Depunere numerar"); 
		 System.out.println("x > Iesire din meniu");
	}
	
	 public static boolean
	    onlyDigits(String str, int n)
	    {
	        
	        for (int i = 0; i < n; i++) {
	 
	            
	            if (str.charAt(i) < '0'
	                || str.charAt(i) > '9') {
	                return false;
	            }
	        }
	         
	        return true;
	    }
	
	public static void main(String[] args) {
	
		System.out.println("Bine ai venit!");
		System.out.println("Please enter PIN code: ");
		try {
			Scanner scanner = new Scanner(System.in);
			String i=scanner.nextLine();
			
			 verificaCodPin(i);
			
			lista=getListaClienti();
			Client client=null;
			for (Client cl : lista) {
				if((cl.getCard().getPinCode()) == Integer.parseInt(i) )
					client=cl;
				else
					continue;
			}
			if(client!=null) {
				System.out.println("Bine ai venit "+ client.getFirstName()+ " "+client.getLastName()+"!");
				
				
				scanner = new Scanner(System.in);
				char inputString;
				
				printMenu();
				
				do {
					inputString = scanner.nextLine().charAt(0);
					recognizeOption(inputString, client);
					
				}
				while(inputString !='x');
			}
			else {
				throw new PINCodeException("Cod PIN gresit!");
			}
		}
		catch(PINCodeException ex) {
			System.out.println(ex.getMessage());
		}
		
		
		

	}
}
