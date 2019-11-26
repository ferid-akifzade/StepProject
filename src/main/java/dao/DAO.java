package dao;

import java.io.IOException;
import java.util.ArrayList;

public interface DAO<T> {
    ArrayList<T> getDatabase() throws IOException;
    void read() throws IOException;
}
