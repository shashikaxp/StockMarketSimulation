/**
 * 
 */
package Stockmarket.api.impl;

import Stockmarket.domain.FixedDividendStock;
import Stockmarket.domain.Stock;
import Stockmarket.exception.InvalidStockException;
import Stockmarket.service.IStockDataService;
import Stockmarket.service.Logger;

/**
 * @author Shivakumar Ramannavar
 *
 */
public class PreferredStockAPI extends AbstractStockAPI {

	/**
	 * @param stockDataService
	 */
	public PreferredStockAPI(IStockDataService stockDataService) {
		super(stockDataService);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.app.stockmarket.api.impl.AbstractStockAPI#calculateDividendYield(java
	 * .lang.String, double)
	 */
	@Override
	public double calculateDividendYield(String stockSymbol, double price) throws InvalidStockException {

		FixedDividendStock stock = (FixedDividendStock) stockDataService.getStockData(stockSymbol);;

		double dividendYield = 0.0;
		
		Logger.logDebugMessage("Fixed DividendYield  = Fixed Dividend  * Par Value / Price");
		Logger.logDebugMessage("                     = " + stock.getFixedDividendPercentage() + " %  * " + stock.getParValue() + " / " + price);
		Logger.logDebugMessage("                     = " + stock.getFixedDividendPercentage() / 100.0 +  " * " + stock.getParValue() + "/" + price);
		double fixedDividendParValue 				 = (stock.getFixedDividendPercentage() / 100.0) * stock.getParValue();
		Logger.logDebugMessage("                     = " + fixedDividendParValue + " / " + price);

		if (price != 0.0) {
			dividendYield = fixedDividendParValue / price;
		}

		Logger.logDebugMessage("                     = " + dividendYield + "\n");
		return dividendYield;
	}

}
