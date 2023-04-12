package PropertyManagementSystem;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import com.owlike.genson.Genson;

import PropertyManagementSystem.PropertyClass.PropertyType;

// Define a contract class named "PropertyManagementSystem" with additional information about the contract
@Contract(name = "PropertyManagementSystem", info = @Info(title = "PropertyManagementSystem Contract", description = "Property Transfer Contract", version = "0.0.1-SNAPSHOT"))
@Default
public final class PropertyTransfer implements ContractInterface {

    // Define a Genson object to serialize and deserialize JSON objects
    private final Genson genson = new Genson();

    // Define an enum to specify potential errors in the property management system
    private enum PropertyErrors {
        Property_NOT_FOUND,
        Property_ALREADY_EXISTS
    }

    // Define a method to initialize the ledger
    @Transaction()
    public void initLedger(final Context ctx) {

    }

    // Define a method to register a new property
    @Transaction()
    public PropertyClass registerNewProperty(final Context ctx,
            final String _id,
            final String _location,
            final PropertyType _propertyType,
            final String _size,
            final String _currentOwner,
            final String _currentOwnershipTransferDate) {

        ChaincodeStub stub = ctx.getStub();

        // Check if the property already exists in the ledger
        String newProperty = stub.getStringState(_id);

        if (!newProperty.isEmpty()) {
            String errorMessage = String.format("Property %s already exists", _id);
            System.out.println(errorMessage);
            // Throw a ChaincodeException if the property already exists
            throw new ChaincodeException(errorMessage, PropertyErrors.Property_ALREADY_EXISTS.toString());
        }

        // Create a new Property object and serialize it as a JSON object
        PropertyClass property = new PropertyClass(_id, _location, _propertyType, _size, _currentOwner,
                _currentOwnershipTransferDate);

        newProperty = genson.serialize(property);

        // Put the new property into the ledger
        stub.putStringState(_id, newProperty);

        // Return the newly registered property
        return property;
    }

    // This function is used to transfer a property from one owner to another owner
    @Transaction()
    public PropertyClass transferProperty(
            final Context ctx,
            final String _id,
            final String _newOwner,
            final String _newOwnershipDate) {
        ChaincodeStub stub = ctx.getStub();

        // Get the current state of the property using the property id
        String propertyState = stub.getStringState(_id);

        // Check if the property exists
        if (propertyState.isEmpty()) {
            String errorMessage = String.format("Property %s not Registered", _id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, PropertyErrors.Property_NOT_FOUND.toString());
        }

        // Deserialize the property state into a Property object
        PropertyClass property = genson.deserialize(propertyState, PropertyClass.class);

        // Create a new Property object with the new owner and ownership transfer date
        PropertyClass newProperty = new PropertyClass(property.getId(),
                property.getLocation(),
                property.getPropertyType(),
                property.getSize(),
                _newOwner,
                _newOwnershipDate);

        // Serialize the new Property object into a JSON string
        String newPropertyState = genson.serialize(newProperty);

        // Update the property state on the ledger with the new Property object
        stub.putStringState(_id, newPropertyState);

        // Return the new Property object
        return newProperty;
    }

    // This function is used to retrieve the details of a property using the
    // property id
    @Transaction()
       public PropertyClass getPropertyById(final Context ctx, final String _id) {
    	ChaincodeStub stub = ctx.getStub();
   
           // Get the current state of the property using the property id
           String propertyState = stub.getStringState(_id);
   
           // Check if the property exists
           if(propertyState.isEmpty()) {
               String errorMessage = String.format("Property %s not Registered", _id);
               System.out.println(errorMessage);
               throw new ChaincodeException(errorMessage, PropertyErrors.Property_NOT_FOUND.toString());
           }
   
           // Deserialize the property state into a Property object and return it
           PropertyClass property = genson.deserialize(propertyState, PropertyClass.class);
           return property;
       
    }
}
