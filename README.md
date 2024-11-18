1. keytool -genkey -keyalg RSA -keystore SSLStore -alias SSLCertificate
2. keytool -lít -v keystore SSLStore
3. keytool -storepasswd -keystore SSLStore
4. keytool -export -rfc -alias sslcertificate -keystore SSLStore -file mycert.cer
5. keytool -import -alias sslcertificate -file mycert.cer -keystore truststore