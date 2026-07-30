// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 32 -> sentence 32
 *                type-inference, introduction-1 -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: explicit type argument overrides inferred type argument
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T): T = x

fun box(): String {
    val a: Any = id<Any>(1)
    if (a != 1) return "NOK"

    val b: Number = id<Number>(42)
    if (b != 42) return "NOK"

    val c: String = id<String>("world")
    if (c != "world") return "NOK"

    return "OK"
}
