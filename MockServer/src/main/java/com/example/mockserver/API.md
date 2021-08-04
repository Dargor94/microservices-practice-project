FORMAT: 01 HOST: http://localhost:9001

​

# Applications MockServer API

​

## Data Structures

​

### Application (object)

Application with access to call local API.

+ id (Long)

### IdentificationType (object)

+ identificationTypeId (Integer)
+ description (String)

### Customer (object)

+ customerId (Long)
+ forename (String)
+ surname (String)
+ identificationNumber (Long)
+ identificationType (IdentificationType)
