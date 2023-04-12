package PropertyManagementSystem;

import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import java.util.Objects;

// Define the data type for this class
@DataType()
public class PropertyClass {

    // Define an enum for the different types of properties
    public enum PropertyType{
        RESIDENTIAL,
        COMMERCIAL,
        INDUSTRIAL
    }

    // Define the properties of a property object
    @Property()
    private final String id;

    @Property()
    private final String location;

    @Property()
    private final PropertyType propertyType;

    @Property()
    private final String size;
    
    @Property()
    private final String currentOwner;

    @Property()
    private final String currentOwnershipTransferDate;

    // Define the constructor for creating a new property object
    public PropertyClass(@JsonProperty("id") final String _id,
        @JsonProperty("location") final String _location,
        @JsonProperty("propertType") final PropertyType _propertyType,
        @JsonProperty("size") final String _size,
        @JsonProperty("currentOwner") final String _currentOwner,
        @JsonProperty("currentOwnershipTransferDate") final String _currentOwnershipTransferDate) {
        this.id = _id;
        this.location = _location;
        this.propertyType = _propertyType;
        this.size = _size;
        this.currentOwner = _currentOwner;
        this.currentOwnershipTransferDate = _currentOwnershipTransferDate;
    }

    // Define getter methods for the properties
    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public String getSize() {
        return size;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public String getCurrentOwnershipTransferDate() {
        return currentOwnershipTransferDate;
    }

    // Define the equals() method for comparing property objects
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        PropertyClass other = (PropertyClass) obj;

        return Objects.equals(id, other.id) &&
            Objects.equals(location, other.location) &&
            Objects.equals(propertyType, other.propertyType) &&
            Objects.equals(size, other.size) &&
            Objects.equals(currentOwner, other.currentOwner) &&
            Objects.equals(currentOwnershipTransferDate, other.currentOwnershipTransferDate);
    }

    // Define the hashCode() method for calculating the hash code of a property object
    @Override
    public int hashCode() {
        return Objects.hash(id, location, propertyType, size, currentOwner, currentOwnershipTransferDate);
    }

    // Define the toString() method for creating a string representation of a property object
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + "[id = " + id + ", Location = " + location + ", Property Type = " + propertyType + ", Size = " + size + ", Current Owner = " + currentOwner + ", Bought on = " + currentOwnershipTransferDate + " ]";
    }

}
