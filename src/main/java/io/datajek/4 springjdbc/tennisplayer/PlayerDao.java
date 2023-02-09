package io.datajek.springdata.tennisplayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Get info of all players
     * @return a list of Player objects
     */
    public List<Player> getAllPlayers() {
        String sql = "SELECT * FROM PLAYER";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Player>(Player.class));
    }

    /**
     * Find the Player with the given id
     * @param id
     * @return a Player object
     */
    public Player getPlayerById(int id) {
        String sql = "SELECT * FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Player>(Player.class), new Object[] {id});
    }

    /**
     * Insert new player into the Player table
     * @param player
     * @return the number of affected rows in the table
     */
    public int insertPlayer(Player player){
        String sql = "INSERT INTO PLAYER (ID, Name, Nationality, Birth_date, Titles) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[] {
                player.getId(),
                player.getName(),
                player.getNationality(),
                new Timestamp(player.getBirthDate().getTime()), player.getTitles()});
    }

    /**
     * Make update to an existing player in the table
     * @param player
     * @return the number of affected rows in the table
     */
    public int updatePlayer(Player player){
        String sql = "UPDATE PLAYER SET Name = ?, Nationality = ?, Birth_date = ? , Titles = ? WHERE ID = ?";
        return jdbcTemplate.update(sql, new Object[] {
                player.getName(),
                player.getNationality(),
                new Timestamp(player.getBirthDate().getTime()), player.getTitles(), player.getId()});
    }

    /**
     * Delete a row from the table
     * @param id
     * @return the number of affected rows in the table
     */
    public int deletePlayerById(int id){
        String sql="DELETE FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.update(sql, new Object[] {id});
    }

    /**
     * Create a new table
     */
    public void createTournamentTable() {
        String sql = "CREATE TABLE TOURNAMENT (ID INTEGER, NAME VARCHAR(50), LOCATION VARCHAR(50), PRIMARY KEY (ID))";
        jdbcTemplate.execute(sql);
    }

    /**
     * Find the Player with the given nationality
     * @param nationality
     * @return a list of Player objects
     */
    public List<Player> getPlayerByNationality(String nationality) {
        String sql = "SELECT * FROM PLAYER WHERE NATIONALITY = ?";
        return jdbcTemplate.query(sql, new PlayerMapper(), new Object[] {nationality});
    }

    /**
     * Define a custom mapping
     */
    private static final class PlayerMapper implements RowMapper {
        @Override
        public Player mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Player player = new Player();
            player.setId(resultSet.getInt("id"));
            player.setName(resultSet.getString("name"));
            player.setNationality(resultSet.getString("nationality"));
            player.setBirthDate(resultSet.getDate("birth_date")); //.getTime("birth_date"));
            player.setTitles(resultSet.getInt("titles"));
            return player;
        }
    }
}
