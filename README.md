# HammingCode
This program allows client to enter 11 binary digits and send them to server, so that server can detect errors, correct them and send corrected data back to the client.

## Requirements
* Eclipse
* Windowbuilder 1.9.1


## How it works

  - Run a server side application first.
  - Run a client side application then this is already set to connect to the server side through TCP.
  - The server automatically accepts the connection from the client.
  - Users input 11 binary digit and press the click button.
  - Client application sends this data to the server.
  - the server receives the data and detect errors in errors bits.
    - If any errors are found, the server corrects them and sends the corrected data back to the client with information that what have been corrected.
    - If there is no error, the server sends a message that data has no error to the client.


## Screenshots

![alt text](https://github.com/s3634359/HammingCode/blob/master/screenshots/h1.PNG)

1. Run server first and client.

![alt text](https://github.com/s3634359/HammingCode/blob/master/screenshots/h2.PNG)

2. Input 11 binary digits in the text field and press the check button

![alt text](https://github.com/s3634359/HammingCode/blob/master/screenshots/h3.PNG)
![alt text](https://github.com/s3634359/HammingCode/blob/master/screenshots/h4.PNG)

3. Server checks the data from client. If there are errors in data, correct them and send corrected data back to client

![alt text](https://github.com/s3634359/HammingCode/blob/master/screenshots/h6.PNG)

4. If no error is found in the data, send a message to client that data is correct.

![alt text](https://github.com/s3634359/HammingCode/blob/master/screenshots/h6.PNG)

5. If client doesn't input 11 binary digits, server sends an alter to client.

