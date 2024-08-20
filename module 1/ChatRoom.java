import java.util.ArrayList;
import java.util.List;

// Subject: ChatRoom
public class ChatRoom {
    private List<Observer> users = new ArrayList<>();
    private String message;

    public void join(Observer user) {
        users.add(user);
    }

    public void leave(Observer user) {
        users.remove(user);
    }

    public void sendMessage(String message) {
        this.message = message;
        notifyAllUsers();
    }

    private void notifyAllUsers() {
        for (Observer user : users) {
            user.update(message);
        }
    }
}
