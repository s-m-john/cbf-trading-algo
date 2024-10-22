package codingblackfemales.gettingstarted;

import codingblackfemales.action.Action;
import codingblackfemales.action.NoAction;
import codingblackfemales.algo.AlgoLogic;
import codingblackfemales.sotw.SimpleAlgoState;
import codingblackfemales.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAlgoLogic implements AlgoLogic {

    private static final Logger logger = LoggerFactory.getLogger(MyAlgoLogic.class);

    @Override
    public Action evaluate(SimpleAlgoState state) {

        var orderBookAsString = Util.orderBookToString(state);

        logger.info("[MYALGO] The state of the order book is:\n" + orderBookAsString);

        /********
         *
         Explanation of the Code
         Logging: The code logs the state of the order book for transparency and debugging.

         Accessing Market Data:

         Retrieves the best bid price, best ask price, and current price from the SimpleAlgoState object.
         Buy and Sell Logic:

         Buy Order: It places a buy order if the current price is less than 98% of the best ask price.
         Sell Order: It places a sell order if the current price is more than 102% of the best bid price.
         Action Returns: The algorithm returns specific actions (buy or sell) based on the conditions met.
         *
         ********/


        // Access market data
        double bestBidPrice = state.getBestBidPrice();
        double bestAskPrice = state.getBestAskPrice();
        double currentPrice = state.getCurrentPrice();
        int quantity = 100;

        // Define threshold for buying and selling
        double buyThreshold = bestAskPrice * 0.98;
        double sellThreshold = bestBidPrice * 1.02;

        // Implement buy logic
        if (currentPrice < buyThreshold) {
            logger.info("[MYALGO] Placing a buy order at price: " + currentPrice);
            state.addOrder(new Order(currentPrice, quantity));
            return new Action("BUY", currentPrice, quantity);
        }

        // Implement se;; ;pgic
        if (currentPrice < sellThreshold) {
            logger.info("[MYALGO] Placing a sell order at price: " + currentPrice);
            state.addOrder(new Order(currentPrice, -quantity));
            return new Action("SELL", currentPrice, -quantity);
        }

        return NoAction.NoAction;
    }
}
