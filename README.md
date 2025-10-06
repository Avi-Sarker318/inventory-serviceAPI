10/06/2025
git clone https://github.com/Avi-Sarker318/inventory-serviceAPI.git
cd inventory-serviceAPI

Code has been tested. Everything works as its intended. However I have not tested error handlers at all. I realized there were syntax mistakes where case sensivity was very important and how to use lombok correctly. If this was not fixed, Spring boot ran but did not retrieve any data. Postman tests was a success. Need to look through globalhandlers more because I know using RuntimeExceptions is not very ideal to throw errors. This is only temporary just to test. I also changed price instead of double to BigDecimal. Even if i will be inserting variables with decimals. In the future, if I were to use sum() or avg(), I will have more than 2 decimal places. Because of this i used precision=10 which lays out 10 whole numbers and scale = 2 which will only give 2 decimal places. I do not know if this is a good practical way to handle this issue but I will check to make sure.


