// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 93 -> sentence 93
 * NUMBER: 3
 * DESCRIPTION: CONTINUE token in labeled continue@loop
 */
// TESTCASE NUMBER: 1
fun labeledContinue93(): String {
    var total = 0
    outer@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) continue@outer
            total++
        }
    }
    return if (total == 3) "OK" else "NOK"
}

fun box(): String = labeledContinue93()
