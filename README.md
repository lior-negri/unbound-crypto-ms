# unbound-crypto-ms

### Set up & Run
1. Clone the unbound-crypto-ms repository.
2. Install all required dependencies using maven.
3. run: mvn clean package in order to build the application jar file.
4. navigate to the jar target folder
5. run: java -jar crypto-ms-1.0.0.jar (or run through your IDE).

### API Explanations
controller baseUrl: http://localhost:8080/crypto
#### generateKey
HTTP POST http://localhost:8080/crypto/keys \
generates a new RSA KeyPair and returns a UUID string which serves as their unique key (e.g dc4b4395-cee5-44ec-ad2e-6758131fee7f)
with HttpStatus 201 (created)
#### deleteKey
HTTP DELETE http://localhost:8080/crypto/keys/{id} \
example: http://localhost:8080/crypto/keys/dc4b4395-cee5-44ec-ad2e-6758131fee7f
will delete the id associated RSA KeyPair with HttpStatus 204 (no content)
iff the id exists.
#### getIds
HTTP GET http://localhost:8080/crypto/keys/ids \
will return a list of all existing KeyPairs ids along with a 200 (ok) status response.
#### sign
HTTP POST http://localhost:8080/crypto/signatre \
content-type: application/json \
with parameters:
* id: UUID String
* data: a base64 string or byte array
* request body example:\
``
{
    "id": "dc4b4395-cee5-44ec-ad2e-6758131fee7f",
    "data": "aGVsbG8gd29ybGQ0NDQ="
}
``

returns the corresponding signature in it's base64 string representation
with a status 200 (ok) iff the id exists.
#### verifySign
HTTP POST http://localhost:8080/crypto/signatre \
content-type: application/json \
with parameters:
* id: UUID String
* data: a base64 string or byte array
* signature: a base64 string representation of the signature
* request body example: \
``
{
    "id": "dc4b4395-cee5-44ec-ad2e-6758131fee7f",
    "data": "aGVsbG8gd29ybGQ0NDQ=",
    "signature": "oSXk+kRf3I5yD4yHjuM3R587h2DU7oCrEdtW7zgxBluX9pKpDiGEz2sT3xZ..."
}
``

returns true iff the id exists, and the signature has been verified on the given data,
with a status 200 (ok).







