// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 111 -> sentence 111
 * NUMBER: 4
 * DESCRIPTION: TAILREC token in member tailrec function
 */
// TESTCASE NUMBER: 1
class TailHolder111 {
    tailrec fun loop111(steps: Int): String {
        if (steps == 0) return "OK"
        return loop111(steps - 1)
    }
}

fun box(): String = TailHolder111().loop111(2)
