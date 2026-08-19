// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, cast-expressions -> paragraph 11 -> sentence 11
 *                type-system, introduction-1 -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: unchecked cast List to List<String> may throw ClassCastException at use-site
 */

// TESTCASE NUMBER: 1
@Suppress("UNCHECKED_CAST")
fun test56211(): String = (listOf(1) as List<String>).first()

fun box(): String {
    return try {
        test56211()
        "NOK"
    } catch (e: ClassCastException) {
        "OK"
    }
}
