package DiaryApplication;
import java.util.ArrayList;
import java.util.List;

public class Diary {
    private String username;
    private String password;
    private boolean isLocked;
    private List<Entry> entries;

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLocked = false;
        this.entries = new ArrayList<>();
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock(String password) {
        if (this.password.equals(password)) {
            isLocked = false;
        } else {
            System.out.println("Incorrect password. Diary remains locked.");
        }
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void createEntry(String title, String body) {
        if (isLocked) {
            System.out.println("Diary is locked. Please unlock it to create an entry.");
            return;
        }
        if (title.isEmpty() || body.isEmpty()) {
            System.out.println("Title and body cannot be empty.");
            return;
        }
        Entry entry = new Entry(entries.size() + 1, title, body);
        entries.add(entry);
        System.out.println("Entry created: " + title);
    }

    public void deleteEntry(int id) {
        Entry entry = findEntryById(id);
        if (entry != null) {
            entries.removeIf(e -> e.getId() == id);
            System.out.println("Entry deleted.");
        } else {
            System.out.println("Entry not found.");
        }
    }

    public Entry findEntryById(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public List<Entry> getEntries() {
        return entries;
    }
}