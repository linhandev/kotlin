// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: body properties are not part of equals/copy/componentN
 */

// TESTCASE NUMBER: 1
data class Box(val id: Int) {
    var extra = 0
}

fun test(): Boolean {
    val a = Box(1)
    a.extra = 9
    val b = Box(1)
    b.extra = 0
    return a.copy() == b && a == b
}

fun box(): String {
    if (!test()) return "NOK"
    val (id) = Box(3)
    if (id != 3) return "NOK"
    return "OK"
}
