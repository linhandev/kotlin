// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, cast-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unchecked cast performs runtime subtype check throws on failure and result type is T
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val value: Any = "ok"
    val cast: String = value as String
    if (cast != "ok") return "NOK"
    val failed = try {
        1 as String
        true
    } catch (_: ClassCastException) {
        false
    }
    return if (!failed) "OK" else "NOK"
}
