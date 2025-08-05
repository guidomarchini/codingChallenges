package preparation.firstCodingPrep

import kotlin.random.Random

sealed class PaymentState

object Pending : PaymentState()
object Processing : PaymentState()
object Success : PaymentState()
data class Failed(val errorMessage: String) : PaymentState()

fun handleState(paymentState: PaymentState) {
    when (paymentState) {
        is Failed -> println("Payment failed with error ${paymentState.errorMessage}")
        is Success -> println("Payment successful!")
        else -> println("Payment is processing with state ${paymentState.toString()}")
    }
}

fun nextState(current: PaymentState): PaymentState {
    return when (current) {
        is Pending -> return Processing
        is Processing -> return if (Random.nextBoolean()) Success else Failed("Random failure");
        is Success -> return current // No next state after success
        is Failed -> return current // No next state after failure
    }
}

fun nextStateWithRetry(
    currentState: PaymentState,
    retriesLeft: Int,
    transitions: List<String> = listOf()
): Pair<PaymentState, List<String>> {
    if (retriesLeft < 0 || isTerminal(currentState)) return Pair(currentState, transitions)

    return (if (Random.nextBoolean()) nextState(currentState) else currentState)
        .let {
            nextStateWithRetry(
                it,
                retriesLeft - 1,
                transitions + "$currentState -> $it"
            )
        }
}

fun isTerminal(state: PaymentState): Boolean {
    return when (state) {
        is Success, is Failed -> true
        else -> false
    }
}