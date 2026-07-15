// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 77 -> sentence 77
 * NUMBER: 2
 * DESCRIPTION: INIT token in multiple init blocks with runtime side effect
 */
// TESTCASE NUMBER: 1

class MultiInit77 {
    val first: Int
    val second: Int

    init {
        first = 21
    }

    init {
        second = first * 2
    }
}

fun box(): String {
    val holder = MultiInit77()
    return if (holder.second == 42) "OK" else "NOK"
}
