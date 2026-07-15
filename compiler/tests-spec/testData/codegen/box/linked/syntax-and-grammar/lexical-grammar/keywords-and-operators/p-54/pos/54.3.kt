// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 54 -> sentence 54
 * NUMBER: 3
 * DESCRIPTION: THIS_AT token in this@block from labeled buildString receiver
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = buildString block@ {
        if (this@block.isEmpty()) {
            append("OK")
        }
    }
    return result
}
