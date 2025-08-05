package preparation.secondCodingPrep

import java.lang.RuntimeException
import java.util.PriorityQueue
import java.util.UUID

/**
 * Ejercicio: Gestión de sesiones de usuario
 * Imagina que trabajás en un backend que maneja sesiones activas de usuarios.
 * Cada sesión tiene:
 *
 * sessionId: String
 * userId: String
 * lastActivityTimestamp: Long (epoch millis)
 *
 * Queremos implementar:
 * * Un método addOrUpdateSession(session: Session) que agrega una sesión nueva o actualiza la lastActivityTimestamp si la sesión ya existe.
 * * Un método removeInactiveSessions(timeoutMillis: Long) que elimina todas las sesiones cuya última actividad fue hace más de timeoutMillis.
 * * Un método getMostActiveUsers(k: Int): List<String> que devuelve los userId de los k usuarios con más sesiones activas
 * (en caso de empate, cualquier orden está bien).
 */

data class Session(
    val sessionId: String?,
    val userId: String,
    val lastActivityTimestamp: Long // epoc millis
)

object Store {
    val sessionStore: MutableMap<String, Session> = mutableMapOf()
    val userSessions: MutableMap<String, MutableSet<String>> = mutableMapOf()
    val updateSessionValidators: List<SessionValidator> = listOf(UserExistsValidator, UserDidNotChangeValidator)
    val newSessionValidators: List<SessionValidator> = listOf(UserExistsValidator)

    fun addUser(userId: String) {
        UserValidator.validate(userId)
        userSessions[userId] = mutableSetOf()
    }

    fun addOrUpdateSession(session: Session) {
        session.sessionId?.let {
            this.updateSession(session)
        } ?: this.createNewSession(session)
    }

    private fun updateSession(session: Session) {
        updateSessionValidators.forEach { it.validate(session) }

        sessionStore[session.sessionId!!] = session
    }

    private fun createNewSession(session: Session) {
        newSessionValidators.forEach { it.validate(session) }

        val newSessionId = UUID.randomUUID().toString()
        sessionStore[newSessionId] = session.copy(sessionId = newSessionId)
        userSessions[session.userId]!!.add(newSessionId)
    }

    fun removeInactiveSessions(timeoutMillis: Long) {
        sessionStore.values
            .filter { it.lastActivityTimestamp < timeoutMillis }
            .forEach { session ->
                sessionStore.remove(session.sessionId)
                userSessions[session.userId]?.remove(session.sessionId)
            }
    }

    fun getMostActiveUsers(k: Int): List<String> =
        userSessions.entries
            .sortedByDescending { it.value.size }
            .take(k)
            .map { it.key }

    fun getMostActiveUsersByPriorityQueue(k: Int): List<String> {
        val pq = PriorityQueue<Map.Entry<String, MutableSet<String>>>(compareBy { it.value.size })

        for (entry in userSessions) {
            pq.add(entry)
            if (pq.size > k) {
                pq.poll()
            }
        }

        return pq
            .sortedByDescending { it.value.size }
            .map { it.key }
    }
}

object UserValidator {
    fun validate(userId: String) {
        if (Store.userSessions.containsKey(userId)) {
            throw RuntimeException("user is already created")
        }
    }
}

sealed interface SessionValidator {
    fun validate(session: Session)
}

data object UserDidNotChangeValidator: SessionValidator {
    override fun validate(session: Session) {
        if (Store.sessionStore[session.sessionId!!]!!.userId != session.userId) {
            throw RuntimeException("session user cannot change")
        }
    }
}

data object UserExistsValidator: SessionValidator {
    override fun validate(session: Session) {
        if (!Store.userSessions.containsKey(session.userId)) {
            throw RuntimeException("user doesn't exist")
        }
    }
}