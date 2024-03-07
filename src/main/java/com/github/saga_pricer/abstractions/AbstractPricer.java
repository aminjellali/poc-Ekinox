package com.github.saga_pricer.abstractions;

import com.github.saga_pricer.exceptions.BasketCannotBeNullOrEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * <p>
 * A Strategy pattern class used to process a given basket text and
 * provide the price of the clients purchase price after applying reduction.
 * </p>
 * <br>
 *
 * The strategy pattern is used to offer the possibility to dynamically change the following
 * key figure implementations.
 * <ul>
 *     <li>{@link #setBasketParser(BasketParser)} To override the basket parsing behaviour.</li>
 *     <li>{@link #setReductionComputer(ReductionComputer)} In case other offers are to implement in the future.</li>
 *     <li>{@link #setPriceLookup(PriceLookup)} in case classes could come from different sources file, db ...</li>
 * </ul>
 *
 * <p>Below are the target interfaces</p>
 * @see BasketParser
 * @see ReductionComputer
 * @see PriceLookup
 *
 * @param <T> The target class cast that will be used along the process once parsed from text.
 */
public abstract class AbstractPricer<T> {

    private static final Logger log = LogManager.getLogger(AbstractPricer.class);
    /**
     * Instance of basket parser to be used.
     */
    protected BasketParser<T> basketParser;
    /**
     * Instance of reduction computer to be used.
     */
    protected ReductionComputer<T> reductionComputer;
    /**
     * Instance of price searcher to be used.
     */
    protected PriceLookup<T> priceLookup;

    /**
     * The default method used as entry point calling all the components used to
     * parse, compute reductions and aggregate the final price.
     * @param basket Text input.
     * @return The corresponding price to pay.
     * @throws BasketCannotBeNullOrEmptyException In case basket input is empty or null.
     */
    public final double getTotalPrice(String basket) throws BasketCannotBeNullOrEmptyException {
        if (basket == null || basket.isEmpty()) {
            throw new BasketCannotBeNullOrEmptyException();
        }
        log.trace("Pricing the following basket {}", basket);
        List<T> titles = basketParser.parseBasket(basket);
        return computeBasketPrice(titles);
    }

    /**
     * Implement this method in order to define the logic of price computation.
     * @param basket List of objects available once parsing is successfully completed.
     * @return The computed price
     */
    protected abstract Double computeBasketPrice(List<T> basket);

    /**
     * Constructor to be called as super in child classes.
     * @param basketParser Basket parser implementation.
     * @param reductionComputer Reduction computer implementation.
     * @param priceLookup Price searcher implementation.
     */
    protected AbstractPricer(BasketParser<T> basketParser, ReductionComputer<T> reductionComputer, PriceLookup<T> priceLookup) {
        this.basketParser = basketParser;
        this.reductionComputer = reductionComputer;
        this.priceLookup = priceLookup;
    }

    /**
     * Used to change the implementation of the provider in runtime if necessary.
     * @param basketParser The parser implementation to change.
     */
    public void setBasketParser(BasketParser<T> basketParser) {
        this.basketParser = basketParser;
    }

    /**
     * Used to change the implementation of the provider in runtime if necessary.
     * @param reductionComputer The reduction implementation to change.
     */
    public void setReductionComputer(ReductionComputer<T> reductionComputer) {
        this.reductionComputer = reductionComputer;
    }

    /**
     * Used to change the implementation of the provider in runtime if necessary.
     * @param priceLookup The price searcher implementation to change.
     */
    public void setPriceLookup(PriceLookup<T> priceLookup) {
        this.priceLookup = priceLookup;
    }
}
