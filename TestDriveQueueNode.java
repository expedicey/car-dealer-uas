public class TestDriveQueueNode {
    TestDriveRequest request;
    TestDriveQueueNode next;

    public TestDriveQueueNode(TestDriveRequest request) {
        this.request = request;
    }
}
