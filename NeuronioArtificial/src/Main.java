import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
	
	public static void main(String args[]){		
		
		//Cria a tabela de aprendizagem instanciando a classe TabelaEntradaNeuronio, definindo as entradas e saídas esperadas
		TabelaEntradaNeuronio tabelaEntradaNeuronio = new TabelaEntradaNeuronio();
		
		tabelaEntradaNeuronio.addEntrada(0,1,1,0);
		tabelaEntradaNeuronio.setSaidaEsperada(0);
		
		tabelaEntradaNeuronio.addEntrada(0,1,1,1);
		tabelaEntradaNeuronio.setSaidaEsperada(1);
		
		tabelaEntradaNeuronio.addEntrada(1,0,0,0);
		tabelaEntradaNeuronio.setSaidaEsperada(0);
		
		tabelaEntradaNeuronio.addEntrada(1,0,1,1);
		tabelaEntradaNeuronio.setSaidaEsperada(1);
		
		tabelaEntradaNeuronio.addEntrada(1,1,0,0);
		tabelaEntradaNeuronio.setSaidaEsperada(1);
		
		//Instancia da classe Neuronio, defindo os valores de treinamento
		Neuronio neuronio = new Neuronio();
		neuronio.setTaxaAprendizagem(.01); //Define a taxa de aprendizagem
		neuronio.setTeta(.1); //Define o valor de teta
		neuronio.setEntradas(4); //Define a quantidade de entradas no neurônio
		neuronio.setTabelaNeuronio(tabelaEntradaNeuronio); //Define a tabela de treinamento
		neuronio.setPesosIniciais(2.1,34.0,2.1,0.98); //Define os pesos iniciais
		
		Calendar inicioDataHora = Calendar.getInstance();
		
		int i;
		int ciclos = 1000000; //Quantidade máximas de cliclos a serem executados 
		for(i=0;i<ciclos;i++){
			neuronio.ciclo();
			if(neuronio.isConvergiu())break;
		}
		
		Calendar fimDataHora = Calendar.getInstance();
		
		if(neuronio.isConvergiu()){
			System.out.println("Neurônio convergiu no " + ++i + "º ciclo.");
			System.out.println("Pesos finais -> ");
			for(BigDecimal peso : neuronio.getPesos()){
				System.out.printf("[%.10f]", peso);
			}
		}else{
			System.out.println("Neurônio não convergiu.");
		}
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS");
		System.out.println("\nDe.: " + format.format(inicioDataHora.getTime()) + " Ás.: " + format.format(fimDataHora.getTime()));
		
	}

}