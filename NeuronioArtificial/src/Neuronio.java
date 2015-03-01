import java.math.BigDecimal;

public class Neuronio {
	
	private BigDecimal taxaAprendizagem;
	private BigDecimal teta;
	private TabelaEntradaNeuronio tabelaNeuronio;
	private int entradas;
	
	private BigDecimal[] pesos;
	
	private boolean convergiu;
	
	public Neuronio() {}

	public void ciclo(){
		setConvergiu(true);
		for (int i=0;i<getTabelaNeuronio().getEntradas().size();i++){
			int saidaObtida = getSaidaNeuronio(getTabelaNeuronio().getEntradas().get(i), this.pesos);
			int saidaEsperada = getTabelaNeuronio().getSaidasEsperadas()[i]; 
			if(saidaObtida != saidaEsperada){
				this.pesos = ajustaPesos(this.pesos, getTabelaNeuronio().getEntradas().get(i), saidaEsperada, saidaObtida);
				setConvergiu(false);
			}
		}
	}
	
	private BigDecimal[] ajustaPesos(BigDecimal[] pesosAnteriores, BigDecimal[] entradas, int saidaEsperada, int saidaObtida){
		int erro = saidaEsperada - saidaObtida;
		
		BigDecimal[] deltaOmega = new BigDecimal[entradas.length];
		int i = 0;
		for(BigDecimal entrada : entradas){
			deltaOmega[i++] = getTaxaAprendizagem().multiply(entrada).multiply(new BigDecimal(erro));
		}
		
		i = 0;
		BigDecimal[] pesosNovos = new BigDecimal[pesosAnteriores.length];
		for(BigDecimal pesoAnterior : pesosAnteriores){
			pesosNovos[i] = pesoAnterior.add(deltaOmega[i]);
			i++;
		}
		
		return pesosNovos;
	}
	
	private int getSaidaNeuronio(BigDecimal[] entradas, BigDecimal[] pesos){
		BigDecimal somaNeuronio = new BigDecimal(0);
		for(int i=0;i<entradas.length;i++){
			somaNeuronio = somaNeuronio.add(entradas[i].multiply(pesos[i]));
		}
		somaNeuronio = somaNeuronio.subtract(getTeta());
		return somaNeuronio.doubleValue() > 0 ? 1 : 0;
	}
	
	/**
	 * @see - Define os pesos iniciais para iniciar o treinamento do neurônio.
	 * @see - Entrada de tamanho variável.
	 * @see ex: 
	 * @see		* setPesosIniciais(.3,1)
	 * @see		* setPesosIniciais(0,1,1.4)
	 * @see		* setPesosIniciais(.56,.1,.11,.03) 
	 * @param double[]
	 * @throws Exception 
	 */
	public void setPesosIniciais(double... pesos){
		int i = 0;
		for(double value : pesos){
			BigDecimal decimal = BigDecimal.valueOf(value);
			this.pesos[i++] = decimal;
		}
	}
	
	/**
	 * get's and set's
	 * @return
	 */
	
	public BigDecimal getTaxaAprendizagem() {
		return taxaAprendizagem;
	}
	
	public void setTaxaAprendizagem(double taxaAprendizagem) {
		this.taxaAprendizagem = BigDecimal.valueOf(taxaAprendizagem);
	}
	
	public BigDecimal getTeta() {
		return teta;
	}
	
	public void setTeta(double teta) {
		this.teta = BigDecimal.valueOf(teta);
	}
	
	public TabelaEntradaNeuronio getTabelaNeuronio() {
		return tabelaNeuronio;
	}
	
	public void setTabelaNeuronio(TabelaEntradaNeuronio tabelaNeuronio) {
		this.tabelaNeuronio = tabelaNeuronio;
	}

	public boolean isConvergiu() {
		return convergiu;
	}

	private void setConvergiu(boolean convergiu){
		this.convergiu = convergiu;
	}

	public int getEntradas() {
		return entradas;
	}

	public void setEntradas(int entradas) {
		this.entradas = entradas;
		this.pesos = new BigDecimal[this.entradas];
	}

	public BigDecimal[] getPesos() {
		return pesos;
	}
}