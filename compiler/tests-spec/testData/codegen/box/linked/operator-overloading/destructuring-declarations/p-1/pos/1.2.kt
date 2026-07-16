/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, destructuring-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: lambda parameter destructuring { (x, z) -> ... } calls component1 and component2
 */

// TESTCASE NUMBER: 1
class PairHolder {
    var component1Calls = 0
    var component2Calls = 0

    operator fun component1(): Int {
        component1Calls++
        return 1
    }

    operator fun component2(): Int {
        component2Calls++
        return 2
    }
}

fun box(): String {
    val holder = PairHolder()
    var sum = 0
    fun invoke(block: (PairHolder) -> Unit) = block(holder)
    invoke { (x, z) -> sum = x + z }
    if (holder.component1Calls != 1) return "NOK component1Calls: ${holder.component1Calls}"
    if (holder.component2Calls != 1) return "NOK component2Calls: ${holder.component2Calls}"
    return if (sum == 3) "OK" else "NOK sum: $sum"
}
