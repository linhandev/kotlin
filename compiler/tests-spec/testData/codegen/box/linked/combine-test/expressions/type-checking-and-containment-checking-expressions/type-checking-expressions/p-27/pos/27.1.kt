// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 27 -> sentence 27
 *                runtime-type-information, runtime-available-types -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: reified type parameter with nullable upper bound correctly checks is type at runtime
 */

// TESTCASE NUMBER: 1
inline fun <reified T : Number?> isOfType(value: Any?): Boolean = value is T

fun box(): String {
    if (!isOfType<Int>(42)) return "NOK"
    if (isOfType<Int>(null)) return "NOK"
    if (!isOfType<Double>(3.14)) return "NOK"
    if (!isOfType<Number?>(42)) return "NOK"
    if (!isOfType<Number?>(null)) return "NOK"
    return "OK"
}
