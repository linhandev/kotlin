// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 30 -> sentence 30
 *                expressions, when-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: when branch with ::class comparison matches runtime class, verifying runtime semantics
 */

// TESTCASE NUMBER: 1
fun label(x: Any): String = when (x::class) {
    String::class -> "str"
    Int::class -> "int"
    else -> "other"
}

// TESTCASE NUMBER: 2
fun test(): String = label("a")

fun box(): String {
    if (test() != "str") return "NOK1"
    if (label(42) != "int") return "NOK2"
    if (label(3.14) != "other") return "NOK3"
    return "OK"
}
