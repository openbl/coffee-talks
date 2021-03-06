package com.chicago.labs.humans

import com.chicago.labs.domain.Human
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
open class HumansService(var humanRepository: HumanRepository) {

    open fun create(human: Human) {
        humanRepository.save(human)
    }

    open fun getAll(): List<Human> {
        return humanRepository.findAll()
    }

    open fun remove(human: Human) {
        humanRepository.deleteHumanByEmail(human.email)
    }

    open fun getEmails() : List<String> {
        return humanRepository.findAll().map { it.email }
    }
}
