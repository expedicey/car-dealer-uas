/**
 * Represents a node in the TestDriveQueue linked list. Contains a TestDriveRequest, 
 * which includes the customer and car information.
 */
public class TestDriveQueueNode {
    TestDriveRequest request;        // The test drive request associated with this node
    TestDriveQueueNode next;         // Reference to the next node in the queue

    /**
     * Constructor to create a new TestDriveQueueNode with a given test drive request.
     * 
     * @param request The TestDriveRequest to be associated with this node.
     */
    public TestDriveQueueNode(TestDriveRequest request) {
        this.request = request; 
    }
}