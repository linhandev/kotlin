// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, inheritance-from-built-in-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Mapper513 implements Function1<String, Int>; SuspendLength513 implements suspend (String) -> Int
 * HELPERS: checkType
 */

class Mapper513 : Function1<String, Int> {
    override fun invoke(value: String): Int = value.length
}

class SuspendLength513 : suspend (String) -> Int {
    override suspend fun invoke(value: String): Int = value.length
}

// TESTCASE NUMBER: 1
fun case1(m: Mapper513): Int = m("abc")

// TESTCASE NUMBER: 2
fun case2(s: SuspendLength513) {
    checkSubtype<suspend (String) -> Int>(s)
}
