@Echo Off

java -XX:+PrintFlagsFinal -version | findstr HeapSize
