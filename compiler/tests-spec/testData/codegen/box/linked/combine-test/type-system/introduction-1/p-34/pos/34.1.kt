// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 34 -> sentence 34
 * PRIMARY LINKS: declarations, classifier-declaration, value-class-declaration -> paragraph 34 -> sentence 34
 *                expressions, cast-expressions -> paragraph 34 -> sentence 34
 *                expressions, when-expressions -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: incorrect Any to value-class cast via when is-String branch fails at runtime
 */

// TESTCASE NUMBER: 1
@JvmInline
value class UserId56234(val raw: Int)

fun test56234(): Int {
    val a: Any = "7"
    return when (a) {
        is String -> (a as UserId56234).raw
        else -> -1
    }
}

fun box(): String {
    return try {
        test56234()
        "NOK"
    } catch (e: ClassCastException) {
        "OK"
    }
}
