// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 4 -> sentence 4
 *                expressions, call-and-property-access-expressions, class-literals -> paragraph 4 -> sentence 4
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: reified is-check agrees with T::class for matching and rejecting values
 */

// TESTCASE NUMBER: 1
inline fun <reified T : Any> matches56204(x: Any): Boolean = x is T && x::class == T::class

fun box(): String {
    if (!matches56204<Int>(1)) return "NOK"
    if (matches56204<Int>("s")) return "NOK"
    if (!matches56204<String>("s")) return "NOK"
    if (matches56204<String>(1)) return "NOK"
    return "OK"
}
