/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: null reference at runtime satisfies is check for nullable classifier types including star projection
 */
// TESTCASE NUMBER: 1

fun box(): String {
    val x: Any? = null
    if (!(x is String?)) return "NOK String?"
    if (!(x is Int?)) return "NOK Int?"
    if (!(x is List<*>?)) return "NOK List<*>?"
    val y: Any = "ok"
    if (y is Int?) return "NOK: non-null string must not satisfy Int?"
    return "OK"
}
