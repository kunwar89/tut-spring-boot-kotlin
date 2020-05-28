package com.example.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
		val entityManager: TestEntityManager,
		val userRepository: UserRepository,
		val articleRepository: ArticleRepository,
        val taskRepository: TaskRepository) {

	@Test
	fun `When findByIdOrNull then return Article`() {
		val juergen = User("springjuergen", "Juergen", "Hoeller")
		entityManager.persist(juergen)
		val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
		entityManager.persist(article)
		entityManager.flush()
		val found = articleRepository.findByIdOrNull(article.id!!)
		assertThat(found).isEqualTo(article)
	}

	@Test
	fun `When findByLogin then return User`() {
		val juergen = User("springjuergen", "Juergen", "Hoeller")
		entityManager.persist(juergen)
		entityManager.flush()
		val user = userRepository.findByLogin(juergen.login)
		assertThat(user).isEqualTo(juergen)
	}

	@Test
	fun `When findByExternalIdIn then return Tasks`() {
		val taskOne = Task("1", "This is Task One", "Entered by User 1")
		entityManager.persist(taskOne)
		entityManager.flush()

		val taskTwo = Task("2", "This is Task Two", "Entered by User 2")
		entityManager.persist(taskTwo)
		entityManager.flush()

		val tasks = taskRepository.findByExternalIdIn(listOf("1", "2"))

		for (task in tasks) {
			if (task.externalId.equals('1')) {
				assertThat(task).isEqualTo(taskOne)
			} else if (task.externalId.equals('2')) {
				assertThat(task).isEqualTo(taskTwo)
			}
		}
	}

}
