// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 114 -> sentence 114
 * NUMBER: 4
 * DESCRIPTION: INFIX token in member infix function
 */
// TESTCASE NUMBER: 1
class PairMaker114 {
    infix fun from114(label: String): String = label
}

fun box(): String {
    val r = PairMaker114() from114 "infix-114-4"
    return if (r == "infix-114-4") "OK" else "NOK"
}
