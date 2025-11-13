@Echo Off

java -Xms1024m -Xmx2048m -server -classpath D:\repositories\Numbers\Sources\.adf;D:\repositories\Numbers\Sources\Numbers-Tests\classes;D:\repositories\Numbers\Sources\Numbers\classes;D:\repositories\Numbers\Libraries\Jmul\jmul-2.1.2.jar;D:\repositories\Numbers\Libraries\Jmul\properties;D:\repositories\Numbers\Libraries\JUnit\junit-4.11.jar;D:\repositories\Numbers\Libraries\JUnit\hamcrest-core-1.3.jar -Djavax.net.ssl.trustStore=C:\Users\PERIPH~1\AppData\Local\Temp\trustStore4027486544873931982.jks test.jmul.math.numbers.CreateNumberVariant2Test
