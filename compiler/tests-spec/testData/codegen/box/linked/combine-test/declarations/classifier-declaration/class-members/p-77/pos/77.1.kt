// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 77 -> sentence 77
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 77 -> sentence 77
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 77 -> sentence 77
 * NUMBER: 1
 * DESCRIPTION: explicit equals override on data class replaces generated equals
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int) {
    override fun equals(other: Any?): Boolean = false
}

fun test(): Boolean = Data(42) == Data(42)

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}
