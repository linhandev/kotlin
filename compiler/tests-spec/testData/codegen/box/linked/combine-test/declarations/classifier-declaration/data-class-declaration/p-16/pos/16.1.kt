// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 16 -> sentence 16
 *                declarations, destructuring-declarations -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: var primary constructor property still generates componentN
 */

// TESTCASE NUMBER: 1
data class Counter(var n: Int)

fun test(): Int {
    val (v) = Counter(1)
    return v
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
