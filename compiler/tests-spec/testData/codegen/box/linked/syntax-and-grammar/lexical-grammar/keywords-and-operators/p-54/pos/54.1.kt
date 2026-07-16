// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 54 -> sentence 54
 * NUMBER: 1
 * DESCRIPTION: THIS_AT token in this@Outer from inner class property access
 */
// TESTCASE NUMBER: 1

class Outer {
    val tag = "outer"
    inner class Inner {
        fun readTag() = this@Outer.tag
    }
}

fun box(): String {
    return if (Outer().Inner().readTag() == "outer") "OK" else "NOK"
}
