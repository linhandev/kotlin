// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: try expression as function call argument participates in type inference
 */

// TESTCASE NUMBER: 1
fun accept(n: Number) = n

fun test(): Number = accept(try {
    1
} catch (e: Exception) {
    2.0
})

fun throwAndCatch(): Number = accept(try {
    throw IllegalStateException()
} catch (e: Exception) {
    2.0
})

fun box(): String {
    val a = test()
    if (a !is Int || a != 1) return "NOK"
    val b = throwAndCatch()
    if (b !is Double || b != 2.0) return "NOK"
    return "OK"
}
