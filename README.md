1. I have created a Rest API using Spring framework
2. LoyaltyPointController is a controller which has following endpoints:
    a. getReport - type: GET
    b. getSortedReport - type: GET
3. I have used Factory pattern for creating instance of different extractors for extracting data from different type of files
    a. DataExtractorFactory
4. DataExtractor is an Abstract class which has extractData(String filePath) method
5. CsvDataExtractor is concrete implementation of DataExtractor abstract class, which provides implementation for extractData method
6. PointCalculator's calculateLoyaltyPoints() method is used to apply business rules
    NOTE: We can use strategy pattern over here if we have multiple sets (algorithms) of business rules. In the context of current problem
    there is only one set of business rule, so I have not implemented Stratgey pattern over here. But it can be easily extended.

My approach:
a. I am using Java 8 stream API
b. Stream API provides handy methods for grouping, filtering transaction data
c. Also, it is less time consuming to spin-off a working solution
d. Downsides of this approach are that all the data has to be in-memory for loyalty point calculation
e. Memory can be a bottleneck if the file contains lots of transaction records

Another approach:
a. Instead of processing all the data in-memory, read the data from files and save it in Database (e.g. MySQL)
b. All the processing for grouping and calculating loyalty points can be done on the database server, which will reduce the memory load
from Application server.
c. Also, if we need to generate the report multiple times for a particular date, we only need to extract the data in DB only once
d. We can query the data N number of times, using SQL queries
e. Also, at any point if the computation become heavy, we can store the computed results in DB as well, which can be used to generate report

NOTE: Due to lack of time I couldn't create a working solution using DB approach.