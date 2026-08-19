// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 22 -> sentence 22
 *                declarations, function-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: data class with default primary constructor parameters still generates copy
 */

// TESTCASE NUMBER: 1
data class Item(val id: Int, val active: Boolean = true)

fun test(): Boolean = Item(1).copy().active

fun box(): String {
    if (!test()) return "NOK"
    if (Item(1, false).copy(active = true).active != true) return "NOK"
    return "OK"
}
