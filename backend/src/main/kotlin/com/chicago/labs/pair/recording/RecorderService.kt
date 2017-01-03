package com.chicago.labs.pair.recording

import com.chicago.labs.domain.Pair
import com.chicago.labs.domain.PairHistory
import com.chicago.labs.pair.PairHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
open class RecorderService
@Autowired constructor(var pairHistoryRepository: PairHistoryRepository) {
    open fun record(pairingList: List<Pair>) {
        pairingList.forEach { pair ->
            val emailTwo = if (pair.second == null) {
                "foreveralone"
            } else {
                pair.second!!.email
            }
            val pairHistory = pairHistoryRepository.findOneByEmailOneAndEmailTwo(pair.first?.email!!, emailTwo!!)
            if(pairHistory == null) {
                pairHistoryRepository.save(PairHistory(emailOne = pair.first?.email!!, emailTwo = emailTwo))
            } else {
                pairHistory.timesPaired++
                pairHistory.lastPairDate = Date()
                pairHistoryRepository.save(pairHistory)
            }
        }
    }

}