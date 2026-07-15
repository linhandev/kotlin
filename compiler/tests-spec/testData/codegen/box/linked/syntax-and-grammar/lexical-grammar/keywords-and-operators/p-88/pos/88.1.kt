// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 88 -> sentence 88
 * NUMBER: 1
 * DESCRIPTION: FOR token in for-in loop over range
 */
// TESTCASE NUMBER: 1
fun sumFor88(): Int {
    var total = 0
    for (i in 1..3) {
        total += i
    }
    return total
}

fun box(): String { return when { sumFor88() == 6 -> "OK"; else -> "NOK" } }
