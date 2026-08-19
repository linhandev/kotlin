// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 30 -> sentence 30
 *                expressions, when-expressions -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: when branch x::class infers KClass<out Any> and compares with class literal, verifying type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<String>(label("a"))
}

fun label(x: Any): String = when (x::class) {
    String::class -> "str"
    else -> "other"
}
