/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: function values have function types as runtime types and execute correctly
 */
// TESTCASE NUMBER: 1

fun box(): String {
    val f: (Int) -> Int = { it + 1 }
    if (f(41) != 42) return "NOK: expected f(41)=42, got ${f(41)}"
    if (f !is Function1<*, *>) return "NOK: function value should have function runtime type"
    return "OK"
}
