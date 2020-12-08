package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDao livroDao;

	public List<Venda> getVendas(long seed) {

	       List<Livro> livros = livroDao.listaTodos();
	       List<Venda> vendas = new ArrayList<Venda>();

	       Random random = new Random(seed);

	       for (Livro livro : livros) {
	           Integer quantidade = random.nextInt(1000);
	           vendas.add(new Venda(livro, quantidade));
	       }

	       return vendas;
	   }
	 
	 public BarChartModel getVendasModel() {
		
		 BarChartModel model = new BarChartModel();
		 
		 ChartSeries vendasSerie1 = new ChartSeries();
		 vendasSerie1.setLabel("Vendas de 2019");
		 
		 List<Venda> vendas = getVendas(1234);
		 
		 for (Venda venda : vendas) {
			vendasSerie1.set(venda.getLivro().getId(), venda.getQuantidade());
		}
		 
		 
		 model.addSeries(vendasSerie1);
		 
		 ChartSeries vendasSerie2 = new ChartSeries();
		 vendasSerie2.setLabel("Vendas 2020");
		 
		 vendas = getVendas(4321);
		 
		 for (Venda venda : vendas) {
			vendasSerie2.set(venda.getLivro(), venda.getQuantidade());
		}
		 
		 model.addSeries(vendasSerie2);
		 
		// pegando o eixo X do gráfico e setando o título do mesmo
		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setLabel("Id do livro");

		// pegando o eixo Y do gráfico e setando o título do mesmo
	    Axis yAxis = model.getAxis(AxisType.Y);
	    yAxis.setLabel("Quantidade");
	    
	    model.setAnimate(true);
	    model.setStacked(true);
		 
		 return model;
	 }
}