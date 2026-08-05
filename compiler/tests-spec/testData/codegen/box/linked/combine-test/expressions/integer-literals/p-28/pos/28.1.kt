// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 28 -> sentence 28
 *                expressions, when-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: when expression branch with separator integer literal matches subject value
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = when (x) {
    1_000 -> "k"
    else -> "o"
}

fun box(): String {
    if (test(1_000) != "k") return "NOK: separator literal matches when branch"
    if (test(1000) != "k") return "NOK: plain literal matches same when branch"
    if (test(999) != "o") return "NOK: value below separator branch hits else"
    if (test(1_001) != "o") return "NOK: value above separator branch hits else"
    return "OK"
}
