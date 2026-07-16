// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, not-null-assertion-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: not-null assertion !! on already non-nullable value has no effect
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val s: String = "ok"
    if (s!! != "ok") return "NOK"
    return "OK"
}
