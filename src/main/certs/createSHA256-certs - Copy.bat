mkdir sha-1
cd sha-1
E:\Work2\Tools\OpenSSL-Win32\bin\openssl genrsa 2048 > ca-key.pem

E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -sha1 -new -x509 -nodes -days 3650 -key ca-key.pem -config ..\rootca.cfg > ca-cert.pem

E:\Work2\Tools\OpenSSL-Win32\bin\openssl genrsa 2048 > clientca-key.pem

E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -sha1 -new -x509 -nodes -days 3650 -key clientca-key.pem -config ..\rootclientca.cfg > clientca-cert.pem


E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -sha1 -newkey rsa:2048 -days 730 -nodes -keyout server-key.pem -config ..\server.cfg > server-req.pem 

E:\Work2\Tools\OpenSSL-Win32\bin\openssl x509 -sha1 -req -in server-req.pem -days 730 -CA ca-cert.pem -CAkey ca-key.pem -set_serial 01 > server-cert.pem 

E:\Work2\Tools\OpenSSL-Win32\bin\openssl rsa -in server-key.pem -out server-key.pem

E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -sha1 -newkey rsa:2048 -days 730 -nodes -keyout client-key.pem -config ..\client.cfg > client-req.pem

E:\Work2\Tools\OpenSSL-Win32\bin\openssl x509 -sha1 -req -in client-req.pem -days 730 -CA clientca-cert.pem -CAkey clientca-key.pem -set_serial 01 > client-cert.pem

E:\Work2\Tools\OpenSSL-Win32\bin\openssl rsa -in client-key.pem -out client-key.pem

cd ..