public class ChatAppTest {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        UserImpl user1 = new UserImpl("User 1");
        UserImpl user2 = new UserImpl("User 2");

        chatRoom.join(user1);
        chatRoom.join(user2);

        chatRoom.sendMessage("Hello, everyone!");

        assert user1.getReceivedMessage().equals("Hello, everyone!") : "User 1 did not receive the correct message!";
        assert user2.getReceivedMessage().equals("Hello, everyone!") : "User 2 did not receive the correct message!";

        System.out.println("All tests passed!");
    }
}
