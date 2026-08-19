// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: custom toString overrides the generated implementation
 */

// TESTCASE NUMBER: 1
data class Id(val v: Int) {
    override fun toString(): String = "#$v"
}

fun test(): String = Id(1).toString()

fun box(): String {
    if (test() != "#1") return "NOK"
    return "OK"
}
