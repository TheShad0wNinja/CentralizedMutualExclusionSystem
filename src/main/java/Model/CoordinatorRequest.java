package Model;

import java.io.Serializable;

public class CoordinatorRequest implements Serializable {
    public enum OperationMode {
        ACQUIRE,
        RELEASE
    }
    public OperationMode operationMode;

    public enum AccessMode {
        READ,
        WRITE
    }
    public AccessMode accessMode;

    public ResourceType resourceType;

    public CoordinatorRequest(OperationMode operationMode, AccessMode accessMode, ResourceType resourceType) {
        this.operationMode = operationMode;
        this.accessMode = accessMode;
        this.resourceType = resourceType;
    }

    public CoordinatorRequest(OperationMode operationMode, ResourceType resourceType) {
        this.operationMode = operationMode;
        this.resourceType = resourceType;
    }
}
