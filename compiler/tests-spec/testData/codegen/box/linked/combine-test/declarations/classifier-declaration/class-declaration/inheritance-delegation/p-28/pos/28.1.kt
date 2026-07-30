// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: local delegated property inside run lambda
 */

// TESTCASE NUMBER: 1
fun test() = run {
    val x: Int by lazy { 42 }
    x
}

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
