// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds, least-upper-bound -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, union-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: When branches resolve to common supertype LUB for enums and open classes
 * HELPERS: checkType
 */

interface I2
enum class E2A : I2 { A }
enum class E2B : I2 { B }

// TESTCASE NUMBER: 1
fun case_1(flag: Boolean): I2 = if (flag) E2A.A else E2B.B

// TESTCASE NUMBER: 2
fun case_2(flag: Boolean) {
    val x = if (flag) E2A.A else E2B.B
    checkSubtype<I2>(x)
}

open class B5
class D5A : B5()
class D5B : B5()

// TESTCASE NUMBER: 3
fun case_3(f: Boolean): B5 = when {
    f -> D5A()
    else -> D5B()
}

// TESTCASE NUMBER: 4
fun case_4(f: Boolean) {
    val x = when {
        f -> D5A()
        else -> D5B()
    }
    checkSubtype<B5>(x)
}
