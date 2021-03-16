# four-russians-algo
Implementation of algorithm Four Russians

Algorithm consists of 2 methods:
preparation: split matrix both A and B on submatrices with A size N x log(N), B size log(N) x N, if necessary we append A with zero columns  
MatrixMultiplication is the main method which implements boolean matrix multiplication, Array C is the result  
All matrix "special" operations are implemented in ArrUtils

To run algorithm download RUN.zip. Firstly unzip the archive. Then use console and go to the directory - command: cd ../../RUN. After that run .jar file - command: java -jar four-russians-algo.jar

IMPORTANT To run .jar you need java to be installed on your computer


Used literature: https://louridas.github.io/rwa/assignments/four-russians/