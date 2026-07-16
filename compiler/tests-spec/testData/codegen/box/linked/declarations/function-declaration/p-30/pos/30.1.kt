// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: nested function body accesses outer variables and class members at runtime
 */

// TESTCASE NUMBER: 1
private val outerValue = 10

class Holder(val member: Int) {
    fun compute(): Int {
        fun inner(): Int = outerValue + member
        return inner()
    }
}

fun outerFunction(base: Int): Int {
    fun inner(): Int = base + 1
    return inner()
}

fun box(): String {
    val fromClass = Holder(5).compute()
    val fromOuter = outerFunction(41)
    return if (fromClass == 15 && fromOuter == 42) "OK" else "NOK class=$fromClass outer=$fromOuter"
}
