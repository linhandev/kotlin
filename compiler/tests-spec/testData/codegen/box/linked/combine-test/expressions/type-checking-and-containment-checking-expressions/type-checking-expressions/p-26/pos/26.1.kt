// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 26 -> sentence 26
 *                runtime-type-information, runtime-available-types -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: reified type parameter with non-nullable upper bound Number correctly checks is type at runtime
 */

// TESTCASE NUMBER: 1
inline fun <reified T : Number> isOfType(value: Any?): Boolean = value is T

fun box(): String {
    if (!isOfType<Int>(42)) return "NOK"
    if (!isOfType<Double>(3.14)) return "NOK"
    if (isOfType<Int>("hello")) return "NOK"
    if (isOfType<Double>(42)) return "NOK"
    return "OK"
}
