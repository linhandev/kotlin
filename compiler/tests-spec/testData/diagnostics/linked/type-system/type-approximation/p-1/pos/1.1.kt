// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -DEBUG_INFO_SMARTCAST -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-approximation -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, intersection-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Intersection types from smart casts approximate to common supertype in expected context
 * HELPERS: checkType
 */

interface IA { fun a(): Int }
interface IB { fun b(): String }
class C : IA, IB { override fun a() = 1; override fun b() = "ok" }

// TESTCASE NUMBER: 1
fun case_1(x: Any): Any? {
    if (x is C) {
        checkSubtype<Any>(x)
        return x
    }
    return null
}

// TESTCASE NUMBER: 2
fun case_2(): Comparable<*> = if (true) 1 else "a"

// TESTCASE NUMBER: 3
fun case_3(flag: Boolean) {
    val x: Comparable<*> = when {
        flag -> 1
        else -> "a"
    }
    checkSubtype<Comparable<*>>(x)
}
