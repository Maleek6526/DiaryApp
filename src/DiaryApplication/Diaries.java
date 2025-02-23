package DiaryApplication;
import java.util.ArrayList;
import java.util.List;

public class Diaries {
    private List<Diary> diaries;

    public Diaries() {
        diaries = new ArrayList<>();
    }

    public void add(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty.");
            return;
        }
        if (findByUsername(username) != null) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }
        diaries.add(new Diary(username, password));
        System.out.println("Diary created for user: " + username);
    }

    public Diary findByUsername(String username) {
        for (Diary diary : diaries) {
            if (diary.getUsername().equals(username)) {
                return diary;
            }
        }
        return null;
    }

    public void delete(String username, String password) {
        Diary diaryToDelete = findByUsername(username);
        if (diaryToDelete != null && diaryToDelete.checkPassword(password)) {
            diaries.remove(diaryToDelete);
            System.out.println("Diary deleted for user: " + username);
        } else {
            System.out.println("Failed to delete diary. Incorrect username or password.");
        }
    }

    public List<Diary> getEntries() {
        return diaries;
    }
}