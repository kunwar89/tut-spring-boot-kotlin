package com.example.blog

import org.springframework.data.repository.CrudRepository
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long> {
	fun findBySlug(slug: String): Article?
	fun findAllByOrderByAddedAtDesc(): Iterable<Article>
}

interface UserRepository : CrudRepository<User, Long> {
	fun findByLogin(login: String): User?
}

interface TaskRepository : JpaRepository<Task, Long> {
	fun findByExternalIdIn(externalId: List<String>): Iterable<Task>
}

