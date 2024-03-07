package com.github.saga_pricer;

import com.github.saga_pricer.abstractions.AbstractPricer;
import com.github.saga_pricer.abstractions.BasketParser;
import com.github.saga_pricer.abstractions.PriceLookup;
import com.github.saga_pricer.abstractions.ReductionComputer;
import com.github.saga_pricer.basic_impl.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * PricerSingleton provides only and only one implementation of an AbstractPricer
 * No need to reference custom implementations as long as a public constructor is provided.
 * <br>
 * In case no singleton implementation is required, multiple implementations could be defined
 * out if this project's scope by extending {@link AbstractPricer}.
 */
public class PricerSingleton {

    private PricerSingleton() {
    }

    private static AbstractPricer<?> pricerInstance = null;

    /**
     * Dynamically get an instance of an AbstractPricer with independently implemented behaviours
     * <p>
     * Please make sure that <b>customAbstractPricerImplClass</b> has an accessible constructor.
     *
     * @param customAbstractPricerImplClass The class of the target implementation.
     * @param customBasketParser            The custom parser implementation.
     * @param customReductionComputer       The custom reduction computer implementation.
     * @param customPriceLookup             The custom price searcher implementation.
     * @return A singleton instance of the custom Pricer.
     * @throws NoSuchMethodException     In case custom Pricer does not declare a constructor.
     * @throws InvocationTargetException In case of a failure to invoke the constructor.
     * @throws InstantiationException    An instance could not be created.
     * @throws IllegalAccessException    In case custom Pricer does not declare a public constructor.
     */
    public static AbstractPricer getCustomPricer(Class customAbstractPricerImplClass, BasketParser customBasketParser,
                                                 ReductionComputer customReductionComputer,
                                                 PriceLookup customPriceLookup)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (pricerInstance == null) {
            Constructor<AbstractPricer> customPricerConstructor =
                    customAbstractPricerImplClass.getConstructor(
                            BasketParser.class,
                            ReductionComputer.class,
                            PriceLookup.class);
            pricerInstance = (AbstractPricer)
                    customPricerConstructor.newInstance(customBasketParser, customReductionComputer, customPriceLookup);
        }
        return pricerInstance;
    }

    /**
     * If a custom implementation is required this function will dynamically fetch you implementations constructor
     * and call it please note that this function is only available if you implement the behavioural attributes
     * inside the custom implementations constructor.
     * <p>
     * {@code Map} below is an example
     * <pre>{@code
     * class X extends AbstractPricer<Object> {
     *     public X() {
     *         super(
     *         new BasketParser<DvdInfo>(){
     *             @Override
     *             public List<DvdInfo> parseBasket(String basketJson) {
     *                 // custom parse process goes here
     *             }} ,
     *         new ReductionComputer<Object>(){
     *             @Override
     *             public Double getReduction(Integer numberOfItemsPurchased, Object saga) {
     *                 // how the reduction should be computed
     *             }},
     *         new PriceLookup<Object>(){
     *             @Override
     *             public Double getPrice(Object saga) {
     *               // what's the saga price
     *                     }}
     *                     );
     *     }
     *     @Override
     *     protected Double computeBasketPrice(List<DvdInfo> basket) {
     *         // some code
     *     }}
     * }</pre>
     *
     * @param customAbstractPricerImplClass The class of the target implementation.
     * @return A singleton instance of the custom Pricer.
     * @throws NoSuchMethodException     In case custom Pricer does not declare a constructor.
     * @throws InvocationTargetException In case of a failure to invoke the constructor.
     * @throws InstantiationException    An instance could not be created.
     * @throws IllegalAccessException    In case custom Pricer does not declare a public constructor.
     */
    public static AbstractPricer getCustomPricerWithInnerImpl(Class customAbstractPricerImplClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (pricerInstance == null) {
            Constructor customPricerConstructor =
                    customAbstractPricerImplClass.getConstructor();
            pricerInstance = (AbstractPricer)
                    customPricerConstructor.newInstance();
        }
        return pricerInstance;
    }

    /**
     * If the basic pricer implementation is enough to cover your needs this function
     * will provide a valid AbstractPricer with already implemented behavioural attribute.
     *
     * @return an instance of AbstractPricer.
     * @see BasicBasketParserImpl
     * @see BasicReductionComputerImpl
     * @see BasicPriceLookupImpl
     */
    public static AbstractPricer<String> getBasicPricer() {
        return new BasicPricer(
                new BasicBasketParserImpl(),
                new BasicReductionComputerImpl(),
                new BasicPriceLookupImpl());
    }
}
