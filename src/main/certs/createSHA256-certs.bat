mkdir sha-256
cd sha-256
E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -x509 -newkey rsa:2048 -sha256 -keyout rootca.key -out rootca.cer -days 2000 -config ..\rootca.cfg

E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -x509 -newkey rsa:2048 -sha256 -keyout server.key -out server.cer -days 2000 -config ..\server.cfg

E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -x509 -newkey rsa:2048 -sha256 -keyout rootclientca.key -out rootclientca.cer -days 2000 -config ..\rootclientca.cfg

E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -x509 -newkey rsa:2048 -sha256 -keyout client.key -out client.cer -days 2000 -config ..\client.cfg

E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -newkey rsa:2048 -sha256 -key server.key -out server.csr -days 2000 -config ..\server.cfg

E:\Work2\Tools\OpenSSL-Win32\bin\openssl req -newkey rsa:2048 -sha256 -key client.key -out client.csr -days 2000 -config ..\client.cfg

E:\Work2\Tools\OpenSSL-Win32\bin\openssl x509 -req -in server.csr -CA rootca.cer -CAkey rootca.key -sha256 -days 2000 -out server.cer -extfile ..\rootca.cfg -extensions usr_cert -CAcreateserial -passin pass:password

E:\Work2\Tools\OpenSSL-Win32\bin\openssl x509 -req -in client.csr -CA rootclientca.cer -CAkey rootclientca.key -sha256 -days 2000 -out client.cer -extfile ..\rootclientca.cfg -extensions usr_cert -CAcreateserial -passin pass:password

cd ..