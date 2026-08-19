// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: after nullable smart cast, value::class matches the non-null type class literal; combines with type-checking and Number/Int hierarchy
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val nullable: String? = "hello"
    val missing: String? = null

    if (nullable == null) return "NOK: expected non-null"
    if (nullable::class != String::class) return "NOK: smart-cast class literal"
    if (!String::class.isInstance(nullable)) return "NOK: String isInstance"
    if (nullable !is String) return "NOK: is-check String"

    if (missing != null) return "NOK: expected null"
    if (missing is String) return "NOK: null must not be String"

    val n: Number = 42
    if (n::class != Int::class) return "NOK: Number holding Int has Int classifier"
    if (n !is Int) return "NOK: is-check Int"
    if (Int::class == Number::class) return "NOK: Int classifier differs from Number"
    if (!Number::class.isInstance(n)) return "NOK: Number isInstance"
    return "OK"
}
