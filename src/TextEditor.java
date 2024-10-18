import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
    private StringBuilder text;
    private Stack<String> undoStack;
    private Stack<String> redoStack;
    private boolean isFirstWrite;

    public TextEditor() {
        text = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        isFirstWrite = true;
    }

    public void show() {
        System.out.println("Teks saat ini:");
        System.out.println(text.toString());
    }

    public void write(String newText) {
        undoStack.push(text.toString());
        if (isFirstWrite) {
            text.append(newText);
            isFirstWrite = false;
        } else {
            text.append(" ").append(newText);
        }
        redoStack.clear();
        System.out.println("Teks ditambahkan: " + newText);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(text.toString());
            text = new StringBuilder(undoStack.pop());
            System.out.println("Undo berhasil.");
            if (undoStack.isEmpty()) {
                isFirstWrite = true;
            }
        } else {
            System.out.println("Tidak ada tindakan untuk di-undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(text.toString());
            text = new StringBuilder(redoStack.pop());
            System.out.println("Redo berhasil.");
        } else {
            System.out.println("Tidak ada tindakan untuk di-redo.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor();
        String command;

        System.out.println("Selamat datang di Simulator Text Editor!");
        System.out.println("Perintah: show, write <text>, undo, redo, exit");

        while (true) {
            System.out.print("Masukkan perintah: ");
            command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("show")) {
                editor.show();
            } else if (command.startsWith("write ")) {
                String textToWrite = command.substring(6); // Mengambil teks setelah "write "
                editor.write(textToWrite);
            } else if (command.equalsIgnoreCase("undo")) {
                editor.undo();
            } else if (command.equalsIgnoreCase("redo")) {
                editor.redo();
            } else if (command.equalsIgnoreCase("exit")) {
                System.out.println("Keluar dari simulator.");
                break;
            } else {
                System.out.println("Perintah tidak dikenali. Coba lagi.");
            }
        }

        scanner.close();
    }
}
