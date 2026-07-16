// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 88 -> sentence 88
 * NUMBER: 3
 * DESCRIPTION: FOR token in labeled for loop with break@label
 */
// TESTCASE NUMBER: 1
fun labeledFor88(): String {
    loop@ for (i in 1..5) {
        if (i == 3) break@loop
    }
    return "OK"
}

fun box(): String = labeledFor88()
