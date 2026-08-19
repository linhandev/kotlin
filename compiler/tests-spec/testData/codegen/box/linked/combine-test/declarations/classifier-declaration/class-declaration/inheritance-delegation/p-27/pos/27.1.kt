// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: local delegated property in function
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val x: Int by lazy { 42 }
    return x
}

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
