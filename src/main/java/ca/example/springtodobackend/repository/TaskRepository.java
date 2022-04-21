package ca.example.springtodobackend.repository;

import ca.example.springtodobackend.model.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskRepository {
    @Select("SELECT * FROM tasks")
    List<Task> findAll();

    @Select("SELECT * FROM tasks WHERE id = #{id}")
    Task findById(long id);

    @Delete("DELETE FROM tasks WHERE id = #{id}")
    int deleteById(long id);

    @Insert("INSERT INTO tasks(title, description) VALUES (#{title}, #{description})")
    int insert(Task task);

    @Update("UPDATE tasks SET title=#{title}, description=#{description} WHERE id=#{id}")
    int update(Task task);
}