# ROI_GUI
A simple GUI for determining simple ROI.



### Why this exists
I created this for doing trading and setting targets. Instead of calculating my simple ROI multiple times in a calculator, input 1 number and output all the possible rates which are relevant in trading. 

## RUN:


```
java -jar roi_gui<version>.jar
```


## Package to JAR file

To get snapshot jar, use Maven Jar plugin below

```
mvn package
```


## TODO
- Add event listener box and get rid of initial console use - Done 3/28/21
- Add log4j2 support
- Add Search Bar with REGEX so each column can be searched with only 1 search field
- Add fee field with check box, dollar commission calculations (normally used in stocks) or fee percentage (normally used in cryptocurrency)
- Add Exchange API row to get how much a certain asset is worth in realtime
- Add additional tools and package into larger software package
