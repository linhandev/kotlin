// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 111 -> sentence 111
 * NUMBER: 1
 * DESCRIPTION: TAILREC token in tailrec recursive function
 */
tailrec fun sum111(n: Int, acc: Int = 0): Int {
    return if (n == 0) acc else sum111(n - 1, acc + n)
}

// TESTCASE NUMBER: 1
fun box(): String { return if (sum111(10) == 55) "OK" else "NOK" }
