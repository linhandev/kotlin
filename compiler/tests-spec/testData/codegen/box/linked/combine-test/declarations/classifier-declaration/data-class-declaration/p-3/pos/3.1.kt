// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 3 -> sentence 3
 *                declarations, destructuring-declarations -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: componentN order matches primary constructor parameter order
 */

// TESTCASE NUMBER: 1
data class Tag(val id: Int, val name: String)

fun test(t: Tag): Pair<Int, String> = t.component1() to t.component2()

fun box(): String {
    val r = test(Tag(7, "x"))
    if (r.first != 7 || r.second != "x") return "NOK"
    return "OK"
}
