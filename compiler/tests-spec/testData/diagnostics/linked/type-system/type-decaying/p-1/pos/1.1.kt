// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-decaying -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-approximation -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Expression types decay to expected type in assignment, return, and elvis contexts
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Any = when {
        true -> 1
        else -> "b"
    }
    checkSubtype<Any>(x)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val x: Number? = null ?: 1
    checkSubtype<Number>(x!!)
}


// TESTCASE NUMBER: 3
fun case_3(flag: Boolean): CharSequence = if (flag) "a" else StringBuilder("b")


// TESTCASE NUMBER: 4
fun case_4() {
    val items: List<Any> = if (true) listOf(1) else listOf("a")
    checkSubtype<List<Any>>(items)
}
