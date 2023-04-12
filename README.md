# Property-Management-Sysytem
Decentralized Property Management Sysytem to ensure transparency

The given code defines a smart contract named "PropertyManagementSystem" using the Hyperledger Fabric framework. It also defines a class named "PropertyClass" that represents a property, along with an enum "PropertyType" that specifies different types of properties.

The "PropertyClass" contains the following properties:

id: a String representing the unique identifier of the property.
location: a String representing the location of the property.
propertyType: a PropertyType enum value representing the type of the property.
size: a String representing the size of the property.
currentOwner: a String representing the current owner of the property.
currentOwnershipTransferDate: a String representing the date of the current ownership transfer of the property.
The "PropertyClass" also provides getter methods for these properties, and it overrides the equals(), hashCode(), and toString() methods.

The "PropertyTransfer" class is the contract interface that implements the "PropertyManagementSystem" contract. It contains the following methods:

initLedger(): a method to initialize the ledger.
registerNewProperty(): a method to register a new property in the ledger. It first checks if the property already exists in the ledger. If it does not exist, it creates a new Property object and serializes it as a JSON object. If it already exists, it throws a ChaincodeException with an appropriate error message.
The contract uses the Genson library to serialize and deserialize JSON objects.
