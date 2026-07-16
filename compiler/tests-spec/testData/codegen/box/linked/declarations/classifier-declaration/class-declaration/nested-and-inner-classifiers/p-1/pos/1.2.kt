// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: inner class can access outer instance members at runtime
 */

// TESTCASE NUMBER: 1
class Outer(val label: String) {
    inner class Inner {
        fun readLabel(): String = label
    }
}

fun box(): String {
    val outer = Outer("OK")
    return if (outer.Inner().readLabel() == "OK") "OK" else "NOK"
}
