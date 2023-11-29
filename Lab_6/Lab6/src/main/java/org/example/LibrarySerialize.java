package org.example;
import java.io.*;

public class LibrarySerialize {
    private static final String FILE_PATH = "file.txt";

    public static void serialize(Library library) {
        try (ObjectOutputStream oOutStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oOutStream.writeObject(library);
            System.out.println("Library serialized successfully.");
        } catch (IOException e ) {
            System.out.println("Error during serialization: " + e.getMessage());
        }
    }

    public static Library deserialize() {
        Library serializedLib = null;
        try (ObjectInputStream oInStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            serializedLib = (Library) oInStream.readObject();
            System.out.println("Library deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
        return serializedLib;
    }
}
