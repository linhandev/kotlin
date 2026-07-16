// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, return-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: return inside inline lambda returns from enclosing inline function
 */

// TESTCASE NUMBER: 1

inline fun runBlock(block: () -> Unit) {
    block()
}

fun readFromInline(): String {
    runBlock {
        return "inline-return"
    }
    return "fell-through"
}

fun box(): String {
    return if (readFromInline() == "inline-return") "OK" else "NOK"
}
