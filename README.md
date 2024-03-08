# Welcome to SAGA pricer !

Hi! Instead of going back in time and buy bitcoin I chose to go back in time and make others lives easier, thanks to the wisdom and knowledge I have acquired over the years.
This project is a pricing tool I wrote with the state of the art practices respecting SOLID principles.
Documented code, with the beloved JAVA doc !
100% code coverage !
And Execptions so clear you will become and SDK wisperer !

enjoy :wink:

# How can this project help you ?
This SDK will be easily integrated into your billing system in order to enable you to offer the Back To The future SAGA partner ship. Extremly generic this SDK will fit into you stack like the perfect shoe thanks to uncle BOB.
## Before going further
|Must have                          |Version                   |
| -------------------------------|-----------------------------|
|JAVA         |17           |
|maven            |3.9.5           |
## Through the basics
### Design patterns
----
- Strategy pattern / template pattern: used to change the implementation of different modules on the pricer under package **com.github.saga_pricer.abstractions**
    - BasketParser:  Interface
    - PriceLookup: Interface
    - ReductionComputer: Interface
    - AbstractPricer: Abstract class
- Singelton pattern: used as a single instance provider to avoid memory leaks and memory over consumption under package **com.github.saga_pricer**
    - PricerSingleton
----
### How to use ?
#### if the basic implementation is enough for your use case:
```java
AbstractPricer basicPricerInstance = PricerSingleton.getBasicPricer();
basicPricerInstance.getTotalPrice([Text Input])
```
#### if the basic implementation is not enough:
```java
class X extends AbstractPricer<Object> {  
    public X() {  
        super(  
                new BasketParser<DvdInfo>() {  
                    @Override  
                    public List<DvdInfo> parseBasket(String basketJson) {  
                        // custom parse process goes here  
  }  
                }, new ReductionComputer<Object>() {  
                    @Override  
                    public Double getReduction(Integer numberOfItemsPurchased, Object saga) {  
                        // how the reduction should be computed  
  }  
                },  
 new PriceLookup<Object>() {  
                    @Override  
                    public Double getPrice(Object dvd) {  
                        // what's the dvd price  
  }  
                }  
        );  
  }  
    @Override  
    protected Double computeBasketPrice(List<DvdInfo> basket) {  
        // some code  
  }  
}

// now we can create an instance of X via the singleton provider as follows
AbstractPricer<DvdInfo> pricer = (AbstractPricer<DvdInfo>) PricerSingleton.getCustomPricerWithInnerImpl(TestPricerImpl.class); 
pricer.getTotalPrice(basket);
```
#### if a dynamic implementation is required:
```java
// In this case we don't pass by the PricerSingleton
// fake implementations
TestSeperateBasketParser parser = new TestSeperateBasketParser();  
TestSeperatePriceLookup searcher = new TestSeperatePriceLookup();  
TestSeperateReductionComputer reductionComputer = new TestSeperateReductionComputer();  
TestSeperateReductionComputerNoReduction noReductionComputer= new TestSeperateReductionComputerNoReduction();  
TestSeperatePriceLookupDestockSeason destockSeason = new TestSeperatePriceLookupDestockSeason();  

AbstractPricer<DvdInfo> pricer = new TestPricerSeperateBehavioursImpl(parser, reductionComputer, searcher);
// get total price with reduction.
pricer.getTotalPrice(basket);
// change the reduction policy as none
pricer.setReductionComputer(noReductionComputer);
pricer.getTotalPrice(basket);  
// we are waiting for new awesome dvds let's liquidate
pricer.setPriceLookup(destockSeason);  
pricer.getTotalPrice(basket);
// etc...
```
---
### Forker ?
#### How to build ?
```shell
 mvn clean install
 ```
#### What will build do ?
- build the app for sure :joy:
- run the jacoco plugin to insure test coverage respect check ```/target/site/jacoco/index.html``` for report
- generate java doc check ```/target/site/apidocs/index.html``` for html report.

> **Note:** The build process will fail if the test minimum coverage limit is not respected.


<h5  align="center">made with :heart: </h5>