import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TabelaEntradaNeuronio {
	
	private List<BigDecimal[]> entradas;
	private int[] saidasEsperadas;
	
	public TabelaEntradaNeuronio() {
		this.entradas = new ArrayList<BigDecimal[]>();
	}
	
	/**
	 * @see - Adiciona uma linha na tabela de aprendizagem do neur�nio.
	 * @see - Entrada de tamanho vari�vel.
	 * @see ex: 
	 * @see		* addEntrada(0,1)
	 * @see		* addEntrada(0,1,1)
	 * @see		* addEntrada(0,1,1,0) 
	 * @param double[]
	 */
	public void addEntrada(double... entradas){
		BigDecimal[] decimals = new BigDecimal[entradas.length];
		int i = 0;
		for(double value : entradas){
			BigDecimal decimal = BigDecimal.valueOf(value);
			decimals[i++] = decimal;
		}
		getEntradas().add(decimals);
	}
	
	/**
	 * @see - Define a sa�da esperada logo ap�s uma linha de teste no neur�nio.
	 * @param int {0,1}
	 */
	public void setSaidaEsperada(int saidaEsperada){
		if(this.saidasEsperadas != null){
			int[] backUpValores = this.saidasEsperadas;
			this.saidasEsperadas = new int[this.saidasEsperadas.length + 1];
			
			int i;
			for(i=0;i<backUpValores.length;i++){
				this.saidasEsperadas[i] = backUpValores[i];
			}
			this.saidasEsperadas[i] = saidaEsperada;
		}else{
			this.saidasEsperadas = new int[1];
			this.saidasEsperadas[0] = saidaEsperada;
		}
	}
	
	/**
	 * get's and set's
	 * @return
	 */
	
	public List<BigDecimal[]> getEntradas() {
		return entradas;
	}
	
	public void setEntradas(List<BigDecimal[]> entradas) {
		this.entradas = entradas;
	}

	public int[] getSaidasEsperadas() {
		return saidasEsperadas;
	}

}