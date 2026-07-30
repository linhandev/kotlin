// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                declarations, declarations-with-type-parameters -> paragraph 24 -> sentence 24
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: reified type check is T? works with nullable instances including null, verifying runtime semantics
 */

inline fun <reified T> isT(x: Any?): Boolean = x is T?

// TESTCASE NUMBER: 1
fun test(): Boolean = isT<String?>(null)

fun box(): String {
    if (!test()) return "NOK1"
    if (!isT<String?>("hello")) return "NOK2"
    if (isT<Int?>("hello")) return "NOK3"
    return "OK"
}
