// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 111 -> sentence 111
 * NUMBER: 2
 * DESCRIPTION: TAILREC token in tailrec function returning String
 */
tailrec fun countdown111(n: Int): String {
    if (n == 0) return "OK"
    return countdown111(n - 1)
}

// TESTCASE NUMBER: 1
fun box(): String = countdown111(3)
