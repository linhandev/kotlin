// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 111 -> sentence 111
 * NUMBER: 3
 * DESCRIPTION: TAILREC token in tailrec gcd function with modulo
 */
tailrec fun gcd111(a: Int, b: Int): Int {
    return if (b == 0) a else gcd111(b, a % b)
}

// TESTCASE NUMBER: 1
fun box(): String = if (gcd111(48, 18) == 6) "OK" else "NOK"
