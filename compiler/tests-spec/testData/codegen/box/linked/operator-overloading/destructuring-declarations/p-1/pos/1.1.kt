/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, destructuring-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: val (x, _, z) calls component1 and component3, skips component2 for ignoring placeholder
 */

// TESTCASE NUMBER: 1
class TripleHolder {
    var component1Calls = 0
    var component2Calls = 0
    var component3Calls = 0

    operator fun component1(): String {
        component1Calls++
        return "O"
    }

    operator fun component2(): String {
        component2Calls++
        return "skip"
    }

    operator fun component3(): String {
        component3Calls++
        return "K"
    }
}

fun box(): String {
    val holder = TripleHolder()
    val (x, _, z) = holder
    if (x != "O" || z != "K") return "NOK values: $x, $z"
    if (holder.component1Calls != 1) return "NOK component1Calls: ${holder.component1Calls}"
    if (holder.component2Calls != 0) return "NOK component2Calls: ${holder.component2Calls}"
    if (holder.component3Calls != 1) return "NOK component3Calls: ${holder.component3Calls}"
    return "OK"
}
