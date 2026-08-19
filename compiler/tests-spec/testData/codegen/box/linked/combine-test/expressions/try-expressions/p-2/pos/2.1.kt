// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-inference, introduction-1 -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: try and catch returning different numeric types infer common supertype Number
 */

// TESTCASE NUMBER: 1
fun test(): Number = try {
    1
} catch (e: Exception) {
    2.0
}

fun throwAndCatch(): Number = try {
    throw IllegalStateException()
} catch (e: Exception) {
    2.0
}

fun box(): String {
    val a = test()
    if (a !is Int || a != 1) return "NOK"
    val b = throwAndCatch()
    if (b !is Double || b != 2.0) return "NOK"
    return "OK"
}
