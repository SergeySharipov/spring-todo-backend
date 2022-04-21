package ca.example.springtodobackend;

import ca.example.springtodobackend.model.Task;
import ca.example.springtodobackend.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
class StudentMapperIntegrationTest {

	@Autowired
	TaskRepository taskRepository;

	@Test
	public void whenRecordsInDatabase_shouldReturnTaskWithGivenId() {
		Task task = taskRepository.findById(1L);

		assertNotNull(task);
		assertEquals(1L,task.getId());
		assertEquals("Test",task.getTitle());
		assertEquals("Test@gmail.com",task.getDescription());
	}
}

 */