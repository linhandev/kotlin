// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, cast-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: checked cast returns null on mismatch and result type is nullable variant of T
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val ok: Any = "ok"
    val castOk: String? = ok as? String
    if (castOk != "ok") return "NOK"
    val castFail: String? = 1 as? String
    if (castFail != null) return "NOK"
    return "OK"
}
