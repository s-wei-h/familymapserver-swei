package Dao;

public class EventDao {
    private final Connection conn;

    public EventDao(Connection conn){
        this.conn = conn;
    }

    public void insert(Event event) throws DataAccessException {

    }
}
